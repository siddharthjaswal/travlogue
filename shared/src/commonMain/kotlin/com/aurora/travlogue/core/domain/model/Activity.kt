package com.aurora.travlogue.core.domain.model

/**
 * Activity domain model - KMP compatible version
 */
data class Activity(
    val id: String = generateUUID(),
    val locationId: String,
    val title: String,
    val description: String?,
    val date: String?, // ISO format: yyyy-MM-dd
    val timeSlot: TimeSlot?,
    val type: ActivityType,
    val startTime: String? = null, // Optional: ISO format with time: HH:mm or ISO_LOCAL_TIME
    val endTime: String? = null // Optional: ISO format with time: HH:mm or ISO_LOCAL_TIME
)

enum class TimeSlot {
    MORNING,
    AFTERNOON,
    EVENING,
    FULL_DAY
}

enum class ActivityType {
    ATTRACTION,
    FOOD,
    BOOKING,
    TRANSIT,
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
