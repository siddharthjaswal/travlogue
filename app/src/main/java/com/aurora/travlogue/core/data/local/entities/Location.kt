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
    val date: String?, // ISO format: yyyy-MM-dd
    val latitude: Double?,
    val longitude: Double?,
    val order: Int
)
