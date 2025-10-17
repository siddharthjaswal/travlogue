package com.aurora.travlogue.feature.tripdetail.presentation

import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule

/**
 * UI State for Trip Detail Screen
 */
data class TripDetailUiState(
    val trip: Trip? = null,
    val locations: List<Location> = emptyList(),
    val daySchedules: List<DaySchedule> = emptyList(),
    val bookings: List<Booking> = emptyList(),
    val gaps: List<Gap> = emptyList(),
    val selectedTab: TripDetailTab = TripDetailTab.TIMELINE,
    val expandedDays: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null,
    // Add dialogs
    val showAddActivityDialog: Boolean = false,
    val showAddLocationDialog: Boolean = false,
    val showAddBookingDialog: Boolean = false,
    val preselectedDate: String? = null,
    val preselectedLocationId: String? = null,
    // Edit dialogs
    val showEditActivityDialog: Boolean = false,
    val showEditLocationDialog: Boolean = false,
    val showEditBookingDialog: Boolean = false,
    val editingActivity: com.aurora.travlogue.core.data.local.entities.Activity? = null,
    val editingLocation: Location? = null,
    val editingBooking: Booking? = null,
    // Gap detail dialog
    val showGapDetailDialog: Boolean = false,
    val selectedGap: Gap? = null
) {
    val locationCount: Int get() = locations.size
    val activityCount: Int get() = daySchedules.sumOf { it.activitiesByTimeSlot.values.flatten().size }
    val bookingCount: Int get() = bookings.size
    val notesCount: Int get() = daySchedules.count { !it.dayNotes.isNullOrBlank() }
    val unresolvedGapCount: Int get() = gaps.count { !it.isResolved }

    val hasData: Boolean get() = trip != null
    val isEmpty: Boolean get() = locations.isEmpty() && daySchedules.all { !it.hasActivities } && bookings.isEmpty()
}

/**
 * Tabs available in Trip Detail Screen
 */
enum class TripDetailTab {
    TIMELINE,
    LOCATIONS,
    BOOKINGS,
    OVERVIEW
}

/**
 * UI Events for Trip Detail Screen
 * One-time events that trigger actions
 */
sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
    data class ShowError(val message: String) : TripDetailUiEvent
    data class NavigateToEditTrip(val tripId: String) : TripDetailUiEvent
    data class ShowSnackbar(val message: String) : TripDetailUiEvent
}
