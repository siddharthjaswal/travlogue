package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.dao.LocationDao
import com.aurora.travlogue.core.data.local.entities.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val locationDao: LocationDao
) {

    fun getLocationsByTripId(tripId: String): Flow<List<Location>> {
        return locationDao.getLocationsByTripId(tripId)
    }

    suspend fun getLocationsByTripIdSync(tripId: String): List<Location> {
        return locationDao.getLocationsByTripIdSync(tripId)
    }

    suspend fun getLocationById(locationId: String): Location? {
        return locationDao.getLocationById(locationId)
    }

    suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    suspend fun insertLocations(locations: List<Location>) {
        locationDao.insertLocations(locations)
    }

    suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location)
    }

    suspend fun deleteLocation(location: Location) {
        locationDao.deleteLocation(location)
    }

    suspend fun deleteLocationById(locationId: String) {
        locationDao.deleteLocationById(locationId)
    }

    suspend fun deleteLocationsByTripId(tripId: String) {
        locationDao.deleteLocationsByTripId(tripId)
    }
}
