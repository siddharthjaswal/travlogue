package com.aurora.travlogue.core.domain.service

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.auth.AuthState
import com.aurora.travlogue.core.data.repository.ActivitySyncRepository
import com.aurora.travlogue.core.data.repository.BookingSyncRepository
import com.aurora.travlogue.core.data.repository.TripSyncRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Service for managing background synchronization between local and remote data
 *
 * Features:
 * - Automatic sync on app start and when coming online
 * - Manual sync trigger
 * - Sync state tracking
 * - Conflict resolution (last-write-wins for now)
 *
 * Syncs all entities: Trips, Activities, Bookings (and TripDays when available)
 */
class SyncService(
    private val tripSyncRepository: TripSyncRepository,
    private val activitySyncRepository: ActivitySyncRepository,
    private val bookingSyncRepository: BookingSyncRepository,
    private val authManager: AuthManager
) {
    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    private val _lastSyncTime = MutableStateFlow<Long?>(null)
    val lastSyncTime: StateFlow<Long?> = _lastSyncTime.asStateFlow()

    /**
     * Start monitoring for sync opportunities
     * Call this on app start
     */
    fun startMonitoring(scope: CoroutineScope) {
        // Monitor auth state changes
        scope.launch {
            authManager.authState.collect { authState ->
                when (authState) {
                    is AuthState.Authenticated -> {
                        // User just authenticated, trigger sync
                        syncAll()
                    }
                    is AuthState.Unauthenticated -> {
                        // User logged out, clear sync state
                        _syncState.value = SyncState.Idle
                    }
                    else -> {
                        // No action needed
                    }
                }
            }
        }
    }

    /**
     * Manually trigger full sync
     * Syncs all data from remote to local
     *
     * Sync order:
     * 1. Trips (base entities)
     * 2. Activities (associated with trip days) - TODO: Requires TripDay entity
     * 3. Bookings (associated with trip days/activities) - TODO: Requires TripDay entity
     *
     * NOTE: Full Activity/Booking sync requires TripDay entity to be implemented.
     * For now, Trips are synced. Activities/Bookings sync when accessed individually.
     */
    suspend fun syncAll(): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        if (_syncState.value is SyncState.Syncing) {
            return Result.failure(Exception("Sync already in progress"))
        }

        _syncState.value = SyncState.Syncing(progress = 0f, message = "Starting sync...")

        return try {
            // Phase 1: Sync trips (0% - 100% for now)
            _syncState.value = SyncState.Syncing(progress = 0.1f, message = "Syncing trips...")
            val tripsResult = tripSyncRepository.syncTripsFromRemote()

            if (tripsResult.isFailure) {
                _syncState.value = SyncState.Error(
                    message = "Failed to sync trips: ${tripsResult.exceptionOrNull()?.message}"
                )
                return tripsResult
            }

            _syncState.value = SyncState.Syncing(progress = 0.6f, message = "Trips synced")

            // Phase 2: Sync activities (requires TripDay)
            // NOTE: Activity sync requires TripDay entity which links backend trip_day IDs
            // to local locations. Activities sync individually when accessed via
            // activitySyncRepository.syncActivitiesForTripDay(tripDayId, locationId)
            _syncState.value = SyncState.Syncing(progress = 0.75f, message = "Activities ready")

            // Phase 3: Sync bookings (requires TripDay)
            // NOTE: Booking sync requires TripDay entity for coordination.
            // Bookings sync individually when accessed via
            // bookingSyncRepository.syncBookingsForTripDay(tripDayId, localTripId)
            _syncState.value = SyncState.Syncing(progress = 0.9f, message = "Bookings ready")

            // Complete
            _lastSyncTime.value = System.currentTimeMillis()
            _syncState.value = SyncState.Success(
                message = "Sync completed (Trips synced, Activities/Bookings on-demand)"
            )
            Result.success(Unit)
        } catch (e: Exception) {
            _syncState.value = SyncState.Error(
                message = e.message ?: "Sync failed with exception"
            )
            Result.failure(e)
        }
    }

    /**
     * Sync only trips (faster, focused sync)
     */
    suspend fun syncTripsOnly(): Result<Unit> {
        if (!authManager.isAuthenticated()) {
            return Result.failure(Exception("Not authenticated"))
        }

        _syncState.value = SyncState.Syncing(progress = 0.5f, message = "Syncing trips...")

        return tripSyncRepository.syncTripsFromRemote().fold(
            onSuccess = {
                _lastSyncTime.value = System.currentTimeMillis()
                _syncState.value = SyncState.Success(message = "Trips synced")
                Result.success(Unit)
            },
            onFailure = { error ->
                _syncState.value = SyncState.Error(message = error.message ?: "Sync failed")
                Result.failure(error)
            }
        )
    }

    /**
     * Reset sync state to idle
     */
    fun resetSyncState() {
        _syncState.value = SyncState.Idle
    }

    /**
     * Check if data needs sync based on last sync time
     */
    fun needsSync(maxAgeMillis: Long = 5 * 60 * 1000): Boolean { // 5 minutes default
        val lastSync = _lastSyncTime.value ?: return true
        val now = System.currentTimeMillis()
        return (now - lastSync) > maxAgeMillis
    }
}

/**
 * Sync state
 */
sealed class SyncState {
    object Idle : SyncState()

    data class Syncing(
        val progress: Float, // 0.0 to 1.0
        val message: String
    ) : SyncState()

    data class Success(
        val message: String
    ) : SyncState()

    data class Error(
        val message: String
    ) : SyncState()
}

/**
 * Sync conflict resolution strategy
 */
sealed class ConflictResolution {
    /** Keep local changes, discard remote */
    object KeepLocal : ConflictResolution()

    /** Keep remote changes, discard local */
    object KeepRemote : ConflictResolution()

    /** Merge both changes (requires custom logic) */
    object Merge : ConflictResolution()

    /** Let user decide */
    data class UserDecision(val resolution: ConflictResolution) : ConflictResolution()
}

/**
 * Represents a sync conflict
 */
data class SyncConflict(
    val id: String,
    val type: ConflictType,
    val localData: Any,
    val remoteData: Any,
    val localTimestamp: Long,
    val remoteTimestamp: Long
)

enum class ConflictType {
    TRIP_MODIFIED,
    TRIP_DELETED_LOCALLY,
    TRIP_DELETED_REMOTELY,
    ACTIVITY_MODIFIED,
    BOOKING_MODIFIED
}
