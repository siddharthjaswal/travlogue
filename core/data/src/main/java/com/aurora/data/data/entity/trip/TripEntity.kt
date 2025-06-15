package com.aurora.data.data.entity.trip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.ai.type.Schema

const val TRIPS_TABLE_NAME = "trips"

@Entity(tableName = TRIPS_TABLE_NAME)
data class TripEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "banner_image_path") val bannerImagePath: String? = null,
    @ColumnInfo(name = "days") val days: Int? = null,
    @ColumnInfo(name = "start_date") val startDate: Long? = null,
    @ColumnInfo(name = "end_date") val endDate: Long? = null,
    @ColumnInfo(name = "last_updated") val lastUpdated: Long
) {
    companion object {
        val tripJsonSchema: Schema = Schema.obj(
            properties = mapOf(
                "name" to Schema.string(
                    description = "The name or destination of the trip.[Feel free to add relevant emojis]"
                ),
                "days" to Schema.integer(
                    description = "The total number of days for the trip. Optional.[Assume if not provided, it's a five-day trip]"
                ),
                "startDate" to Schema.integer(
                    description = "The start date of the trip as a Unix timestamp in milliseconds. Optional."
                ),
                "endDate" to Schema.integer(
                    description = "The end date of the trip as a Unix timestamp in milliseconds. Optional."
                )
            ),
            optionalProperties = listOf("days", "startDate", "endDate")
        )

    }
}
