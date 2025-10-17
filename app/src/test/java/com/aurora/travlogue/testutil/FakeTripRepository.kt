package com.aurora.travlogue.testutil

import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * Fake implementation of TripRepository for testing.
 *
 * This implementation uses in-memory collections instead of a database,
 * allowing for fast, isolated tests without Room dependencies.
 *
 * Matches the actual TripRepository interface with String IDs (UUIDs).
 *
 * Usage:
 * ```
 * val fakeRepository = FakeTripRepository()
 * fakeRepository.addTrips(listOf(trip1, trip2))
 * val trip = fakeRepository.getTripById(tripId).first()
 * ```
 */
class FakeTripRepository {

    // In-memory storage
    private val trips = MutableStateFlow<List<Trip>>(emptyList())
    private val locations = MutableStateFlow<List<Location>>(emptyList())
    private val activities = MutableStateFlow<List<Activity>>(emptyList())
    private val bookings = MutableStateFlow<List<Booking>>(emptyList())

    // Error simulation flags
    var shouldThrowError = false
    var errorMessage = "Test error"

    // ==================== Flow Queries ====================

    val allTrips: Flow<List<Trip>> = trips

    fun getTripById(tripId: String): Flow<Trip?> {
        return trips.map { list -> list.find { it.id == tripId } }
    }

    fun getLocationsForTrip(tripId: String): Flow<List<Location>> {
        return locations.map { list ->
            list.filter { it.tripId == tripId }
                .sortedBy { it.order }
        }
    }

    fun getActivitiesForTrip(tripId: String): Flow<List<Activity>> {
        return activities.map { list ->
            list.filter {
                // Activities are linked via locationId, need to find via locations
                val locationIds = locations.value.filter { it.tripId == tripId }.map { it.id }
                it.locationId in locationIds
            }
        }
    }

    fun getActivitiesForLocation(locationId: String): Flow<List<Activity>> {
        return activities.map { list ->
            list.filter { it.locationId == locationId }
        }
    }

    fun getBookingsForTrip(tripId: String): Flow<List<Booking>> {
        return bookings.map { list ->
            list.filter { it.tripId == tripId }
        }
    }

    // ==================== Suspend Queries ====================

    suspend fun getTripByIdSync(tripId: String): Trip? {
        throwIfErrorEnabled()
        return trips.value.find { it.id == tripId }
    }

    suspend fun getLocationsForTripSync(tripId: String): List<Location> {
        throwIfErrorEnabled()
        return locations.value
            .filter { it.tripId == tripId }
            .sortedBy { it.order }
    }

    suspend fun getActivitiesForTripSync(tripId: String): List<Activity> {
        throwIfErrorEnabled()
        val locationIds = locations.value.filter { it.tripId == tripId }.map { it.id }
        return activities.value.filter { it.locationId in locationIds }
    }

    suspend fun getBookingsForTripSync(tripId: String): List<Booking> {
        throwIfErrorEnabled()
        return bookings.value.filter { it.tripId == tripId }
    }

    // ==================== Trip Mutations ====================

    suspend fun insertTrip(trip: Trip) {
        throwIfErrorEnabled()
        trips.value = trips.value + trip
    }

    suspend fun updateTrip(trip: Trip) {
        throwIfErrorEnabled()
        trips.value = trips.value.map { if (it.id == trip.id) trip else it }
    }

    suspend fun deleteTrip(trip: Trip) {
        throwIfErrorEnabled()
        trips.value = trips.value.filter { it.id != trip.id }
        // Cascade delete
        locations.value = locations.value.filter { it.tripId != trip.id }
        val locationIds = locations.value.filter { it.tripId == trip.id }.map { it.id }
        activities.value = activities.value.filter { it.locationId !in locationIds }
        bookings.value = bookings.value.filter { it.tripId != trip.id }
    }

    suspend fun deleteTripById(tripId: String) {
        throwIfErrorEnabled()
        trips.value = trips.value.filter { it.id != tripId }
        // Cascade delete
        val locationIds = locations.value.filter { it.tripId == tripId }.map { it.id }
        locations.value = locations.value.filter { it.tripId != tripId }
        activities.value = activities.value.filter { it.locationId !in locationIds }
        bookings.value = bookings.value.filter { it.tripId != tripId }
    }

    // ==================== Location Mutations ====================

    suspend fun insertLocation(location: Location) {
        throwIfErrorEnabled()
        locations.value = locations.value + location
    }

    suspend fun updateLocation(location: Location) {
        throwIfErrorEnabled()
        locations.value = locations.value.map { if (it.id == location.id) location else it }
    }

    suspend fun deleteLocation(location: Location) {
        throwIfErrorEnabled()
        locations.value = locations.value.filter { it.id != location.id }
        // Cascade delete activities
        activities.value = activities.value.filter { it.locationId != location.id }
    }

    // ==================== Activity Mutations ====================

    suspend fun insertActivity(activity: Activity) {
        throwIfErrorEnabled()
        activities.value = activities.value + activity
    }

    suspend fun updateActivity(activity: Activity) {
        throwIfErrorEnabled()
        activities.value = activities.value.map { if (it.id == activity.id) activity else it }
    }

    suspend fun deleteActivity(activity: Activity) {
        throwIfErrorEnabled()
        activities.value = activities.value.filter { it.id != activity.id }
    }

    suspend fun deleteActivityById(activityId: String) {
        throwIfErrorEnabled()
        activities.value = activities.value.filter { it.id != activityId }
    }

    // ==================== Booking Mutations ====================

    suspend fun insertBooking(booking: Booking) {
        throwIfErrorEnabled()
        bookings.value = bookings.value + booking
    }

    suspend fun updateBooking(booking: Booking) {
        throwIfErrorEnabled()
        bookings.value = bookings.value.map { if (it.id == booking.id) booking else it }
    }

    suspend fun deleteBooking(booking: Booking) {
        throwIfErrorEnabled()
        bookings.value = bookings.value.filter { it.id != booking.id }
    }

    // ==================== Test Utilities ====================

    /**
     * Add multiple trips at once (for test setup)
     */
    fun addTrips(tripList: List<Trip>) {
        trips.value = trips.value + tripList
    }

    /**
     * Add multiple locations at once (for test setup)
     */
    fun addLocations(locationList: List<Location>) {
        locations.value = locations.value + locationList
    }

    /**
     * Add multiple activities at once (for test setup)
     */
    fun addActivities(activityList: List<Activity>) {
        activities.value = activities.value + activityList
    }

    /**
     * Add multiple bookings at once (for test setup)
     */
    fun addBookings(bookingList: List<Booking>) {
        bookings.value = bookings.value + bookingList
    }

    /**
     * Clear all data (for test cleanup)
     */
    fun clearAll() {
        trips.value = emptyList()
        locations.value = emptyList()
        activities.value = emptyList()
        bookings.value = emptyList()
        shouldThrowError = false
    }

    /**
     * Get current snapshot of all trips (for assertions)
     */
    fun getAllTripsSnapshot(): List<Trip> = trips.value

    /**
     * Get current snapshot of all locations (for assertions)
     */
    fun getAllLocationsSnapshot(): List<Location> = locations.value

    /**
     * Get current snapshot of all activities (for assertions)
     */
    fun getAllActivitiesSnapshot(): List<Activity> = activities.value

    /**
     * Get current snapshot of all bookings (for assertions)
     */
    fun getAllBookingsSnapshot(): List<Booking> = bookings.value

    /**
     * Simulate an error for testing error handling
     */
    private fun throwIfErrorEnabled() {
        if (shouldThrowError) {
            throw Exception(errorMessage)
        }
    }
}
