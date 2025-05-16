package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ACTIVITIES_TABLE_NAME = "activities"

@Entity(tableName = ACTIVITIES_TABLE_NAME)
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    /**
     * Foreign key referencing the id of the DayPlan this activity belongs to.
     */
    @ColumnInfo(name = "day_plan_id") val dayPlanId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "time") val time: Long?,
    @ColumnInfo(name = "duration_in_mins") val durationInMins: Int?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "notes") val notes: String?
)