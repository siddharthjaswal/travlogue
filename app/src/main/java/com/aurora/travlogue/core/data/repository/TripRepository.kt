package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.dao.TripDao
import com.aurora.travlogue.core.data.local.entities.Trip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val tripDao: TripDao
) {

    val allTrips: Flow<List<Trip>> = tripDao.getAllTrips()

    fun getTripByIdFlow(tripId: String): Flow<Trip?> {
        return tripDao.getTripByIdFlow(tripId)
    }

    suspend fun getTripById(tripId: String): Trip? {
        return tripDao.getTripById(tripId)
    }

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
}
