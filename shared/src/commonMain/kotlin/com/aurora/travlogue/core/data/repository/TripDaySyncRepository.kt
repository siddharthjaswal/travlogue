package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.mapper.toDomainModel
import com.aurora.travlogue.core.data.remote.mapper.toCreateDto
import com.aurora.travlogue.core.data.remote.mapper.toUpdateDto
import com.aurora.travlogue.core.domain.model.TripDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository that synchronizes TripDay data between local cache and remote backend
 *
 * Implements offline-first pattern:
 * - Reads: Return local data immediately, sync from remote in background
 * - Writes: Save locally first, sync to remote when online
 *
 * Uses IdMappingRepository to track UUID ↔ Int ID relationships
 *
 * Note: TripDays bridge backend trip_day entities with local location entities
 */
class TripDaySyncRepository(
    private val database: com.aurora.travlogue.core.data.local.TravlogueDb,
    private val apiClient: LogbookApiClient,
    private val authManager: AuthManager,
    private val idMappingRepository: IdMappingRepository
) {
    /**
     * Get all trip days for a trip with automatic sync
     * Returns local data immediately, triggers background sync
     */
    fun getTripDaysForTrip(tripId: String): Flow<List<TripDay>> {
        return flow {
            // Return local data immediately
            val localTripDays = database.tripDayQueries
                .getTripDaysForTrip(tripId)
                .executeAsList()
                .map { it.toDomainModel() }

            emit(localTripDays)

            // Trigger background sync if authenticated
            if (shouldSync()) {
                syncTripDaysForTrip(tripId)
            }
        }
    }

    /**
     * Get trip day by ID with automatic sync
     */
    fun getTripDayById(tripDayId: String): Flow<TripDay?> {
        return flow {
            // Return local data immediately
            val localTripDay = database.tripDayQueries
                .getTripDayById(tripDayId)
                .executeAsOneOrNull()
                ?.toDomainModel()

            emit(localTripDay)

            // Try to sync this specific trip day
            if (shouldSync()) {
                syncTripDayFromRemote(tripDayId)
            }
        }
    }

    /**
     * Create new trip day
     * Saves locally first, then syncs to backend
     */
    suspend fun createTripDay(tripDay: TripDay): Result<TripDay> {
        // 1. Save locally first (offline-first)
        try {
            database.tripDayQueries.insertOrReplace(
                id = tripDay.id,
                trip_id = tripDay.tripId,
                date = tripDay.date,
                day_number = tripDay.dayNumber.toLong(),
                day_type = tripDay.dayType.name,
                title = tripDay.title,
                notes = tripDay.notes,
                country = tripDay.country,
                city = tripDay.city,
                transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(tripDay.transitDetails),
                location_id = tripDay.locationId,
                created_at = tripDay.createdAt,
                updated_at = tripDay.updatedAt
            )
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to save trip day locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Mark as pending sync
            return Result.success(tripDay)
        }

        // Need backend trip ID to create trip day
        val backendTripId = idMappingRepository.resolveToBackendId(tripDay.tripId, EntityType.TRIP)
        if (backendTripId == null) {
            // Trip not synced yet, mark trip day as pending sync
            return Result.success(tripDay)
        }

        return syncTripDayToRemote(tripDay, backendTripId)
    }

    /**
     * Update existing trip day
     * Updates locally first, then syncs to backend
     */
    suspend fun updateTripDay(tripDay: TripDay): Result<TripDay> {
        // 1. Update locally first
        try {
            database.tripDayQueries.updateTripDay(
                date = tripDay.date,
                day_number = tripDay.dayNumber.toLong(),
                day_type = tripDay.dayType.name,
                title = tripDay.title,
                notes = tripDay.notes,
                country = tripDay.country,
                city = tripDay.city,
                transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(tripDay.transitDetails),
                location_id = tripDay.locationId,
                updated_at = System.currentTimeMillis().toString(),
                id = tripDay.id
            )
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to update trip day locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            return Result.success(tripDay)
        }

        return syncTripDayUpdateToRemote(tripDay)
    }

    /**
     * Delete trip day
     * Deletes locally first, then syncs to backend
     */
    suspend fun deleteTripDay(tripDayId: String): Result<Unit> {
        // 1. Delete locally first
        try {
            database.tripDayQueries.deleteTripDayById(tripDayId)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to delete trip day locally: ${e.message}"))
        }

        // 2. Try to sync deletion to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Delete the mapping as well
            idMappingRepository.deleteByLocalId(tripDayId, EntityType.TRIP_DAY)
            return Result.success(Unit)
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(tripDayId, EntityType.TRIP_DAY)
        if (backendId == null) {
            // Trip day only exists locally, no need to sync deletion
            return Result.success(Unit)
        }

        return apiClient.deleteTripDay(backendId).fold(
            onSuccess = {
                // Delete the mapping after successful backend deletion
                idMappingRepository.deleteByLocalId(tripDayId, EntityType.TRIP_DAY)
                Result.success(Unit)
            },
            onFailure = { error ->
                // Deletion failed remotely but succeeded locally
                // Keep the mapping for retry later
                Result.failure(Exception("Failed to delete trip day remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync all trip days for a trip from remote
     */
    suspend fun syncTripDaysForTrip(localTripId: String): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        // Need backend trip ID to fetch trip days
        val backendTripId = idMappingRepository.resolveToBackendId(localTripId, EntityType.TRIP)
            ?: return Result.success(Unit) // Trip only exists locally

        return apiClient.listTripDays(tripId = backendTripId).fold(
            onSuccess = { tripDayDtos ->
                try {
                    // Convert DTOs to domain models and save to local DB
                    tripDayDtos.forEach { dto ->
                        val tripDay = dto.toDomainModel(localTripId)
                        val backendId = dto.id

                        // Save the ID mapping
                        idMappingRepository.saveMapping(
                            localId = tripDay.id,
                            backendId = backendId,
                            entityType = EntityType.TRIP_DAY
                        )

                        // Save to database
                        database.tripDayQueries.insertOrReplace(
                            id = tripDay.id,
                            trip_id = tripDay.tripId,
                            date = tripDay.date,
                            day_number = tripDay.dayNumber.toLong(),
                            day_type = tripDay.dayType.name,
                            title = tripDay.title,
                            notes = tripDay.notes,
                            country = tripDay.country,
                            city = tripDay.city,
                            transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(tripDay.transitDetails),
                            location_id = tripDay.locationId,
                            created_at = tripDay.createdAt,
                            updated_at = tripDay.updatedAt
                        )
                    }
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced trip days: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync trip days from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync single trip day from remote
     */
    private suspend fun syncTripDayFromRemote(tripDayId: String): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(tripDayId, EntityType.TRIP_DAY)
            ?: return Result.success(Unit) // Trip day only exists locally

        return apiClient.getTripDay(backendId).fold(
            onSuccess = { tripDayDto ->
                try {
                    // Get local trip ID from trip mapping
                    val localTripId = idMappingRepository.resolveToLocalId(
                        tripDayDto.tripId.toString(),
                        EntityType.TRIP
                    )

                    val tripDay = tripDayDto.toDomainModel(localTripId)

                    // Update the ID mapping
                    idMappingRepository.saveMapping(
                        localId = tripDay.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP_DAY
                    )

                    // Save to database
                    database.tripDayQueries.insertOrReplace(
                        id = tripDay.id,
                        trip_id = tripDay.tripId,
                        date = tripDay.date,
                        day_number = tripDay.dayNumber.toLong(),
                        day_type = tripDay.dayType.name,
                        title = tripDay.title,
                        notes = tripDay.notes,
                        country = tripDay.country,
                        city = tripDay.city,
                        transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(tripDay.transitDetails),
                        location_id = tripDay.locationId,
                        created_at = tripDay.createdAt,
                        updated_at = tripDay.updatedAt
                    )
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced trip day: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync trip day from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync new trip day to remote
     */
    private suspend fun syncTripDayToRemote(tripDay: TripDay, backendTripId: Int): Result<TripDay> {
        val createDto = tripDay.toCreateDto(backendTripId)

        return apiClient.createTripDay(createDto).fold(
            onSuccess = { responseDto ->
                try {
                    val backendId = responseDto.id
                    val syncedTripDay = responseDto.toDomainModel(tripDay.tripId)

                    // Save the ID mapping UUID ↔ Int
                    idMappingRepository.saveMapping(
                        localId = tripDay.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP_DAY
                    )

                    // Update local trip day with backend data
                    database.tripDayQueries.insertOrReplace(
                        id = syncedTripDay.id,
                        trip_id = syncedTripDay.tripId,
                        date = syncedTripDay.date,
                        day_number = syncedTripDay.dayNumber.toLong(),
                        day_type = syncedTripDay.dayType.name,
                        title = syncedTripDay.title,
                        notes = syncedTripDay.notes,
                        country = syncedTripDay.country,
                        city = syncedTripDay.city,
                        transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(syncedTripDay.transitDetails),
                        location_id = syncedTripDay.locationId,
                        created_at = syncedTripDay.createdAt,
                        updated_at = syncedTripDay.updatedAt
                    )

                    Result.success(syncedTripDay)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local trip day with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to create trip day remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync trip day update to remote
     */
    private suspend fun syncTripDayUpdateToRemote(tripDay: TripDay): Result<TripDay> {
        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(tripDay.id, EntityType.TRIP_DAY)
        if (backendId == null) {
            // Trip day only exists locally, would need backend trip ID to create
            return Result.failure(Exception("Cannot sync local-only trip day without trip sync"))
        }

        val updateDto = tripDay.toUpdateDto()

        return apiClient.updateTripDay(backendId, updateDto).fold(
            onSuccess = { responseDto ->
                try {
                    val syncedTripDay = responseDto.toDomainModel(tripDay.tripId)

                    // Update the ID mapping timestamp
                    idMappingRepository.saveMapping(
                        localId = tripDay.id,
                        backendId = backendId,
                        entityType = EntityType.TRIP_DAY
                    )

                    // Update local database
                    database.tripDayQueries.insertOrReplace(
                        id = syncedTripDay.id,
                        trip_id = syncedTripDay.tripId,
                        date = syncedTripDay.date,
                        day_number = syncedTripDay.dayNumber.toLong(),
                        day_type = syncedTripDay.dayType.name,
                        title = syncedTripDay.title,
                        notes = syncedTripDay.notes,
                        country = syncedTripDay.country,
                        city = syncedTripDay.city,
                        transit_details = com.aurora.travlogue.core.data.remote.mapper.toJson(syncedTripDay.transitDetails),
                        location_id = syncedTripDay.locationId,
                        created_at = syncedTripDay.createdAt,
                        updated_at = syncedTripDay.updatedAt
                    )

                    Result.success(syncedTripDay)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local trip day with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to update trip day remotely: ${error.message}"))
            }
        )
    }

    /**
     * Check if we should sync
     */
    private fun shouldSync(): Boolean {
        return authManager.isAuthenticated()
    }
}

/**
 * Extension function to convert SQLDelight TripDay to domain model
 */
private fun com.aurora.travlogue.database.Trip_days.toDomainModel(): TripDay {
    return TripDay(
        id = id,
        tripId = trip_id,
        date = date,
        dayNumber = day_number.toInt(),
        dayType = com.aurora.travlogue.core.domain.model.DayType.fromString(day_type),
        title = title,
        notes = notes,
        country = country,
        city = city,
        transitDetails = com.aurora.travlogue.core.data.remote.mapper.parseTransitDetails(transit_details),
        locationId = location_id,
        createdAt = created_at,
        updatedAt = updated_at
    )
}
