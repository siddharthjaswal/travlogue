package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.trip.TRIPS_TABLE_NAME
import com.aurora.data.data.entity.trip.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trip: TripEntity): Long

    @Update
    suspend fun update(trip: TripEntity): Int

    @Delete
    suspend fun delete(trip: TripEntity)

    @Query("SELECT * FROM $TRIPS_TABLE_NAME WHERE id = :id")
    fun getTripFlowById(id: Long): Flow<TripEntity?>

    @Query("SELECT * FROM $TRIPS_TABLE_NAME WHERE id = :id")
    suspend fun getTripById(id: Long?): TripEntity?

    @Query("SELECT * FROM $TRIPS_TABLE_NAME ORDER BY start_date DESC")
    fun getAll(): Flow<List<TripEntity>>
}
