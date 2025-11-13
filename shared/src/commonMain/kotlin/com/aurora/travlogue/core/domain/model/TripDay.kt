package com.aurora.travlogue.core.domain.model

/**
 * TripDay domain model - represents a single day within a trip
 *
 * This bridges the gap between the backend's TripDay concept and the local Location entity.
 * In the backend, Activities and Bookings are associated with TripDays.
 * Locally, we use Locations which roughly correspond to TripDays.
 *
 * Key relationships:
 * - One TripDay ↔ One Location (roughly, location may span multiple days)
 * - TripDay has Many Activities
 * - TripDay has Many Bookings
 */
data class TripDay(
    val id: String = generateUUID(), // Local UUID
    val tripId: String, // Local trip ID
    val date: String, // ISO format: yyyy-MM-dd
    val dayNumber: Int, // Day number in the trip (1-based)
    val dayType: DayType = DayType.SIGHTSEEING,
    val title: String? = null,
    val notes: String? = null,
    val country: String? = null,
    val city: String? = null,
    val transitDetails: List<TransitDetail> = emptyList(),
    val locationId: String? = null, // Link to local Location entity (if exists)
    val createdAt: String? = null,
    val updatedAt: String? = null
)

/**
 * Type of activity for the day
 */
enum class DayType {
    TRANSIT,
    SIGHTSEEING,
    RELAXATION,
    ADVENTURE,
    CULTURAL,
    BUSINESS,
    FREE_DAY,
    MIXED;

    companion object {
        fun fromString(value: String): DayType {
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: SIGHTSEEING
        }
    }
}

/**
 * Transit details for a transit day
 */
data class TransitDetail(
    val type: String? = null, // "flight", "train", "bus", "car", etc.
    val from: String? = null,
    val to: String? = null,
    val departureTime: String? = null, // ISO 8601
    val arrivalTime: String? = null, // ISO 8601
    val provider: String? = null, // Airline, train company, etc.
    val bookingReference: String? = null
)

/**
 * Platform-agnostic UUID generation
 */
private fun generateUUID(): String {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(Regex("[xy]")) { match ->
        val r = kotlin.random.Random.nextInt(16)
        val v = if (match.value == "x") r else (r and 0x3 or 0x8)
        v.toString(16)
    }
}
