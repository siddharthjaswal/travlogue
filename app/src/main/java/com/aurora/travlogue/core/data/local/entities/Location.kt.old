package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "locations",
    foreignKeys = [
        ForeignKey(
            entity = Trip::class,
            parentColumns = ["id"],
            childColumns = ["tripId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["tripId"])]
)
data class Location(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val name: String,
    val country: String,
    val date: String?, // ISO format: yyyy-MM-dd (deprecated, use arrivalDateTime instead)
    val latitude: Double?,
    val longitude: Double?,
    val order: Int,
    val timezone: String? = null, // IANA timezone: "Asia/Tokyo", "Europe/Paris", etc.
    val arrivalDateTime: String? = null, // ISO 8601 with timezone: "2025-07-01T14:30:00+09:00"
    val departureDateTime: String? = null // ISO 8601 with timezone: "2025-07-05T09:00:00+09:00"
)
