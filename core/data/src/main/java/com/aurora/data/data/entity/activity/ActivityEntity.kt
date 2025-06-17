package com.aurora.data.data.entity.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Name of the table for activities.
 */
const val ACTIVITIES_TABLE_NAME = "activities"

/**
 * Represents an activity within a day plan.
 *
 * @property id Unique identifier for the activity.
 * @property dayId Foreign key referencing the id of the DayPlan this activity belongs to.
 * @property name Name of the activity.
 * @property time Time of the activity in milliseconds since epoch.
 * @property durationInMins Duration of the activity in minutes.
 * @property location Location of the activity.
 * @property latitude Latitude of the activity's location.
 * @property longitude Longitude of the activity's location.
 * @property notes Additional notes for the activity.
 */
@Entity(tableName = ACTIVITIES_TABLE_NAME)
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "day_id") val dayId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "time") val time: Long?,
    @ColumnInfo(name = "duration_in_mins") val durationInMins: Int?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "notes") val notes: String?
)
