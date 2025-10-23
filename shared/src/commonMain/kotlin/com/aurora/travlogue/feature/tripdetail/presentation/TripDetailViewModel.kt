package com.aurora.travlogue.feature.tripdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.*
import com.aurora.travlogue.core.domain.service.BookingSyncService
import com.aurora.travlogue.core.domain.service.GapDetectionService
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus

/**
 * TripDetailViewModel - KMP version
 * Manages trip detail screen state and business logic
 */
class TripDetailViewModel(
    private val tripId: String,
    private val tripRepository: TripRepository,
    private val gapDetectionService: GapDetectionService,
    private val bookingSyncService: BookingSyncService
) : ViewModel() {

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

    // Edit Dialogs
    fun showEditActivityDialog(activity: Activity) {
        _uiState.update {
            it.copy(
                showEditActivityDialog = true,
                editingActivity = activity
            )
        }
    }

    fun hideEditActivityDialog() {
        _uiState.update {
            it.copy(
                showEditActivityDialog = false,
                editingActivity = null
            )
        }
    }

    fun showEditLocationDialog(location: Location) {
        _uiState.update {
            it.copy(
                showEditLocationDialog = true,
                editingLocation = location
            )
        }
    }

    fun hideEditLocationDialog() {
        _uiState.update {
            it.copy(
                showEditLocationDialog = false,
                editingLocation = null
            )
        }
    }

    fun showEditBookingDialog(booking: Booking) {
        _uiState.update {
            it.copy(
                showEditBookingDialog = true,
                editingBooking = booking
            )
        }
    }

    fun hideEditBookingDialog() {
        _uiState.update {
            it.copy(
                showEditBookingDialog = false,
                editingBooking = null
            )
        }
    }

    fun showGapDetailDialog(gap: Gap) {
        _uiState.update {
            it.copy(
                showGapDetailDialog = true,
                selectedGap = gap
            )
        }
    }

    fun hideGapDetailDialog() {
        _uiState.update {
            it.copy(
                showGapDetailDialog = false,
                selectedGap = null
            )
        }
    }

    // ==================== Activity CRUD ====================

    fun addActivity(
        locationId: String,
        title: String,
        description: String?,
        date: String?,
        timeSlot: TimeSlot?,
        type: ActivityType,
        startTime: String? = null,
        endTime: String? = null
    ) {
        viewModelScope.launch {
            try {
                val activity = Activity(
                    locationId = locationId,
                    title = title,
                    description = description,
                    date = date,
                    timeSlot = timeSlot,
                    type = type,
                    startTime = startTime,
                    endTime = endTime
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
        timezone: String?,
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
                    order = order,
                    timezone = timezone,
                    arrivalDateTime = null, // Will be set by booking sync logic
                    departureDateTime = null // Will be set by booking sync logic
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
        endTimezone: String?,
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
                    endTimezone = endTimezone,
                    fromLocation = fromLocation,
                    toLocation = toLocation,
                    price = price,
                    currency = currency,
                    notes = notes,
                    imageUri = null
                )
                tripRepository.insertBooking(booking)

                // Sync booking times with location arrival/departure times
                val updatedLocations = bookingSyncService.syncBookingsWithLocations(
                    _uiState.value.locations,
                    _uiState.value.bookings + booking
                )
                updatedLocations.forEach { tripRepository.updateLocation(it) }

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

                // Sync booking times with location arrival/departure times
                val updatedLocations = bookingSyncService.syncBookingsWithLocations(
                    _uiState.value.locations,
                    _uiState.value.bookings
                )
                updatedLocations.forEach { tripRepository.updateLocation(it) }

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

                // Sync booking times with location arrival/departure times
                // This will clear arrival/departure times if no other bookings reference the location
                val updatedLocations = bookingSyncService.syncBookingsWithLocations(
                    _uiState.value.locations,
                    _uiState.value.bookings.filter { it.id != booking.id }
                )
                updatedLocations.forEach { tripRepository.updateLocation(it) }

                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Booking deleted successfully"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to delete booking"))
            }
        }
    }

    // ==================== Gap Management ====================

    fun markGapAsResolved(gapId: String) {
        viewModelScope.launch {
            try {
                tripRepository.markGapAsResolved(gapId)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Gap marked as resolved"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to resolve gap"))
            }
        }
    }

    fun deleteGap(gap: Gap) {
        viewModelScope.launch {
            try {
                tripRepository.deleteGap(gap)
                _uiEvents.emit(TripDetailUiEvent.ShowSnackbar("Gap dismissed"))
            } catch (e: Exception) {
                _uiEvents.emit(TripDetailUiEvent.ShowError(e.message ?: "Failed to dismiss gap"))
            }
        }
    }

    /**
     * Manually trigger gap detection and update database.
     * Called automatically when trip data changes.
     */
    fun detectAndUpdateGaps() {
        viewModelScope.launch {
            try {
                val trip = _uiState.value.trip ?: return@launch
                val locations = _uiState.value.locations
                val bookings = _uiState.value.bookings

                // Detect gaps using the service
                val detectedGaps = gapDetectionService.detectGaps(trip, locations, bookings)

                // Clear old gaps and insert new ones
                tripRepository.deleteAllGapsForTrip(trip.id)
                if (detectedGaps.isNotEmpty()) {
                    tripRepository.insertGaps(detectedGaps)
                }
            } catch (e: Exception) {
                // Silent failure - gap detection is not critical
                e.printStackTrace()
            }
        }
    }

    // ==================== Private Methods ====================

    private fun loadTripDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Combine flows to avoid nested collects and prevent infinite loops
                combine(
                    tripRepository.getTripById(tripId),
                    tripRepository.getLocationsForTrip(tripId),
                    tripRepository.getActivitiesForTrip(tripId),
                    tripRepository.getBookingsForTrip(tripId),
                    tripRepository.getGapsForTrip(tripId)
                ) { trip, locations, activities, bookings, gaps ->
                    TripDetailData(trip, locations, activities, bookings, gaps)
                }.collect { data ->
                    val trip = data.trip
                    if (trip != null) {
                        // Generate day schedules
                        val daySchedules = generateDaySchedules(trip, data.locations, data.activities)

                        _uiState.update {
                            it.copy(
                                trip = trip,
                                locations = data.locations.sortedBy { it.order },
                                daySchedules = daySchedules,
                                bookings = data.bookings.sortedBy { it.startDateTime },
                                gaps = data.gaps.filter { !it.isResolved },
                                isLoading = false,
                                error = null
                            )
                        }

                        // Trigger gap detection when locations or bookings change
                        // but only if no gaps exist (to avoid infinite loop)
                        if (data.gaps.isEmpty() && (data.locations.isNotEmpty() || data.bookings.isNotEmpty())) {
                            detectAndUpdateGaps()
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

    /**
     * Data class to hold combined trip detail data
     */
    private data class TripDetailData(
        val trip: Trip?,
        val locations: List<Location>,
        val activities: List<Activity>,
        val bookings: List<Booking>,
        val gaps: List<Gap>
    )

    private fun generateDaySchedules(
        trip: Trip,
        locations: List<Location>,
        activities: List<Activity>
    ): List<DaySchedule> {
        // For fixed dates, generate day-by-day schedule
        val tripStartDate = trip.startDate
        val tripEndDate = trip.endDate
        if (trip.dateType == DateType.FIXED && tripStartDate != null && tripEndDate != null) {
            val startDate = LocalDate.parse(tripStartDate)
            val endDate = LocalDate.parse(tripEndDate)
            val dayCount = (endDate.toEpochDays() - startDate.toEpochDays()).toInt() + 1

            return (0 until dayCount).map { dayIndex ->
                val currentDate = startDate.plus(dayIndex.toLong(), DateTimeUnit.DAY)
                val dateString = currentDate.toString()

                // Find location for this date
                val location = locations.find { it.date == dateString }

                // Find activities for this date
                val dayActivities = activities.filter { it.date == dateString }

                // Group activities by time slot
                val activitiesByTimeSlot = dayActivities
                    .groupBy { it.timeSlot ?: TimeSlot.FULL_DAY }
                    .toList()
                    .sortedBy { (timeSlot, _) ->
                        when (timeSlot) {
                            TimeSlot.MORNING -> 0
                            TimeSlot.AFTERNOON -> 1
                            TimeSlot.EVENING -> 2
                            TimeSlot.FULL_DAY -> 3
                        }
                    }
                    .toMap()

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
