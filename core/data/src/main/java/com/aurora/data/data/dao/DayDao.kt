package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.DAY_TABLE_NAME
import com.aurora.data.data.entity.DayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(dayPlan: DayEntity): Long

    @Update
    suspend fun update(dayPlan: DayEntity)

    @Delete
    suspend fun delete(dayPlan: DayEntity)

    @Query("SELECT * FROM $DAY_TABLE_NAME WHERE id = :id")
    fun getById(id: Long): Flow<DayEntity?>

    @Query("SELECT * FROM $DAY_TABLE_NAME WHERE trip_id = :tripId ORDER BY date ASC")
    fun getDayPlansForTrip(tripId: Long): Flow<List<DayEntity>>

    @Query("SELECT * FROM $DAY_TABLE_NAME ORDER BY date ASC")
    fun getAll(): Flow<List<DayEntity>>
}