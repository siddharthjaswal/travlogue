package com.aurora.travlogue.core.data.remote.mapper

import com.aurora.travlogue.core.data.remote.dto.*
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType as DomainBookingType

/**
 * Mappers for converting between Booking DTOs and Domain models
 */

/**
 * Convert BookingResponseDto from backend to local Booking domain model
 *
 * Note: Backend and local booking models are very well aligned!
 * This is one of the easiest mappings.
 */
fun BookingResponseDto.toDomainModel(): Booking {
    // Combine date and time for start/end DateTime
    val startDateTime = combineDateTime(bookingDate, bookingTime)

    return Booking(
        id = id.toString(),
        tripId = tripDayId?.toString() ?: "", // Map trip_day_id to tripId for now
        type = bookingType.toDomainType(),
        confirmationNumber = confirmationNumber,
        provider = provider ?: "",
        startDateTime = startDateTime,
        endDateTime = null, // Backend doesn't have explicit end time
        timezone = "UTC", // Default timezone
        fromLocation = location,
        toLocation = null, // Backend doesn't separate from/to yet
        price = cost,
        currency = currency,
        notes = notes,
        imageUri = null // Local field, not in backend
    )
}

/**
 * Convert BookingListResponseDto to Booking domain model
 */
fun BookingListResponseDto.toDomainModel(): Booking {
    val startDateTime = combineDateTime(bookingDate, null)

    return Booking(
        id = id.toString(),
        tripId = tripDayId?.toString() ?: "",
        type = bookingType.toDomainType(),
        confirmationNumber = confirmationNumber,
        provider = provider ?: "",
        startDateTime = startDateTime,
        endDateTime = null,
        timezone = "UTC",
        fromLocation = null, // Not in list response
        toLocation = null,
        price = cost,
        currency = currency,
        notes = null,
        imageUri = null
    )
}

/**
 * Convert Booking domain model to BookingCreateDto for backend
 */
fun Booking.toCreateDto(tripDayId: Int? = null, activityId: Int? = null): BookingCreateDto {
    val (date, time) = splitDateTime(startDateTime)

    return BookingCreateDto(
        tripDayId = tripDayId,
        activityId = activityId,
        bookingType = type.toBackendType(),
        name = provider, // Use provider as name
        provider = provider,
        confirmationNumber = confirmationNumber,
        bookingReference = confirmationNumber, // Use same value
        cost = price,
        currency = currency ?: "USD",
        bookingDate = date,
        bookingTime = time,
        location = fromLocation ?: "", // Handle nullable fromLocation
        locationAddress = null, // Not in local model
        contactPhone = null,
        contactEmail = null,
        bookingUrl = null,
        status = BookingStatus.PENDING,
        notes = notes
    )
}

/**
 * Convert Booking domain model to BookingUpdateDto for backend
 */
fun Booking.toUpdateDto(): BookingUpdateDto {
    val (date, time) = splitDateTime(startDateTime)

    return BookingUpdateDto(
        bookingType = type.toBackendType(),
        name = provider,
        provider = provider,
        confirmationNumber = confirmationNumber,
        bookingReference = confirmationNumber,
        cost = price,
        currency = currency,
        bookingDate = date,
        bookingTime = time,
        location = fromLocation,
        locationAddress = null,
        contactPhone = null,
        contactEmail = null,
        bookingUrl = null,
        status = null, // Don't update status automatically
        notes = notes
    )
}

/**
 * Map backend BookingType to domain BookingType
 * Note: Backend types are more granular, domain types are simplified
 */
private fun com.aurora.travlogue.core.data.remote.dto.BookingType.toDomainType(): DomainBookingType {
    return when (this) {
        com.aurora.travlogue.core.data.remote.dto.BookingType.ACCOMMODATION -> DomainBookingType.HOTEL
        com.aurora.travlogue.core.data.remote.dto.BookingType.TRANSPORTATION -> DomainBookingType.OTHER // Generic transport
        com.aurora.travlogue.core.data.remote.dto.BookingType.TOUR -> DomainBookingType.TICKET
        com.aurora.travlogue.core.data.remote.dto.BookingType.RESTAURANT -> DomainBookingType.OTHER
        com.aurora.travlogue.core.data.remote.dto.BookingType.ENTERTAINMENT -> DomainBookingType.TICKET
        com.aurora.travlogue.core.data.remote.dto.BookingType.ATTRACTION -> DomainBookingType.TICKET
        com.aurora.travlogue.core.data.remote.dto.BookingType.SERVICE,
        com.aurora.travlogue.core.data.remote.dto.BookingType.RENTAL,
        com.aurora.travlogue.core.data.remote.dto.BookingType.OTHER -> DomainBookingType.OTHER
    }
}

/**
 * Map domain BookingType to backend BookingType
 * Note: Domain types are simplified, choose most appropriate backend type
 */
private fun DomainBookingType.toBackendType(): com.aurora.travlogue.core.data.remote.dto.BookingType {
    return when (this) {
        DomainBookingType.FLIGHT -> com.aurora.travlogue.core.data.remote.dto.BookingType.TRANSPORTATION
        DomainBookingType.HOTEL -> com.aurora.travlogue.core.data.remote.dto.BookingType.ACCOMMODATION
        DomainBookingType.TRAIN -> com.aurora.travlogue.core.data.remote.dto.BookingType.TRANSPORTATION
        DomainBookingType.BUS -> com.aurora.travlogue.core.data.remote.dto.BookingType.TRANSPORTATION
        DomainBookingType.TICKET -> com.aurora.travlogue.core.data.remote.dto.BookingType.TOUR
        DomainBookingType.OTHER -> com.aurora.travlogue.core.data.remote.dto.BookingType.OTHER
    }
}

/**
 * Combine date and time into ISO 8601 datetime string
 * Format: yyyy-MM-ddTHH:mm:ssZ
 */
private fun combineDateTime(date: String?, time: String?): String {
    val dateStr = date ?: "2025-01-01" // Default date
    val timeStr = time ?: "00:00" // Default time

    return "${dateStr}T${timeStr}:00Z"
}

/**
 * Split ISO 8601 datetime string into date and time
 * Returns pair of (date, time)
 */
private fun splitDateTime(datetime: String): Pair<String?, String?> {
    // Format: yyyy-MM-ddTHH:mm:ssZ or similar
    val parts = datetime.split('T')
    if (parts.size != 2) return Pair(null, null)

    val date = parts[0]
    val time = parts[1].substringBefore('Z').substringBefore('+').substringBefore('-').take(5) // Get HH:mm

    return Pair(date, time)
}
