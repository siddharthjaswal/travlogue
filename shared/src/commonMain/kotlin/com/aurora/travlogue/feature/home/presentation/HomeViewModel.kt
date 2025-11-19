package com.aurora.travlogue.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.repository.TripSyncRepository
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.service.SyncService
import com.aurora.travlogue.core.domain.service.SyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * HomeViewModel - KMP version
 * Manages the home screen state and trip list
 *
 * Updated to use TripSyncRepository for offline-first sync
 */
class HomeViewModel(
    private val tripSyncRepository: TripSyncRepository,
    private val syncService: SyncService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Sync state from SyncService
    val syncState: StateFlow<SyncState> = syncService.syncState

    // All trips as StateFlow for Compose (uses sync repository)
    val allTrips: StateFlow<List<Trip>> by lazy {
        tripSyncRepository.getAllTrips(forceRefresh = false)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    init {
        startSyncMonitoring()
    }

    private fun startSyncMonitoring() {
        // Start monitoring auth state and trigger sync automatically
        syncService.startMonitoring(viewModelScope)

        // Collect sync state changes
        viewModelScope.launch {
            syncState.collect { state ->
                _uiState.update {
                    it.copy(
                        isSyncing = state is SyncState.Syncing,
                        syncProgress = if (state is SyncState.Syncing) state.progress else 0f,
                        syncMessage = when (state) {
                            is SyncState.Syncing -> state.message
                            is SyncState.Success -> state.message
                            is SyncState.Error -> state.message
                            else -> null
                        }
                    )
                }
            }
        }
    }

    /**
     * Manually trigger sync (e.g., pull-to-refresh)
     */
    fun refreshTrips() {
        viewModelScope.launch {
            val result = syncService.syncAll()
            if (result.isFailure) {
                _uiState.update {
                    it.copy(error = result.exceptionOrNull()?.message ?: "Sync failed")
                }
            }
        }
    }

    fun deleteTrip(trip: Trip) {
        viewModelScope.launch {
            try {
                tripSyncRepository.deleteTrip(trip.id)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "Failed to delete trip")
                }
            }
        }
    }

    fun deleteTripById(tripId: String) {
        viewModelScope.launch {
            try {
                tripSyncRepository.deleteTrip(tripId)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "Failed to delete trip")
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

/**
 * UI State for Home screen
 * Note: Trips are managed separately in allTrips StateFlow
 */
data class HomeUiState(
    val isSyncing: Boolean = false,
    val syncProgress: Float = 0f,
    val syncMessage: String? = null,
    val error: String? = null
)
