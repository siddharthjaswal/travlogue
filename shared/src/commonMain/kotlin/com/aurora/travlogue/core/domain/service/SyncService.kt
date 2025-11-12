package com.aurora.travlogue.core.domain.service

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.auth.AuthState
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
 */
class SyncService(
    private val tripSyncRepository: TripSyncRepository,
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
            // Sync trips
            _syncState.value = SyncState.Syncing(progress = 0.2f, message = "Syncing trips...")
            val result = tripSyncRepository.syncTripsFromRemote()

            result.fold(
                onSuccess = {
                    _lastSyncTime.value = System.currentTimeMillis()
                    _syncState.value = SyncState.Success(
                        message = "Sync completed successfully"
                    )
                    Result.success(Unit)
                },
                onFailure = { error ->
                    _syncState.value = SyncState.Error(
                        message = error.message ?: "Sync failed"
                    )
                    Result.failure(error)
                }
            )
        } catch (e: Exception) {
            _syncState.value = SyncState.Error(
                message = e.message ?: "Sync failed with exception"
            )
            Result.failure(e)
        }
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
