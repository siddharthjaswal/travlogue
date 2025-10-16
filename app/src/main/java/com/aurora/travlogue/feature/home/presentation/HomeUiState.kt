package com.aurora.travlogue.feature.home.presentation

import com.aurora.travlogue.core.data.local.entities.Trip

/**
 * UI State for Home Screen
 *
 * Represents different states the home screen can be in:
 * - Loading: Initial load or refreshing
 * - Empty: No trips exist
 * - Success: Trips loaded successfully
 * - Error: Failed to load trips
 */
sealed interface HomeUiState {

    /**
     * Loading state - showing progress indicator
     */
    data object Loading : HomeUiState

    /**
     * Empty state - no trips created yet
     */
    data object Empty : HomeUiState

    /**
     * Success state - trips loaded and displayed
     */
    data class Success(
        val trips: List<Trip>,
        val isRefreshing: Boolean = false
    ) : HomeUiState

    /**
     * Error state - failed to load trips
     */
    data class Error(
        val message: String,
        val trips: List<Trip> = emptyList() // Cache previous data
    ) : HomeUiState
}

/**
 * UI Events for Home Screen
 *
 * One-time events that trigger UI actions:
 * - Navigation
 * - Snackbar messages
 * - Dialog displays
 */
sealed interface HomeUiEvent {
    data class NavigateToTrip(val tripId: String) : HomeUiEvent
    data class ShowError(val message: String) : HomeUiEvent
    data class ShowSuccess(val message: String) : HomeUiEvent
}
