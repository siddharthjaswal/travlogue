package com.aurora.travlogue.core.domain.model

/**
 * TransitOption domain model - KMP compatible version
 */
data class TransitOption(
    val id: String = generateUUID(),
    val gapId: String,
    val mode: TransitMode,
    val provider: String?,
    val duration: Int, // minutes
    val price: Double?,
    val currency: String?,
    val departureTime: String?,
    val arrivalTime: String?,
    val fetchedAt: Long = currentTimeMillis()
)

enum class TransitMode {
    FLIGHT,
    TRAIN,
    BUS,
    CAR,
    FERRY
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
