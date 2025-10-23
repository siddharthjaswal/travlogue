package com.aurora.travlogue.core.domain.model

/**
 * Location domain model - KMP compatible version
 */
data class Location(
    val id: String = generateUUID(),
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
