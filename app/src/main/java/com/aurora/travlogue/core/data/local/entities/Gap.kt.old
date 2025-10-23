package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "gaps",
    foreignKeys = [
        ForeignKey(
            entity = Trip::class,
            parentColumns = ["id"],
            childColumns = ["tripId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["fromLocationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["toLocationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["tripId"]),
        Index(value = ["fromLocationId"]),
        Index(value = ["toLocationId"])
    ]
)
data class Gap(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val gapType: GapType,
    val fromLocationId: String,
    val toLocationId: String,
    val fromDate: String, // ISO format: yyyy-MM-dd
    val toDate: String, // ISO format: yyyy-MM-dd
    val isResolved: Boolean = false
)

enum class GapType {
    MISSING_TRANSIT,
    UNPLANNED_DAY,
    TIME_CONFLICT
}
