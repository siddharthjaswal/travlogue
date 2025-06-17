package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.stay.STAYS_TABLE_NAME
import com.aurora.data.data.entity.stay.StayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stay: StayEntity): Long

    @Update
    suspend fun update(stay: StayEntity)

    @Delete
    suspend fun delete(stay: StayEntity)

    @Query("SELECT * FROM $STAYS_TABLE_NAME WHERE id = :id")
    fun getById(id: Long): Flow<StayEntity?>

    @Query("SELECT * FROM $STAYS_TABLE_NAME ORDER BY name ASC")
    fun getAll(): Flow<List<StayEntity>>
}