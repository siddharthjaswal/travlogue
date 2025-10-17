package com.aurora.travlogue.feature.tripdetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.ActivityType
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Location
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

    // ==================== Dialog Management ====================

    fun showAddActivityDialog(date: String? = null, locationId: String? = null) {
        _uiState.update {
            it.copy(
                showAddActivityDialog = true,
                preselectedDate = date,
                preselectedLocationId = locationId
            )
        }
    }

    fun hideAddActivityDialog() {
        _uiState.update {
            it.copy(
                showAddActivityDialog = false,
                preselectedDate = null,
                preselectedLocationId = null
            )
        }
    }

    fun showAddLocationDialog() {
        _uiState.update { it.copy(showAddLocationDialog = true) }
    }

    fun hideAddLocationDialog() {
        _uiState.update { it.copy(showAddLocationDialog = false) }
    }

    fun showAddBookingDialog() {
        _uiState.update { it.copy(showAddBookingDialog = true) }
    }

    fun hideAddBookingDialog() {
        _uiState.update { it.copy(showAddBookingDialog = false) }
    }

    // ==================== Activity CRUD ====================

    fun addActivity(
        locationId: String,
        title: String,
        description: String?,
        date: String?,
        timeSlot: TimeSlot?,
        type: ActivityType
    ) {
        viewModelScope.launch {
            try {
                val activity = Activity(
                    locationId = locationId,
                    title = title,
                    description = description,
                    date = date,
                    timeSlot = timeSlot,
                    type = type
                )
                tripRepository.insertActivity(activity)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Activity added successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to add activity"))
            }
        }
    }

    fun updateActivity(activity: Activity) {
        viewModelScope.launch {
            try {
                tripRepository.updateActivity(activity)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Activity updated successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to update activity"))
            }
        }
    }

    fun deleteActivity(activityId: String) {
        viewModelScope.launch {
            try {
                tripRepository.deleteActivityById(activityId)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Activity deleted successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to delete activity"))
            }
        }
    }

    // ==================== Location CRUD ====================

    fun addLocation(
        name: String,
        country: String,
        date: String?,
        order: Int
    ) {
        viewModelScope.launch {
            try {
                val location = Location(
                    tripId = tripId,
                    name = name,
                    country = country,
                    date = date,
                    latitude = null,
                    longitude = null,
                    order = order
                )
                tripRepository.insertLocation(location)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Location added successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to add location"))
            }
        }
    }

    fun updateLocation(location: Location) {
        viewModelScope.launch {
            try {
                tripRepository.updateLocation(location)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Location updated successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to update location"))
            }
        }
    }

    fun deleteLocation(location: Location) {
        viewModelScope.launch {
            try {
                tripRepository.deleteLocation(location)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Location deleted successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to delete location"))
            }
        }
    }

    // ==================== Booking CRUD ====================

    fun addBooking(
        type: BookingType,
        confirmationNumber: String?,
        provider: String,
        startDateTime: String,
        endDateTime: String?,
        timezone: String,
        fromLocation: String?,
        toLocation: String?,
        price: Double?,
        currency: String?,
        notes: String?
    ) {
        viewModelScope.launch {
            try {
                val booking = Booking(
                    tripId = tripId,
                    type = type,
                    confirmationNumber = confirmationNumber,
                    provider = provider,
                    startDateTime = startDateTime,
                    endDateTime = endDateTime,
                    timezone = timezone,
                    fromLocation = fromLocation,
                    toLocation = toLocation,
                    price = price,
                    currency = currency,
                    notes = notes,
                    imageUri = null
                )
                tripRepository.insertBooking(booking)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Booking added successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to add booking"))
            }
        }
    }

    fun updateBooking(booking: Booking) {
        viewModelScope.launch {
            try {
                tripRepository.updateBooking(booking)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Booking updated successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to update booking"))
            }
        }
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch {
            try {
                tripRepository.deleteBooking(booking)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Booking deleted successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to delete booking"))
            }
        }
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
