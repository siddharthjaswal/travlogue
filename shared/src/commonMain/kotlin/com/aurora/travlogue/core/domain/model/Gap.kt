package com.aurora.travlogue.core.domain.model

/**
 * Gap domain model - KMP compatible version
 */
data class Gap(
    val id: String = generateUUID(),
    val tripId: String,
    val gapType: GapType,
    val fromLocationId: String,
    val toLocationId: String,
    val fromDate: String, // ISO format: yyyy-MM-dd
    val toDate: String, // ISO format: yyyy-MM-dd
    val isResolved: Boolean = false
)

enum class GapType {
    MISSING_TRANSIT,
    UNPLANNED_DAY,
    TIME_CONFLICT
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
