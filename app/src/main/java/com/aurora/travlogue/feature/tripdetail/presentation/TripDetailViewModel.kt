package com.aurora.travlogue.feature.tripdetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(
    private val tripRepository: TripRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tripId: String = checkNotNull(savedStateHandle["tripId"])

    // UI State
    private val _uiState = MutableStateFlow(TripDetailUiState(isLoading = true))
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()

    // UI Events
    private val _uiEvents = MutableSharedFlow<TripDetailUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    init {
        loadTripDetails()
    }

    // ==================== Public API ====================

    fun onTabSelected(tab: TripDetailTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun onDayClicked(date: String) {
        _uiState.update { state ->
            val expandedDays = if (date in state.expandedDays) {
                state.expandedDays - date
            } else {
                state.expandedDays + date
            }
            state.copy(expandedDays = expandedDays)
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _uiEvents.emit(TripDetailUiEvent.NavigateBack)
        }
    }

    fun onEditTrip() {
        viewModelScope.launch {
            _uiEvents.emit(TripDetailUiEvent.NavigateToEditTrip(tripId))
        }
    }

    fun retryLoading() {
        loadTripDetails()
    }

    // ==================== Private Methods ====================

    private fun loadTripDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                tripRepository.getTripById(tripId).collect { trip ->
                    if (trip != null) {
                        // Load locations
                        tripRepository.getLocationsForTrip(tripId).collect { locations ->
                            // Load activities
                            tripRepository.getActivitiesForTrip(tripId).collect { activities ->
                                // Load bookings
                                tripRepository.getBookingsForTrip(tripId).collect { bookings ->
                                    // Generate day schedules
                                    val daySchedules = generateDaySchedules(trip, locations, activities)

                                    _uiState.update {
                                        it.copy(
                                            trip = trip,
                                            locations = locations.sortedBy { it.order },
                                            daySchedules = daySchedules,
                                            bookings = bookings.sortedBy { it.startDateTime },
                                            isLoading = false,
                                            error = null
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = "Trip not found"
                            )
                        }
                        _uiEvents.emit(TripDetailUiEvent.ShowError("Trip not found"))
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load trip details"
                    )
                }
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to load trip details"))
            }
        }
    }

    private fun generateDaySchedules(
        trip: com.aurora.travlogue.core.data.local.entities.Trip,
        locations: List<com.aurora.travlogue.core.data.local.entities.Location>,
        activities: List<com.aurora.travlogue.core.data.local.entities.Activity>
    ): List<DaySchedule> {
        // For fixed dates, generate day-by-day schedule
        if (trip.dateType == DateType.FIXED && trip.startDate != null && trip.endDate != null) {
            val startDate = LocalDate.parse(trip.startDate)
            val endDate = LocalDate.parse(trip.endDate)
            val dayCount = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1

            return (0 until dayCount).map { dayIndex ->
                val currentDate = startDate.plusDays(dayIndex.toLong())
                val dateString = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE)

                // Find location for this date
                val location = locations.find { it.date == dateString }

                // Find activities for this date
                val dayActivities = activities.filter { it.date == dateString }

                // Group activities by time slot
                val activitiesByTimeSlot = dayActivities
                    .groupBy { it.timeSlot ?: TimeSlot.FULL_DAY }
                    .toSortedMap(compareBy { timeSlot ->
                        when (timeSlot) {
                            TimeSlot.MORNING -> 0
                            TimeSlot.AFTERNOON -> 1
                            TimeSlot.EVENING -> 2
                            TimeSlot.FULL_DAY -> 3
                        }
                    })

                DaySchedule(
                    date = dateString,
                    dayNumber = dayIndex + 1,
                    location = location,
                    activitiesByTimeSlot = activitiesByTimeSlot,
                    dayNotes = null // TODO: Add day notes support
                )
            }
        } else {
            // For flexible dates, show locations without specific dates
            return locations.mapIndexed { index, location ->
                val locationActivities = activities.filter { it.locationId == location.id }
                val activitiesByTimeSlot = locationActivities
                    .groupBy { it.timeSlot ?: TimeSlot.FULL_DAY }

                DaySchedule(
                    date = location.date ?: "flexible-${location.id}",
                    dayNumber = index + 1,
                    location = location,
                    activitiesByTimeSlot = activitiesByTimeSlot,
                    dayNotes = null
                )
            }
        }
    }
}
