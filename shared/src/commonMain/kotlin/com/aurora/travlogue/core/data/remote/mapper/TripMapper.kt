package com.aurora.travlogue.core.data.remote.mapper

import com.aurora.travlogue.core.data.remote.dto.*
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.model.currentTimeMillis

/**
 * Mappers for converting between Trip DTOs and Domain models
 */

/**
 * Convert TripResponseDto from backend to local Trip domain model
 *
 * Note: Backend has many more fields than local model.
 * This mapper extracts only the fields currently used in the app.
 * Additional fields can be stored in a separate table for sync purposes.
 */
fun TripResponseDto.toDomainModel(): Trip {
    // Determine date type based on whether dates are fixed or flexible
    val dateType = if (startDate != null && endDate != null) {
        DateType.FIXED
    } else {
        DateType.FLEXIBLE
    }

    return Trip(
        id = id.toString(), // Backend uses Int ID, local uses String UUID
        name = name,
        originCity = originCity ?: "",
        dateType = dateType,
        startDate = startDate,
        endDate = endDate,
        flexibleMonth = null, // Backend doesn't have this field yet
        flexibleDuration = null, // Backend doesn't have this field yet
        createdAt = parseIsoDateTime(createdAt),
        updatedAt = parseIsoDateTime(updatedAt)
    )
}

/**
 * Convert TripListResponseDto to Trip domain model
 */
fun TripListResponseDto.toDomainModel(): Trip {
    val dateType = if (startDate != null && endDate != null) {
        DateType.FIXED
    } else {
        DateType.FLEXIBLE
    }

    return Trip(
        id = id.toString(),
        name = name,
        originCity = "", // Not included in list response
        dateType = dateType,
        startDate = startDate,
        endDate = endDate,
        flexibleMonth = null,
        flexibleDuration = null,
        createdAt = parseIsoDateTime(createdAt),
        updatedAt = parseIsoDateTime(updatedAt)
    )
}

/**
 * Convert Trip domain model to TripCreateDto for backend
 */
fun Trip.toCreateDto(
    description: String? = null,
    visibility: TripVisibility = TripVisibility.PRIVATE,
    currency: String = "USD"
): TripCreateDto {
    return TripCreateDto(
        name = name,
        description = description,
        startDate = startDate,
        endDate = endDate,
        originCity = originCity.ifEmpty { null },
        originCountry = null, // Not in local model yet
        destinations = emptyList(), // Will be populated from locations
        tripType = null, // Not in local model yet
        visibility = visibility,
        status = determineTripStatus(startDate, endDate),
        currency = currency,
        tags = emptyList()
    )
}

/**
 * Convert Trip domain model to TripUpdateDto for backend
 */
fun Trip.toUpdateDto(
    description: String? = null,
    visibility: TripVisibility? = null,
    currency: String? = null
): TripUpdateDto {
    return TripUpdateDto(
        name = name,
        description = description,
        startDate = startDate,
        endDate = endDate,
        originCity = originCity.ifEmpty { null },
        originCountry = null,
        destinations = null,
        tripType = null,
        visibility = visibility,
        status = determineTripStatus(startDate, endDate),
        currency = currency,
        tags = null
    )
}

/**
 * Determine trip status based on dates
 */
private fun determineTripStatus(startDate: String?, endDate: String?): TripStatus {
    if (startDate == null) {
        return TripStatus.PLANNING
    }

    val currentTime = currentTimeMillis()
    val startMillis = parseIsoDate(startDate)
    val endMillis = endDate?.let { parseIsoDate(it) }

    return when {
        currentTime < startMillis -> TripStatus.UPCOMING
        endMillis != null && currentTime > endMillis -> TripStatus.COMPLETED
        currentTime in startMillis..(endMillis ?: Long.MAX_VALUE) -> TripStatus.ONGOING
        else -> TripStatus.PLANNING
    }
}

/**
 * Parse ISO 8601 date string (yyyy-MM-dd) to epoch millis
 */
private fun parseIsoDate(date: String): Long {
    // Simple date parsing for yyyy-MM-dd format
    val parts = date.split("-")
    if (parts.size != 3) return currentTimeMillis()

    val year = parts[0].toIntOrNull() ?: return currentTimeMillis()
    val month = parts[1].toIntOrNull() ?: return currentTimeMillis()
    val day = parts[2].toIntOrNull() ?: return currentTimeMillis()

    // Approximate calculation (not accounting for leap years, etc.)
    // For production, use kotlinx-datetime
    val millisPerDay = 24 * 60 * 60 * 1000L
    val millisPerYear = 365L * millisPerDay
    val daysInMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    val yearMillis = (year - 1970) * millisPerYear
    val monthMillis = daysInMonth.take(month - 1).sum() * millisPerDay
    val dayMillis = (day - 1) * millisPerDay

    return yearMillis + monthMillis + dayMillis
}

/**
 * Parse ISO 8601 datetime string to epoch millis
 */
private fun parseIsoDateTime(datetime: String): Long {
    // For now, just parse the date part
    // Format: 2025-11-12T10:30:00Z
    val datePart = datetime.substringBefore('T')
    return parseIsoDate(datePart)
}

/**
 * Extended Trip model that includes all backend fields
 * Use this for caching full backend data
 */
data class TripExtended(
    val trip: Trip,
    val description: String?,
    val originCountry: String?,
    val destinations: List<String>,
    val countriesVisited: List<String>,
    val citiesVisited: List<String>,
    val tripType: TripType?,
    val visibility: TripVisibility,
    val status: TripStatus,
    val coverImage: String?,
    val budget: Double?,
    val currency: String,
    val tags: List<String>,
    val isCollaborative: Boolean,
    val viewCount: Int,
    val likeCount: Int,
    val createdBy: Int,
    val backendId: Int // Backend's integer ID
)

/**
 * Convert TripResponseDto to TripExtended
 */
fun TripResponseDto.toExtendedModel(): TripExtended {
    return TripExtended(
        trip = toDomainModel(),
        description = description,
        originCountry = originCountry,
        destinations = destinations,
        countriesVisited = countriesVisited,
        citiesVisited = citiesVisited,
        tripType = tripType,
        visibility = visibility,
        status = status,
        coverImage = coverImage,
        budget = budget,
        currency = currency,
        tags = tags,
        isCollaborative = isCollaborative,
        viewCount = viewCount,
        likeCount = likeCount,
        createdBy = createdBy,
        backendId = id
    )
}
