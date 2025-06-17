package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.day.DAY_TABLE_NAME
import com.aurora.data.data.entity.day.DayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(dayEntity: DayEntity): Long

    @Update
    suspend fun update(dayEntity: DayEntity)

    @Delete
    suspend fun delete(dayEntity: DayEntity)

    @Query("SELECT * FROM $DAY_TABLE_NAME WHERE id = :id")
    suspend fun getDayById(id: Long): DayEntity?

    @Query("SELECT * FROM $DAY_TABLE_NAME WHERE trip_id = :tripId ORDER BY date ASC")
    fun getDaysForTrip(tripId: Long): Flow<List<DayEntity>>

    @Query("SELECT * FROM $DAY_TABLE_NAME ORDER BY date ASC")
    fun getAll(): Flow<List<DayEntity>>
}