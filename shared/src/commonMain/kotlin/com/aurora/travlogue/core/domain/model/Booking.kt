package com.aurora.travlogue.core.domain.model

/**
 * Booking domain model - KMP compatible version
 */
data class Booking(
    val id: String = generateUUID(),
    val tripId: String,
    val type: BookingType,
    val confirmationNumber: String?,
    val provider: String,
    val startDateTime: String, // ISO 8601 with timezone: "2025-11-15T14:30:00+01:00"
    val endDateTime: String?, // ISO 8601 with timezone: "2025-11-15T16:45:00+01:00"
    val timezone: String, // IANA timezone for start: "Europe/Madrid", "America/New_York"
    val endTimezone: String? = null, // IANA timezone for end (if different from start): "America/Los_Angeles"
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
