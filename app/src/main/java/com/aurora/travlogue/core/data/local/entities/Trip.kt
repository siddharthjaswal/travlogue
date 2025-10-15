package com.aurora.travlogue.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val originCity: String,
    val dateType: DateType,
    val startDate: String?, // ISO format: yyyy-MM-dd
    val endDate: String?, // ISO format: yyyy-MM-dd
    val flexibleMonth: String?, // e.g., "November 2025"
    val flexibleDuration: Int?, // days
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class DateType {
    FIXED,
    FLEXIBLE
}
