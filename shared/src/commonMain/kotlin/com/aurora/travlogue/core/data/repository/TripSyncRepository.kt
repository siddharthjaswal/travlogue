package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.mapper.*
import com.aurora.travlogue.core.domain.model.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Repository that synchronizes Trip data between local cache and remote backend
 *
 * Implements offline-first pattern:
 * - Reads: Return local data immediately, sync from remote in background
 * - Writes: Save locally first, sync to remote when online
 * - Conflicts: Last-write-wins strategy (for now)
 *
 * Uses IdMappingRepository to track UUID ↔ Int ID relationships
 */
class TripSyncRepository(
    private val localRepository: TripRepository,
    private val apiClient: LogbookApiClient,
    private val authManager: AuthManager,
    private val idMappingRepository: IdMappingRepository
) {
    /**
     * Get all trips with automatic sync
     * Returns local data immediately, triggers background sync
     */
    fun getAllTrips(forceRefresh: Boolean = false): Flow<List<Trip>> {
        return localRepository.allTrips
            .onStart {
                // Trigger sync in background when flow starts
                if (forceRefresh || shouldSync()) {
                    syncTripsFromRemote()
                }
            }
    }

    /**
     * Get trip by ID with automatic sync
     */
    fun getTripById(tripId: String): Flow<Trip?> {
        return localRepository.getTripById(tripId)
            .onStart {
                // Try to sync this specific trip
                syncTripFromRemote(tripId)
            }
    }

    /**
     * Create new trip
     * Saves locally first, then syncs to backend
     */
    suspend fun createTrip(trip: Trip): Result<Trip> {
        // 1. Save locally first (offline-first)
        try {
            localRepository.insertTrip(trip)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to save trip locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Mark as pending sync
            return Result.success(trip)
        }

        return syncTripToRemote(trip)
    }

    /**
     * Update existing trip
     * Updates locally first, then syncs to backend
     */
    suspend fun updateTrip(trip: Trip): Result<Trip> {
        // 1. Update locally first
        try {
            localRepository.updateTrip(trip)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to update trip locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            return Result.success(trip)
        }

        return syncTripUpdateToRemote(trip)
    }

    /**
     * Delete trip
     * Deletes locally first, then syncs to backend
     */
    suspend fun deleteTrip(tripId: String): Result<Unit> {
        // 1. Delete locally first
        try {
            localRepository.deleteTripById(tripId)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to delete trip locally: ${e.message}"))
        }

        // 2. Try to sync deletion to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Delete the mapping as well
            idMappingRepository.deleteByLocalId(tripId, EntityType.TRIP)
            return Result.success(Unit)
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(tripId, EntityType.TRIP)
        if (backendId == null) {
            // Trip only exists locally, no need to sync deletion
            return Result.success(Unit)
        }

        return apiClient.deleteTrip(backendId).fold(
            onSuccess = {
                // Delete the mapping after successful backend deletion
                idMappingRepository.deleteByLocalId(tripId, EntityType.TRIP)
                Result.success(Unit)
            },
            onFailure = { error ->
                // Deletion failed remotely but succeeded locally
                // Keep the mapping for retry later
                Result.failure(Exception("Failed to delete trip remotely: ${error.message}"))
            }
        )
    }

    /**
     * Force sync all trips from remote
     */
    suspend fun syncTripsFromRemote(): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        return apiClient.listMyTrips().fold(
            onSuccess = { tripDtos ->
                try {
                    // Convert DTOs to domain models and save to local DB
                    tripDtos.forEach { dto ->
                        val trip = dto.toDomainModel()
                        val backendId = dto.id

                        // Save the ID mapping
                        idMappingRepository.saveMapping(
                            localId = trip.id,
                            backendId = backendId,
                            entityType = EntityType.TRIP
                        )

                        // Try to update existing trip, or insert if new
                        try {
                            localRepository.updateTrip(trip)
                        } catch (e: Exception) {
                            localRepository.insertTrip(trip)
                        }
                    }
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced trips: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync trips from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync single trip from remote
     */
    private suspend fun syncTripFromRemote(tripId: String): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(tripId, EntityType.TRIP)
            ?: return Result.success(Unit) // Trip only exists locally

        return apiClient.getTrip(backendId).fold(
            onSuccess = { tripDto ->
                try {
                    val trip = tripDto.toDomainModel()

                    // Update the ID mapping
                    idMappingRepository.saveMapping(
                        localId = trip.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP
                    )

                    try {
                        localRepository.updateTrip(trip)
                    } catch (e: Exception) {
                        localRepository.insertTrip(trip)
                    }
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced trip: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync trip from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync new trip to remote
     */
    private suspend fun syncTripToRemote(trip: Trip): Result<Trip> {
        val createDto = trip.toCreateDto()

        return apiClient.createTrip(createDto).fold(
            onSuccess = { responseDto ->
                try {
                    val backendId = responseDto.id
                    val syncedTrip = responseDto.toDomainModel()

                    // Save the ID mapping UUID ↔ Int
                    idMappingRepository.saveMapping(
                        localId = trip.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP
                    )

                    // Update local trip with backend ID
                    localRepository.deleteTripById(trip.id) // Delete old UUID-based trip
                    localRepository.insertTrip(syncedTrip) // Insert with backend ID

                    Result.success(syncedTrip)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local trip with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                // Trip is saved locally but failed to sync
                // Mark as pending sync
                Result.failure(Exception("Failed to create trip remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync trip update to remote
     */
    private suspend fun syncTripUpdateToRemote(trip: Trip): Result<Trip> {
        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(trip.id, EntityType.TRIP)
        if (backendId == null) {
            // Trip only exists locally, create it on backend instead
            return syncTripToRemote(trip)
        }

        val updateDto = trip.toUpdateDto()

        return apiClient.updateTrip(backendId, updateDto).fold(
            onSuccess = { responseDto ->
                try {
                    val syncedTrip = responseDto.toDomainModel()

                    // Update the ID mapping timestamp
                    idMappingRepository.saveMapping(
                        localId = trip.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP
                    )

                    localRepository.updateTrip(syncedTrip)
                    Result.success(syncedTrip)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local trip with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to update trip remotely: ${error.message}"))
            }
        )
    }

    /**
     * Check if we should sync based on last sync time
     * For now, always return true (sync on every access)
     * TODO: Implement smarter sync strategy with timestamps
     */
    private fun shouldSync(): Boolean {
        return authManager.isAuthenticated()
    }

    /**
     * Search trips (remote only for now)
     */
    suspend fun searchTrips(query: String): Result<List<Trip>> {
        if (!authManager.isAuthenticated()) {
            // Fallback to local search
            val localTrips = localRepository.allTrips.first()
            val filtered = localTrips.filter { trip ->
                trip.name.contains(query, ignoreCase = true) ||
                trip.originCity.contains(query, ignoreCase = true)
            }
            return Result.success(filtered)
        }

        return apiClient.searchTrips(query).fold(
            onSuccess = { tripDtos ->
                val trips = tripDtos.map { it.toDomainModel() }
                Result.success(trips)
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to search trips: ${error.message}"))
            }
        )
    }
}
