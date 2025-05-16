package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aurora.data.data.model.TransitMode

const val DAY_PLANS_TABLE_NAME = "day_plans"

@Entity(
    tableName = DAY_PLANS_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = TripEntity::class,
            parentColumns = ["id"],
            childColumns = ["trip_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DayPlanEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "trip_id", index = true) val tripId: Long,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Long? = null,
    @ColumnInfo(name = "departure_time") val departureTime: Long? = null,
    @ColumnInfo(name = "transit_mode") val transitMode: TransitMode? = null,
    @ColumnInfo(name = "transit_details") val transitDetails: String? = null,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "notes") val notes: String? = null
)
