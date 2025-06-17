package com.aurora.data.data.entity.day

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aurora.data.data.entity.trip.TripEntity
import com.aurora.data.data.model.TransitMode
import com.google.firebase.ai.type.Schema

/**
 * Represents the database table name for day plans.
 */
const val DAY_TABLE_NAME = "day_table"

/**
 * Represents a single day's plan within a trip.
 * This entity is stored in the [DAY_TABLE_NAME] table.
 * It has a foreign key relationship with the [TripEntity] table.
 *
 * @property id Unique identifier for the day plan, auto-generated.
 * @property tripId Identifier of the [TripEntity] this day plan belongs to. Indexed for faster queries.
 * @property date The specific date of this plan, stored as a Long (e.g., timestamp).
 * @property arrivalTime Optional arrival time for the planned activity/place, stored as a Long.
 * @property departureTime Optional departure time from the planned activity/place, stored as a Long.
 * @property transitMode Optional mode of transit to reach the place for this day plan.
 * @property transitDetails Optional details about the transit (e.g., flight number, train details).
 * @property place The name or description of the place to visit or activity planned.
 * @property notes Optional additional notes or details for this day plan.
 */
@Entity(
    tableName = DAY_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = TripEntity::class,
            parentColumns = ["id"],
            childColumns = ["trip_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "trip_id", index = true) val tripId: Long,
    @ColumnInfo(name = "day") val day: Int,
    @ColumnInfo(name = "date") val date: Long?,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Long? = null,
    @ColumnInfo(name = "departure_time") val departureTime: Long? = null,
    @ColumnInfo(name = "transit_mode") val transitMode: TransitMode? = null,
    @ColumnInfo(name = "transit_details") val transitDetails: String? = null,
    @ColumnInfo(name = "place") val place: String? = null,
    @ColumnInfo(name = "notes") val notes: String? = null
)

val dayJsonSchema: Schema = Schema.obj(
    properties = mapOf(
        "date" to Schema.integer(
            description = "The specific date of this plan as a Unix timestamp in milliseconds. Use if start and end date is mentioned in the Trip Data"
        ),
        "day" to Schema.integer(
            description = "The specific day of this plan as a string: Ex: Day 1, Day 2, Day 3.."
        ),
        "place" to Schema.string(
            description = "The name or description of the place to visit or activity planned for the day. Required."
        ),
        "notes" to Schema.string(
            description = "Optional additional notes, tips, reservation details, or reminders for this day plan."
        )
    ),
    optionalProperties = listOf(
        "date",
        "arrivalTime",
        "departureTime",
        "transitMode",
        "transitDetails",
        "notes"
    )
)

