package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "transit_options",
    foreignKeys = [
        ForeignKey(
            entity = Gap::class,
            parentColumns = ["id"],
            childColumns = ["gapId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["gapId"])]
)
data class TransitOption(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val gapId: String,
    val mode: TransitMode,
    val provider: String?,
    val duration: Int, // minutes
    val price: Double?,
    val currency: String?,
    val departureTime: String?,
    val arrivalTime: String?,
    val fetchedAt: Long = System.currentTimeMillis()
)

enum class TransitMode {
    FLIGHT,
    TRAIN,
    BUS,
    CAR,
    FERRY
}
