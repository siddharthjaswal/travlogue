package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "activities",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["locationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["locationId"])]
)
data class Activity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val locationId: String,
    val title: String,
    val description: String?,
    val date: String?, // ISO format: yyyy-MM-dd
    val timeSlot: TimeSlot?,
    val type: ActivityType
)

enum class TimeSlot {
    MORNING,
    AFTERNOON,
    EVENING,
    FULL_DAY
}

enum class ActivityType {
    ATTRACTION,
    FOOD,
    BOOKING,
    TRANSIT,
    OTHER
}
