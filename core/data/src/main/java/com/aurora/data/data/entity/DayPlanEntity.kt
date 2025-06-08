package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aurora.data.data.entity.trip.TripEntity
import com.aurora.data.data.model.TransitMode

/**
 * Represents the database table name for day plans.
 */
const val DAY_PLANS_TABLE_NAME = "day_plans"

/**
 * Represents a single day's plan within a trip.
 * This entity is stored in the [DAY_PLANS_TABLE_NAME] table.
 * It has a foreign key relationship with the [com.aurora.data.data.entity.trip.TripEntity] table.
 *
 * @property id Unique identifier for the day plan, auto-generated.
 * @property tripId Identifier of the [com.aurora.data.data.entity.trip.TripEntity] this day plan belongs to. Indexed for faster queries.
 * @property date The specific date of this plan, stored as a Long (e.g., timestamp).
 * @property arrivalTime Optional arrival time for the planned activity/place, stored as a Long.
 * @property departureTime Optional departure time from the planned activity/place, stored as a Long.
 * @property transitMode Optional mode of transit to reach the place for this day plan.
 * @property transitDetails Optional details about the transit (e.g., flight number, train details).
 * @property place The name or description of the place to visit or activity planned.
 * @property notes Optional additional notes or details for this day plan.
 */
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

