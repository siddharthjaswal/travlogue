package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    /**
     * Foreign key referencing the id of the Plan this activity belongs to.
     */
    @ColumnInfo(name = "plan_id") val planId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "time") val time: Long?,
    @ColumnInfo(name = "duration_in_mins") val durationInMins: Int?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "notes") val notes: String?
)