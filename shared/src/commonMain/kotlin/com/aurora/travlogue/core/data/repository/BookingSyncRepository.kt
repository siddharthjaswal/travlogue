package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.mapper.toDomainModel
import com.aurora.travlogue.core.data.remote.mapper.toCreateDto
import com.aurora.travlogue.core.data.remote.mapper.toUpdateDto
import com.aurora.travlogue.core.domain.model.Booking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * Repository that synchronizes Booking data between local cache and remote backend
 *
 * Implements offline-first pattern:
 * - Reads: Return local data immediately, sync from remote in background
 * - Writes: Save locally first, sync to remote when online
 *
 * Uses IdMappingRepository to track UUID ↔ Int ID relationships
 *
 * Note: Bookings in the backend are associated with TripDays and optionally Activities.
 * The local model uses tripId directly, so we need to handle this mapping.
 */
class BookingSyncRepository(
    private val localRepository: TripRepository,
    private val apiClient: LogbookApiClient,
    private val authManager: AuthManager,
    private val idMappingRepository: IdMappingRepository
) {
    /**
     * Get bookings for a trip with automatic sync
     * Returns local data immediately, triggers background sync
     */
    fun getBookingsForTrip(tripId: String): Flow<List<Booking>> {
        return localRepository.getBookingsForTrip(tripId)
            .onStart {
                // Trigger sync in background when flow starts
                if (shouldSync()) {
                    syncBookingsForTrip(tripId)
                }
            }
    }

    /**
     * Create new booking
     * Saves locally first, then syncs to backend
     *
     * @param booking The booking to create
     * @param tripDayId Optional backend trip day ID for immediate sync
     * @param activityId Optional backend activity ID to associate with
     */
    suspend fun createBooking(
        booking: Booking,
        tripDayId: Int? = null,
        activityId: Int? = null
    ): Result<Booking> {
        // 1. Save locally first (offline-first)
        try {
            localRepository.insertBooking(booking)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to save booking locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated and have tripDayId
        if (!authManager.isAuthenticated() || tripDayId == null) {
            // Mark as pending sync
            return Result.success(booking)
        }

        return syncBookingToRemote(booking, tripDayId, activityId)
    }

    /**
     * Update existing booking
     * Updates locally first, then syncs to backend
     */
    suspend fun updateBooking(booking: Booking): Result<Booking> {
        // 1. Update locally first
        try {
            localRepository.updateBooking(booking)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to update booking locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            return Result.success(booking)
        }

        return syncBookingUpdateToRemote(booking)
    }

    /**
     * Delete booking
     * Deletes locally first, then syncs to backend
     */
    suspend fun deleteBooking(bookingId: String): Result<Unit> {
        // 1. Delete locally first
        try {
            localRepository.deleteBooking(
                localRepository.getBookingsForTripSync(bookingId).firstOrNull()
                    ?: return Result.failure(Exception("Booking not found"))
            )
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to delete booking locally: ${e.message}"))
        }

        // 2. Try to sync deletion to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Delete the mapping as well
            idMappingRepository.deleteByLocalId(bookingId, EntityType.BOOKING)
            return Result.success(Unit)
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(bookingId, EntityType.BOOKING)
        if (backendId == null) {
            // Booking only exists locally, no need to sync deletion
            return Result.success(Unit)
        }

        return apiClient.deleteBooking(backendId).fold(
            onSuccess = {
                // Delete the mapping after successful backend deletion
                idMappingRepository.deleteByLocalId(bookingId, EntityType.BOOKING)
                Result.success(Unit)
            },
            onFailure = { error ->
                // Deletion failed remotely but succeeded locally
                // Keep the mapping for retry later
                Result.failure(Exception("Failed to delete booking remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync bookings for a specific trip day from remote
     *
     * @param tripDayId Backend trip day ID
     * @param localTripId Local trip ID to associate bookings with
     */
    suspend fun syncBookingsForTripDay(tripDayId: Int, localTripId: String): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        return apiClient.listBookingsByTripDay(tripDayId).fold(
            onSuccess = { bookingDtos ->
                try {
                    // Convert DTOs to domain models and save to local DB
                    bookingDtos.forEach { dto ->
                        val booking = dto.toDomainModel().copy(tripId = localTripId)
                        val backendId = dto.id

                        // Save the ID mapping
                        idMappingRepository.saveMapping(
                            localId = booking.id,
                            backendId = backendId,
                            entityType = EntityType.BOOKING
                        )

                        // Try to update existing booking, or insert if new
                        try {
                            localRepository.updateBooking(booking)
                        } catch (e: Exception) {
                            localRepository.insertBooking(booking)
                        }
                    }
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced bookings: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync bookings from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync bookings for a trip from remote
     * This requires iterating through all trip days
     */
    private suspend fun syncBookingsForTrip(tripId: String): Result<Unit> {
        // TODO: Implement when we have full trip day sync
        // For now, this is a no-op
        return Result.success(Unit)
    }

    /**
     * Sync new booking to remote
     */
    private suspend fun syncBookingToRemote(
        booking: Booking,
        tripDayId: Int,
        activityId: Int?
    ): Result<Booking> {
        val createDto = booking.toCreateDto(
            tripDayId = tripDayId,
            activityId = activityId
        )

        return apiClient.createBooking(createDto).fold(
            onSuccess = { responseDto ->
                try {
                    val backendId = responseDto.id
                    val syncedBooking = responseDto.toDomainModel().copy(tripId = booking.tripId)

                    // Save the ID mapping UUID ↔ Int
                    idMappingRepository.saveMapping(
                        localId = booking.id,
                        backendId = backendId,
                        entityType = EntityType.BOOKING
                    )

                    // Update local booking with backend ID
                    localRepository.deleteBooking(booking) // Delete old UUID-based booking
                    localRepository.insertBooking(syncedBooking) // Insert with backend ID

                    Result.success(syncedBooking)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local booking with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to create booking remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync booking update to remote
     */
    private suspend fun syncBookingUpdateToRemote(booking: Booking): Result<Booking> {
        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(booking.id, EntityType.BOOKING)
        if (backendId == null) {
            // Booking only exists locally
            // Can't sync without knowing tripDayId
            return Result.failure(Exception("Cannot sync local-only booking without tripDayId"))
        }

        val updateDto = booking.toUpdateDto()

        return apiClient.updateBooking(backendId, updateDto).fold(
            onSuccess = { responseDto ->
                try {
                    val syncedBooking = responseDto.toDomainModel().copy(tripId = booking.tripId)

                    // Update the ID mapping timestamp
                    idMappingRepository.saveMapping(
                        localId = booking.id,
                        backendId = backendId,
                        entityType = EntityType.BOOKING
                    )

                    localRepository.updateBooking(syncedBooking)
                    Result.success(syncedBooking)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local booking with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to update booking remotely: ${error.message}"))
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
