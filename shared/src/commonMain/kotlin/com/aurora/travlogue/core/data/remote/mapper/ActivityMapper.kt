package com.aurora.travlogue.core.data.remote.mapper

import com.aurora.travlogue.core.data.remote.dto.*
import com.aurora.travlogue.core.domain.model.Activity
import com.aurora.travlogue.core.domain.model.ActivityType as DomainActivityType
import com.aurora.travlogue.core.domain.model.TimeSlot

/**
 * Mappers for converting between Activity DTOs and Domain models
 */

/**
 * Convert ActivityResponseDto from backend to local Activity domain model
 *
 * Note: Backend activity model is much richer than local model.
 * This mapper extracts only the fields currently used in the app.
 */
fun ActivityResponseDto.toDomainModel(locationId: String): Activity {
    return Activity(
        id = id.toString(),
        locationId = locationId,
        title = name,
        description = notes ?: "",
        date = extractDateFromTime(time),
        timeSlot = determineTimeSlot(time),
        type = activityType.toDomainType(),
        startTime = time,
        endTime = time?.let { calculateEndTime(it, duration) }
    )
}

/**
 * Convert ActivityListResponseDto to Activity domain model
 */
fun ActivityListResponseDto.toDomainModel(locationId: String): Activity {
    return Activity(
        id = id.toString(),
        locationId = locationId,
        title = name,
        description = "",
        date = extractDateFromTime(time),
        timeSlot = determineTimeSlot(time),
        type = activityType.toDomainType(),
        startTime = time,
        endTime = time?.let { calculateEndTime(it, duration) }
    )
}

/**
 * Convert Activity domain model to ActivityCreateDto for backend
 */
fun Activity.toCreateDto(
    tripDayId: Int,
    cost: Double? = null,
    currency: String = "USD",
    displayOrder: Int = 0
): ActivityCreateDto {
    return ActivityCreateDto(
        tripDayId = tripDayId,
        name = title,
        activityType = type.toBackendType(),
        time = startTime,
        duration = calculateDuration(startTime, endTime),
        location = null, // Not in local model
        locationAddress = null,
        latitude = null,
        longitude = null,
        cost = cost,
        currency = currency,
        bookingRequired = false,
        confirmationNumber = null,
        bookingUrl = null,
        contactPhone = null,
        contactEmail = null,
        status = ActivityStatus.PLANNED,
        notes = description.ifEmpty { null },
        displayOrder = displayOrder
    )
}

/**
 * Convert Activity domain model to ActivityUpdateDto for backend
 */
fun Activity.toUpdateDto(
    cost: Double? = null,
    currency: String? = null,
    displayOrder: Int? = null
): ActivityUpdateDto {
    return ActivityUpdateDto(
        name = title,
        activityType = type.toBackendType(),
        time = startTime,
        duration = calculateDuration(startTime, endTime),
        location = null,
        locationAddress = null,
        latitude = null,
        longitude = null,
        cost = cost,
        currency = currency,
        bookingRequired = null,
        confirmationNumber = null,
        bookingUrl = null,
        contactPhone = null,
        contactEmail = null,
        status = null,
        notes = description.ifEmpty { null },
        displayOrder = displayOrder
    )
}

/**
 * Map backend ActivityType to domain ActivityType
 */
private fun com.aurora.travlogue.core.data.remote.dto.ActivityType.toDomainType(): DomainActivityType {
    return when (this) {
        com.aurora.travlogue.core.data.remote.dto.ActivityType.SIGHTSEEING -> DomainActivityType.SIGHTSEEING
        com.aurora.travlogue.core.data.remote.dto.ActivityType.DINING -> DomainActivityType.DINING
        com.aurora.travlogue.core.data.remote.dto.ActivityType.ENTERTAINMENT -> DomainActivityType.ENTERTAINMENT
        com.aurora.travlogue.core.data.remote.dto.ActivityType.SHOPPING -> DomainActivityType.SHOPPING
        com.aurora.travlogue.core.data.remote.dto.ActivityType.RELAXATION -> DomainActivityType.RELAXATION
        com.aurora.travlogue.core.data.remote.dto.ActivityType.ADVENTURE,
        com.aurora.travlogue.core.data.remote.dto.ActivityType.CULTURAL,
        com.aurora.travlogue.core.data.remote.dto.ActivityType.TRANSPORTATION,
        com.aurora.travlogue.core.data.remote.dto.ActivityType.ACCOMMODATION,
        com.aurora.travlogue.core.data.remote.dto.ActivityType.BUSINESS,
        com.aurora.travlogue.core.data.remote.dto.ActivityType.OTHER -> DomainActivityType.OTHER
    }
}

/**
 * Map domain ActivityType to backend ActivityType
 */
private fun DomainActivityType.toBackendType(): com.aurora.travlogue.core.data.remote.dto.ActivityType {
    return when (this) {
        DomainActivityType.SIGHTSEEING -> com.aurora.travlogue.core.data.remote.dto.ActivityType.SIGHTSEEING
        DomainActivityType.DINING -> com.aurora.travlogue.core.data.remote.dto.ActivityType.DINING
        DomainActivityType.ENTERTAINMENT -> com.aurora.travlogue.core.data.remote.dto.ActivityType.ENTERTAINMENT
        DomainActivityType.SHOPPING -> com.aurora.travlogue.core.data.remote.dto.ActivityType.SHOPPING
        DomainActivityType.RELAXATION -> com.aurora.travlogue.core.data.remote.dto.ActivityType.RELAXATION
        DomainActivityType.ATTRACTION -> com.aurora.travlogue.core.data.remote.dto.ActivityType.SIGHTSEEING
        DomainActivityType.FOOD -> com.aurora.travlogue.core.data.remote.dto.ActivityType.DINING
        DomainActivityType.BOOKING -> com.aurora.travlogue.core.data.remote.dto.ActivityType.ACCOMMODATION
        DomainActivityType.TRANSIT -> com.aurora.travlogue.core.data.remote.dto.ActivityType.TRANSPORTATION
        DomainActivityType.OTHER -> com.aurora.travlogue.core.data.remote.dto.ActivityType.OTHER
    }
}

/**
 * Determine TimeSlot from time string (HH:mm format)
 */
private fun determineTimeSlot(time: String?): TimeSlot? {
    if (time == null) return null

    val hour = time.split(":").firstOrNull()?.toIntOrNull() ?: return null

    return when (hour) {
        in 0..11 -> TimeSlot.MORNING
        in 12..16 -> TimeSlot.AFTERNOON
        in 17..23 -> TimeSlot.EVENING
        else -> null
    }
}

/**
 * Extract date from time field (if it contains date info)
 * For now, returns null as backend time is just HH:mm
 */
private fun extractDateFromTime(time: String?): String? {
    // Backend time field is just "HH:mm" format
    // Date comes from trip_day relationship
    return null
}

/**
 * Calculate end time given start time and duration (in hours)
 */
private fun calculateEndTime(startTime: String, duration: Double?): String? {
    if (duration == null) return null

    val parts = startTime.split(":")
    if (parts.size != 2) return null

    val startHour = parts[0].toIntOrNull() ?: return null
    val startMinute = parts[1].toIntOrNull() ?: return null

    val totalMinutes = (startHour * 60 + startMinute) + (duration * 60).toInt()
    val endHour = (totalMinutes / 60) % 24
    val endMinute = totalMinutes % 60

    return "${endHour.toString().padStart(2, '0')}:${endMinute.toString().padStart(2, '0')}"
}

/**
 * Calculate duration in hours from start and end time
 */
private fun calculateDuration(startTime: String?, endTime: String?): Double? {
    if (startTime == null || endTime == null) return null

    val startParts = startTime.split(":")
    val endParts = endTime.split(":")

    if (startParts.size != 2 || endParts.size != 2) return null

    val startHour = startParts[0].toIntOrNull() ?: return null
    val startMinute = startParts[1].toIntOrNull() ?: return null
    val endHour = endParts[0].toIntOrNull() ?: return null
    val endMinute = endParts[1].toIntOrNull() ?: return null

    val startTotalMinutes = startHour * 60 + startMinute
    val endTotalMinutes = endHour * 60 + endMinute

    val durationMinutes = if (endTotalMinutes >= startTotalMinutes) {
        endTotalMinutes - startTotalMinutes
    } else {
        // Crosses midnight
        (24 * 60 - startTotalMinutes) + endTotalMinutes
    }

    return durationMinutes / 60.0
}
