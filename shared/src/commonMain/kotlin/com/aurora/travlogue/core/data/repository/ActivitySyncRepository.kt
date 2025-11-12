package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.mapper.toDomainModel
import com.aurora.travlogue.core.data.remote.mapper.toCreateDto
import com.aurora.travlogue.core.data.remote.mapper.toUpdateDto
import com.aurora.travlogue.core.domain.model.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * Repository that synchronizes Activity data between local cache and remote backend
 *
 * Similar to TripSyncRepository, implements offline-first pattern:
 * - Reads: Return local data immediately, sync from remote in background
 * - Writes: Save locally first, sync to remote when online
 *
 * Uses IdMappingRepository to track UUID ↔ Int ID relationships
 *
 * Note: Activities are associated with TripDays in the backend,
 * but with Locations in the local model. We need to handle this mapping.
 */
class ActivitySyncRepository(
    private val localRepository: TripRepository,
    private val apiClient: LogbookApiClient,
    private val authManager: AuthManager,
    private val idMappingRepository: IdMappingRepository
) {
    /**
     * Get activities for a location with automatic sync
     * Returns local data immediately, triggers background sync
     */
    fun getActivitiesForLocation(locationId: String): Flow<List<Activity>> {
        return localRepository.getActivitiesForLocation(locationId)
            .onStart {
                // Trigger sync in background when flow starts
                if (shouldSync()) {
                    syncActivitiesForLocation(locationId)
                }
            }
    }

    /**
     * Get activities for a trip with automatic sync
     */
    fun getActivitiesForTrip(tripId: String): Flow<List<Activity>> {
        return localRepository.getActivitiesForTrip(tripId)
            .onStart {
                if (shouldSync()) {
                    syncActivitiesForTrip(tripId)
                }
            }
    }

    /**
     * Create new activity
     * Saves locally first, then syncs to backend
     *
     * Note: Requires tripDayId from backend. For offline creation,
     * the activity is saved locally and synced when trip day is created.
     */
    suspend fun createActivity(activity: Activity, tripDayId: Int? = null): Result<Activity> {
        // 1. Save locally first (offline-first)
        try {
            localRepository.insertActivity(activity)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to save activity locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated and have tripDayId
        if (!authManager.isAuthenticated() || tripDayId == null) {
            // Mark as pending sync
            return Result.success(activity)
        }

        return syncActivityToRemote(activity, tripDayId)
    }

    /**
     * Update existing activity
     * Updates locally first, then syncs to backend
     */
    suspend fun updateActivity(activity: Activity, tripDayId: Int? = null): Result<Activity> {
        // 1. Update locally first
        try {
            localRepository.updateActivity(activity)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to update activity locally: ${e.message}"))
        }

        // 2. Try to sync to backend if authenticated
        if (!authManager.isAuthenticated()) {
            return Result.success(activity)
        }

        return syncActivityUpdateToRemote(activity, tripDayId)
    }

    /**
     * Delete activity
     * Deletes locally first, then syncs to backend
     */
    suspend fun deleteActivity(activityId: String): Result<Unit> {
        // 1. Delete locally first
        try {
            localRepository.deleteActivityById(activityId)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to delete activity locally: ${e.message}"))
        }

        // 2. Try to sync deletion to backend if authenticated
        if (!authManager.isAuthenticated()) {
            // Delete the mapping as well
            idMappingRepository.deleteByLocalId(activityId, EntityType.ACTIVITY)
            return Result.success(Unit)
        }

        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(activityId, EntityType.ACTIVITY)
        if (backendId == null) {
            // Activity only exists locally, no need to sync deletion
            return Result.success(Unit)
        }

        return apiClient.deleteActivity(backendId).fold(
            onSuccess = {
                // Delete the mapping after successful backend deletion
                idMappingRepository.deleteByLocalId(activityId, EntityType.ACTIVITY)
                Result.success(Unit)
            },
            onFailure = { error ->
                // Deletion failed remotely but succeeded locally
                // Keep the mapping for retry later
                Result.failure(Exception("Failed to delete activity remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync activities for a specific trip day from remote
     *
     * Note: This requires knowing the backend tripDayId.
     * For now, this is a manual operation.
     */
    suspend fun syncActivitiesForTripDay(tripDayId: Int, locationId: String): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        return apiClient.listActivitiesByTripDay(tripDayId).fold(
            onSuccess = { activityDtos ->
                try {
                    // Convert DTOs to domain models and save to local DB
                    activityDtos.forEach { dto ->
                        val activity = dto.toDomainModel(locationId)
                        val backendId = dto.id

                        // Save the ID mapping
                        idMappingRepository.saveMapping(
                            localId = activity.id,
                            backendId = backendId,
                            entityType = EntityType.ACTIVITY
                        )

                        // Try to update existing activity, or insert if new
                        try {
                            localRepository.updateActivity(activity)
                        } catch (e: Exception) {
                            localRepository.insertActivity(activity)
                        }
                    }
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to save synced activities: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to sync activities from remote: ${error.message}"))
            }
        )
    }

    /**
     * Sync activities for a location from remote
     * This is more complex as we need to map location → trip day
     */
    private suspend fun syncActivitiesForLocation(locationId: String): Result<Unit> {
        // TODO: Implement when we have location → trip day mapping
        // For now, this is a no-op
        return Result.success(Unit)
    }

    /**
     * Sync activities for a trip from remote
     * This requires iterating through all trip days
     */
    private suspend fun syncActivitiesForTrip(tripId: String): Result<Unit> {
        // TODO: Implement when we have full trip day sync
        // For now, this is a no-op
        return Result.success(Unit)
    }

    /**
     * Sync new activity to remote
     */
    private suspend fun syncActivityToRemote(activity: Activity, tripDayId: Int): Result<Activity> {
        val createDto = activity.toCreateDto(
            tripDayId = tripDayId,
            displayOrder = 0
        )

        return apiClient.createActivity(createDto).fold(
            onSuccess = { responseDto ->
                try {
                    val backendId = responseDto.id
                    val syncedActivity = responseDto.toDomainModel(activity.locationId)

                    // Save the ID mapping UUID ↔ Int
                    idMappingRepository.saveMapping(
                        localId = activity.id,
                        backendId = backendId,
                        entityType = EntityType.ACTIVITY
                    )

                    // Update local activity with backend ID
                    localRepository.deleteActivityById(activity.id) // Delete old UUID-based activity
                    localRepository.insertActivity(syncedActivity) // Insert with backend ID

                    Result.success(syncedActivity)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local activity with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to create activity remotely: ${error.message}"))
            }
        )
    }

    /**
     * Sync activity update to remote
     */
    private suspend fun syncActivityUpdateToRemote(activity: Activity, tripDayId: Int?): Result<Activity> {
        // Convert local ID to backend ID using ID mapping
        val backendId = idMappingRepository.resolveToBackendId(activity.id, EntityType.ACTIVITY)
        if (backendId == null) {
            // Activity only exists locally, create it on backend instead
            return if (tripDayId != null) {
                syncActivityToRemote(activity, tripDayId)
            } else {
                Result.failure(Exception("Cannot sync activity without tripDayId"))
            }
        }

        val updateDto = activity.toUpdateDto()

        return apiClient.updateActivity(backendId, updateDto).fold(
            onSuccess = { responseDto ->
                try {
                    val syncedActivity = responseDto.toDomainModel(activity.locationId)

                    // Update the ID mapping timestamp
                    idMappingRepository.saveMapping(
                        localId = activity.id,
                        backendId = backendId,
                        entityType = EntityType.ACTIVITY
                    )

                    localRepository.updateActivity(syncedActivity)
                    Result.success(syncedActivity)
                } catch (e: Exception) {
                    Result.failure(Exception("Failed to update local activity with backend data: ${e.message}"))
                }
            },
            onFailure = { error ->
                Result.failure(Exception("Failed to update activity remotely: ${error.message}"))
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
