package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.activity.ACTIVITIES_TABLE_NAME
import com.aurora.data.data.entity.activity.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activity: ActivityEntity): Long

    @Update
    suspend fun update(activity: ActivityEntity)

    @Delete
    suspend fun delete(activity: ActivityEntity)

    @Query("SELECT * FROM $ACTIVITIES_TABLE_NAME WHERE id = :id")
    fun getById(id: Long): Flow<ActivityEntity?>

    @Query("SELECT * FROM $ACTIVITIES_TABLE_NAME WHERE day_id = :dayId ORDER BY time ASC")
    fun getActivitiesForDay(dayId: Long): Flow<List<ActivityEntity>>

    @Query("SELECT * FROM $ACTIVITIES_TABLE_NAME ORDER BY time ASC")
    fun getAll(): Flow<List<ActivityEntity>>
}
