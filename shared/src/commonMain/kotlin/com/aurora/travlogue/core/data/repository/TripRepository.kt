package com.aurora.travlogue.core.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.aurora.travlogue.core.data.local.TravlogueDb
import com.aurora.travlogue.core.data.local.toDomainModel
import com.aurora.travlogue.core.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository for Trip-related data operations - KMP version using SQLDelight
 */
class TripRepository(private val database: TravlogueDb) {

    // ==================== Trip Queries ====================

    val allTrips: Flow<List<Trip>> = database.tripQueries
        .getAllTrips()
        .asFlow()
        .mapToList(Dispatchers.Default)
        .map { it.map { trip -> trip.toDomainModel() } }

    fun getTripById(tripId: String): Flow<Trip?> {
        return database.tripQueries
            .getTripById(tripId)
            .asFlow()
            .mapToOneOrNull(Dispatchers.Default)
            .map { it?.toDomainModel() }
    }

    suspend fun getTripByIdSync(tripId: String): Trip? {
        return database.tripQueries
            .getTripById(tripId)
            .executeAsOneOrNull()
            ?.toDomainModel()
    }

    // ==================== Location Queries ====================

    fun getLocationsForTrip(tripId: String): Flow<List<Location>> {
        return database.locationQueries
            .getLocationsByTripId(tripId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { location -> location.toDomainModel() } }
    }

    suspend fun getLocationsForTripSync(tripId: String): List<Location> {
        return database.locationQueries
            .getLocationsByTripId(tripId)
            .executeAsList()
            .map { it.toDomainModel() }
    }

    // ==================== Activity Queries ====================

    fun getActivitiesForTrip(tripId: String): Flow<List<Activity>> {
        return database.activityQueries
            .getActivitiesByTripId(tripId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { activity -> activity.toDomainModel() } }
    }

    suspend fun getActivitiesForTripSync(tripId: String): List<Activity> {
        return database.activityQueries
            .getActivitiesByTripId(tripId)
            .executeAsList()
            .map { it.toDomainModel() }
    }

    fun getActivitiesForLocation(locationId: String): Flow<List<Activity>> {
        return database.activityQueries
            .getActivitiesByLocationId(locationId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { activity -> activity.toDomainModel() } }
    }

    // ==================== Booking Queries ====================

    fun getBookingsForTrip(tripId: String): Flow<List<Booking>> {
        return database.bookingQueries
            .getBookingsByTripId(tripId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { booking -> booking.toDomainModel() } }
    }

    suspend fun getBookingsForTripSync(tripId: String): List<Booking> {
        return database.bookingQueries
            .getBookingsByTripId(tripId)
            .executeAsList()
            .map { it.toDomainModel() }
    }

    // ==================== Trip Mutations ====================

    suspend fun insertTrip(trip: Trip) {
        database.tripQueries.insertTrip(
            id = trip.id,
            name = trip.name,
            originCity = trip.originCity,
            dateType = trip.dateType.name,
            startDate = trip.startDate,
            endDate = trip.endDate,
            flexibleMonth = trip.flexibleMonth,
            flexibleDuration = trip.flexibleDuration?.toLong(),
            createdAt = trip.createdAt,
            updatedAt = trip.updatedAt
        )
    }

    suspend fun updateTrip(trip: Trip) {
        database.tripQueries.updateTrip(
            name = trip.name,
            originCity = trip.originCity,
            dateType = trip.dateType.name,
            startDate = trip.startDate,
            endDate = trip.endDate,
            flexibleMonth = trip.flexibleMonth,
            flexibleDuration = trip.flexibleDuration?.toLong(),
            updatedAt = trip.updatedAt,
            id = trip.id
        )
    }

    suspend fun deleteTrip(trip: Trip) {
        database.tripQueries.deleteTrip(trip.id)
    }

    suspend fun deleteTripById(tripId: String) {
        database.tripQueries.deleteTrip(tripId)
    }

    // ==================== Location Mutations ====================

    suspend fun insertLocation(location: Location) {
        database.locationQueries.insertLocation(
            id = location.id,
            tripId = location.tripId,
            name = location.name,
            country = location.country,
            date = location.date,
            latitude = location.latitude,
            longitude = location.longitude,
            order = location.order.toLong(),
            timezone = location.timezone,
            arrivalDateTime = location.arrivalDateTime,
            departureDateTime = location.departureDateTime
        )
    }

    suspend fun updateLocation(location: Location) {
        database.locationQueries.updateLocation(
            name = location.name,
            country = location.country,
            date = location.date,
            latitude = location.latitude,
            longitude = location.longitude,
            order = location.order.toLong(),
            timezone = location.timezone,
            arrivalDateTime = location.arrivalDateTime,
            departureDateTime = location.departureDateTime,
            id = location.id
        )
    }

    suspend fun deleteLocation(location: Location) {
        database.locationQueries.deleteLocation(location.id)
    }

    // ==================== Activity Mutations ====================

    suspend fun insertActivity(activity: Activity) {
        database.activityQueries.insertActivity(
            id = activity.id,
            locationId = activity.locationId,
            title = activity.title,
            description = activity.description,
            date = activity.date,
            timeSlot = activity.timeSlot?.name,
            type = activity.type.name,
            startTime = activity.startTime,
            endTime = activity.endTime
        )
    }

    suspend fun updateActivity(activity: Activity) {
        database.activityQueries.updateActivity(
            title = activity.title,
            description = activity.description,
            date = activity.date,
            timeSlot = activity.timeSlot?.name,
            type = activity.type.name,
            startTime = activity.startTime,
            endTime = activity.endTime,
            id = activity.id
        )
    }

    suspend fun deleteActivity(activity: Activity) {
        database.activityQueries.deleteActivity(activity.id)
    }

    suspend fun deleteActivityById(activityId: String) {
        database.activityQueries.deleteActivity(activityId)
    }

    // ==================== Booking Mutations ====================

    suspend fun insertBooking(booking: Booking) {
        database.bookingQueries.insertBooking(
            id = booking.id,
            tripId = booking.tripId,
            type = booking.type.name,
            confirmationNumber = booking.confirmationNumber,
            provider = booking.provider,
            startDateTime = booking.startDateTime,
            endDateTime = booking.endDateTime,
            timezone = booking.timezone,
            endTimezone = booking.endTimezone,
            fromLocation = booking.fromLocation,
            toLocation = booking.toLocation,
            price = booking.price,
            currency = booking.currency,
            notes = booking.notes,
            imageUri = booking.imageUri
        )
    }

    suspend fun updateBooking(booking: Booking) {
        database.bookingQueries.updateBooking(
            type = booking.type.name,
            confirmationNumber = booking.confirmationNumber,
            provider = booking.provider,
            startDateTime = booking.startDateTime,
            endDateTime = booking.endDateTime,
            timezone = booking.timezone,
            endTimezone = booking.endTimezone,
            fromLocation = booking.fromLocation,
            toLocation = booking.toLocation,
            price = booking.price,
            currency = booking.currency,
            notes = booking.notes,
            imageUri = booking.imageUri,
            id = booking.id
        )
    }

    suspend fun deleteBooking(booking: Booking) {
        database.bookingQueries.deleteBooking(booking.id)
    }

    // ==================== Gap Queries ====================

    fun getGapsForTrip(tripId: String): Flow<List<Gap>> {
        return database.gapQueries
            .getGapsByTripId(tripId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { gap -> gap.toDomainModel() } }
    }

    fun getUnresolvedGapsForTrip(tripId: String): Flow<List<Gap>> {
        return database.gapQueries
            .getUnresolvedGapsByTripId(tripId)
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.map { gap -> gap.toDomainModel() } }
    }

    suspend fun getGapsForTripSync(tripId: String): List<Gap> {
        return database.gapQueries
            .getGapsByTripId(tripId)
            .executeAsList()
            .map { it.toDomainModel() }
    }

    // ==================== Gap Mutations ====================

    suspend fun insertGap(gap: Gap) {
        database.gapQueries.insertGap(
            id = gap.id,
            tripId = gap.tripId,
            gapType = gap.gapType.name,
            fromLocationId = gap.fromLocationId,
            toLocationId = gap.toLocationId,
            fromDate = gap.fromDate,
            toDate = gap.toDate,
            isResolved = if (gap.isResolved) 1L else 0L
        )
    }

    suspend fun insertGaps(gaps: List<Gap>) {
        gaps.forEach { insertGap(it) }
    }

    suspend fun updateGap(gap: Gap) {
        database.gapQueries.updateGap(
            gapType = gap.gapType.name,
            fromLocationId = gap.fromLocationId,
            toLocationId = gap.toLocationId,
            fromDate = gap.fromDate,
            toDate = gap.toDate,
            isResolved = if (gap.isResolved) 1L else 0L,
            id = gap.id
        )
    }

    suspend fun deleteGap(gap: Gap) {
        database.gapQueries.deleteGap(gap.id)
    }

    suspend fun markGapAsResolved(gapId: String) {
        database.gapQueries.markGapAsResolved(gapId)
    }

    suspend fun deleteAllGapsForTrip(tripId: String) {
        database.gapQueries.deleteGapsByTripId(tripId)
    }
}
