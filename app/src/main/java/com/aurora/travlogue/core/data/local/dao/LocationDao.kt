package com.aurora.travlogue.core.data.local.dao

import androidx.room.*
import com.aurora.travlogue.core.data.local.entities.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations WHERE tripId = :tripId ORDER BY `order` ASC")
    fun getLocationsByTripId(tripId: String): Flow<List<Location>>

    @Query("SELECT * FROM locations WHERE tripId = :tripId ORDER BY `order` ASC")
    suspend fun getLocationsByTripIdSync(tripId: String): List<Location>

    @Query("SELECT * FROM locations WHERE id = :locationId")
    suspend fun getLocationById(locationId: String): Location?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<Location>)

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("DELETE FROM locations WHERE id = :locationId")
    suspend fun deleteLocationById(locationId: String)

    @Query("DELETE FROM locations WHERE tripId = :tripId")
    suspend fun deleteLocationsByTripId(tripId: String)
}
