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

/**
 * Mock trip data for previews and testing
 */
object TripMockData {

    val spainAdventure = Trip(
        id = "1",
        name = "Spain Adventure 2025",
        originCity = "New York",
        dateType = DateType.FIXED,
        startDate = "2025-11-15",
        endDate = "2025-11-25",
        flexibleMonth = null,
        flexibleDuration = null
    )

    val japanCherryBlossom = Trip(
        id = "2",
        name = "Japan Cherry Blossom",
        originCity = "San Francisco",
        dateType = DateType.FLEXIBLE,
        startDate = null,
        endDate = null,
        flexibleMonth = "April 2026",
        flexibleDuration = 10
    )

    val icelandRoadTrip = Trip(
        id = "3",
        name = "Iceland Road Trip",
        originCity = "Boston",
        dateType = DateType.FIXED,
        startDate = "2025-08-01",
        endDate = "2025-08-14",
        flexibleMonth = null,
        flexibleDuration = null
    )

    val europeBackpacking = Trip(
        id = "4",
        name = "European Backpacking",
        originCity = "Chicago",
        dateType = DateType.FLEXIBLE,
        startDate = null,
        endDate = null,
        flexibleMonth = "Summer 2025",
        flexibleDuration = null
    )

    val weekendGetaway = Trip(
        id = "5",
        name = "Weekend Getaway",
        originCity = "Chicago",
        dateType = DateType.FIXED,
        startDate = "2025-12-20",
        endDate = "2025-12-22",
        flexibleMonth = null,
        flexibleDuration = null
    )

    /**
     * Sample list of trips for previews
     */
    val sampleTrips = listOf(
        spainAdventure,
        japanCherryBlossom,
        icelandRoadTrip
    )

    /**
     * All available mock trips
     */
    val allMockTrips = listOf(
        spainAdventure,
        japanCherryBlossom,
        icelandRoadTrip,
        europeBackpacking,
        weekendGetaway
    )
}
