package com.aurora.travlogue.core.data.remote.mapper

import com.aurora.travlogue.core.data.remote.dto.*
import com.aurora.travlogue.core.domain.model.DayType
import com.aurora.travlogue.core.domain.model.TransitDetail
import com.aurora.travlogue.core.domain.model.TripDay
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Mappers for converting between TripDay DTOs and Domain models
 */

private val json = Json { ignoreUnknownKeys = true }

/**
 * Convert TripDayResponseDto from backend to local TripDay domain model
 */
fun TripDayResponseDto.toDomainModel(localTripId: String, locationId: String? = null): TripDay {
    return TripDay(
        id = id.toString(), // Backend uses Int ID, local uses String
        tripId = localTripId,
        date = date,
        dayNumber = dayNumber,
        dayType = dayType.toDomainModel(),
        title = title,
        notes = notes,
        country = country,
        city = city,
        transitDetails = transitDetails.map { it.toDomainModel() },
        locationId = locationId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Convert TripDayListResponseDto to TripDay domain model
 */
fun TripDayListResponseDto.toDomainModel(localTripId: String, locationId: String? = null): TripDay {
    return TripDay(
        id = id.toString(),
        tripId = localTripId,
        date = date,
        dayNumber = dayNumber,
        dayType = dayType.toDomainModel(),
        title = title,
        notes = null, // Not included in list response
        country = country,
        city = city,
        transitDetails = emptyList(), // Not included in list response
        locationId = locationId,
        createdAt = null, // Not included in list response
        updatedAt = null // Not included in list response
    )
}

/**
 * Convert TripDay domain model to TripDayCreateDto for API
 */
fun TripDay.toCreateDto(backendTripId: Int): TripDayCreateDto {
    return TripDayCreateDto(
        tripId = backendTripId,
        date = date,
        dayNumber = dayNumber,
        dayType = dayType.toDto(),
        title = title,
        notes = notes,
        country = country,
        city = city,
        transitDetails = transitDetails.map { it.toDto() }
    )
}

/**
 * Convert TripDay domain model to TripDayUpdateDto for API
 */
fun TripDay.toUpdateDto(): TripDayUpdateDto {
    return TripDayUpdateDto(
        date = date,
        dayNumber = dayNumber,
        dayType = dayType.toDto(),
        title = title,
        notes = notes,
        country = country,
        city = city,
        transitDetails = transitDetails.map { it.toDto() }
    )
}

// ==================== DayType Mappers ====================

/**
 * Convert DTO DayType to Domain DayType
 */
fun com.aurora.travlogue.core.data.remote.dto.DayType.toDomainModel(): DayType {
    return when (this) {
        com.aurora.travlogue.core.data.remote.dto.DayType.TRANSIT -> DayType.TRANSIT
        com.aurora.travlogue.core.data.remote.dto.DayType.SIGHTSEEING -> DayType.SIGHTSEEING
        com.aurora.travlogue.core.data.remote.dto.DayType.RELAXATION -> DayType.RELAXATION
        com.aurora.travlogue.core.data.remote.dto.DayType.ADVENTURE -> DayType.ADVENTURE
        com.aurora.travlogue.core.data.remote.dto.DayType.CULTURAL -> DayType.CULTURAL
        com.aurora.travlogue.core.data.remote.dto.DayType.BUSINESS -> DayType.BUSINESS
        com.aurora.travlogue.core.data.remote.dto.DayType.FREE_DAY -> DayType.FREE_DAY
        com.aurora.travlogue.core.data.remote.dto.DayType.MIXED -> DayType.MIXED
    }
}

/**
 * Convert Domain DayType to DTO DayType
 */
fun DayType.toDto(): com.aurora.travlogue.core.data.remote.dto.DayType {
    return when (this) {
        DayType.TRANSIT -> com.aurora.travlogue.core.data.remote.dto.DayType.TRANSIT
        DayType.SIGHTSEEING -> com.aurora.travlogue.core.data.remote.dto.DayType.SIGHTSEEING
        DayType.RELAXATION -> com.aurora.travlogue.core.data.remote.dto.DayType.RELAXATION
        DayType.ADVENTURE -> com.aurora.travlogue.core.data.remote.dto.DayType.ADVENTURE
        DayType.CULTURAL -> com.aurora.travlogue.core.data.remote.dto.DayType.CULTURAL
        DayType.BUSINESS -> com.aurora.travlogue.core.data.remote.dto.DayType.BUSINESS
        DayType.FREE_DAY -> com.aurora.travlogue.core.data.remote.dto.DayType.FREE_DAY
        DayType.MIXED -> com.aurora.travlogue.core.data.remote.dto.DayType.MIXED
    }
}

// ==================== TransitDetail Mappers ====================

/**
 * Convert TransitDetailDto to Domain TransitDetail
 */
fun TransitDetailDto.toDomainModel(): TransitDetail {
    return TransitDetail(
        type = type,
        from = from,
        to = to,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        provider = provider,
        bookingReference = bookingReference
    )
}

/**
 * Convert Domain TransitDetail to TransitDetailDto
 */
fun TransitDetail.toDto(): TransitDetailDto {
    return TransitDetailDto(
        type = type,
        from = from,
        to = to,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        provider = provider,
        bookingReference = bookingReference
    )
}

// ==================== JSON Serialization Helpers ====================

/**
 * Serialize transit details to JSON string for SQLDelight storage
 */
fun List<TransitDetail>.toJson(): String {
    return json.encodeToString(this.map { it.toDto() })
}

/**
 * Deserialize transit details from JSON string
 */
fun String?.parseTransitDetails(): List<TransitDetail> {
    if (this.isNullOrEmpty()) return emptyList()
    return try {
        json.decodeFromString<List<TransitDetailDto>>(this).map { it.toDomainModel() }
    } catch (e: Exception) {
        emptyList()
    }
}
