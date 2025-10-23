package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.dao.ActivityDao
import com.aurora.travlogue.core.data.local.dao.BookingDao
import com.aurora.travlogue.core.data.local.dao.GapDao
import com.aurora.travlogue.core.data.local.dao.LocationDao
import com.aurora.travlogue.core.data.local.dao.TripDao
import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val tripDao: TripDao,
    private val locationDao: LocationDao,
    private val activityDao: ActivityDao,
    private val bookingDao: BookingDao,
    private val gapDao: GapDao
) {

    val allTrips: Flow<List<Trip>> = tripDao.getAllTrips()

    // Trip queries
    fun getTripById(tripId: String): Flow<Trip?> {
        return tripDao.getTripByIdFlow(tripId)
    }

    suspend fun getTripByIdSync(tripId: String): Trip? {
        return tripDao.getTripById(tripId)
    }

    // Location queries
    fun getLocationsForTrip(tripId: String): Flow<List<Location>> {
        return locationDao.getLocationsByTripId(tripId)
    }

    suspend fun getLocationsForTripSync(tripId: String): List<Location> {
        return locationDao.getLocationsByTripIdSync(tripId)
    }

    // Activity queries
    fun getActivitiesForTrip(tripId: String): Flow<List<Activity>> {
        return activityDao.getActivitiesByTripId(tripId)
    }

    suspend fun getActivitiesForTripSync(tripId: String): List<Activity> {
        return activityDao.getActivitiesByTripIdSync(tripId)
    }

    fun getActivitiesForLocation(locationId: String): Flow<List<Activity>> {
        return activityDao.getActivitiesByLocationId(locationId)
    }

    // Booking queries
    fun getBookingsForTrip(tripId: String): Flow<List<Booking>> {
        return bookingDao.getBookingsByTripId(tripId)
    }

    suspend fun getBookingsForTripSync(tripId: String): List<Booking> {
        return bookingDao.getBookingsByTripIdSync(tripId)
    }

    // Trip mutations
    suspend fun insertTrip(trip: Trip) {
        tripDao.insertTrip(trip)
    }

    suspend fun updateTrip(trip: Trip) {
        tripDao.updateTrip(trip)
    }

    suspend fun deleteTrip(trip: Trip) {
        tripDao.deleteTrip(trip)
    }

    suspend fun deleteTripById(tripId: String) {
        tripDao.deleteTripById(tripId)
    }

    // Location mutations
    suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location)
    }

    suspend fun deleteLocation(location: Location) {
        locationDao.deleteLocation(location)
    }

    // Activity mutations
    suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity)
    }

    suspend fun updateActivity(activity: Activity) {
        activityDao.updateActivity(activity)
    }

    suspend fun deleteActivity(activity: Activity) {
        activityDao.deleteActivity(activity)
    }

    suspend fun deleteActivityById(activityId: String) {
        activityDao.deleteActivityById(activityId)
    }

    // Booking mutations
    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }

    suspend fun updateBooking(booking: Booking) {
        bookingDao.updateBooking(booking)
    }

    suspend fun deleteBooking(booking: Booking) {
        bookingDao.deleteBooking(booking)
    }

    // Gap queries
    fun getGapsForTrip(tripId: String): Flow<List<Gap>> {
        return gapDao.getGapsByTripId(tripId)
    }

    fun getUnresolvedGapsForTrip(tripId: String): Flow<List<Gap>> {
        return gapDao.getUnresolvedGapsByTripId(tripId)
    }

    suspend fun getGapsForTripSync(tripId: String): List<Gap> {
        return gapDao.getGapsByTripIdSync(tripId)
    }

    // Gap mutations
    suspend fun insertGap(gap: Gap) {
        gapDao.insertGap(gap)
    }

    suspend fun insertGaps(gaps: List<Gap>) {
        gapDao.insertGaps(gaps)
    }

    suspend fun updateGap(gap: Gap) {
        gapDao.updateGap(gap)
    }

    suspend fun deleteGap(gap: Gap) {
        gapDao.deleteGap(gap)
    }

    suspend fun markGapAsResolved(gapId: String) {
        gapDao.markGapAsResolved(gapId)
    }

    suspend fun deleteAllGapsForTrip(tripId: String) {
        gapDao.deleteGapsByTripId(tripId)
    }
}
