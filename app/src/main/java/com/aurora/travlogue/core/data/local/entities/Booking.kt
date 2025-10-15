package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "bookings",
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
data class Booking(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val type: BookingType,
    val confirmationNumber: String?,
    val provider: String,
    val startDate: String, // ISO format: yyyy-MM-dd'T'HH:mm:ss
    val endDate: String?, // ISO format: yyyy-MM-dd'T'HH:mm:ss
    val fromLocation: String?,
    val toLocation: String?,
    val price: Double?,
    val currency: String?,
    val notes: String?,
    val imageUri: String? // for saved screenshots
)

enum class BookingType {
    FLIGHT,
    HOTEL,
    TRAIN,
    BUS,
    TICKET,
    OTHER
}
