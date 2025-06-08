package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.DAY_PLANS_TABLE_NAME
import com.aurora.data.data.entity.DayPlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayPlanDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(dayPlan: DayPlanEntity): Long

    @Update
    suspend fun update(dayPlan: DayPlanEntity)

    @Delete
    suspend fun delete(dayPlan: DayPlanEntity)

    @Query("SELECT * FROM ${DAY_PLANS_TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): Flow<DayPlanEntity?>

    @Query("SELECT * FROM ${DAY_PLANS_TABLE_NAME} WHERE trip_id = :tripId ORDER BY date ASC")
    fun getDayPlansForTrip(tripId: Long): Flow<List<DayPlanEntity>>

    @Query("SELECT * FROM ${DAY_PLANS_TABLE_NAME} ORDER BY date ASC")
    fun getAll(): Flow<List<DayPlanEntity>>
}