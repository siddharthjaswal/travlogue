package com.aurora.travlogue.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TripDay DTOs matching Logbook API schema
 */

@Serializable
enum class DayType {
    @SerialName("transit")
    TRANSIT,
    @SerialName("sightseeing")
    SIGHTSEEING,
    @SerialName("relaxation")
    RELAXATION,
    @SerialName("adventure")
    ADVENTURE,
    @SerialName("cultural")
    CULTURAL,
    @SerialName("business")
    BUSINESS,
    @SerialName("free_day")
    FREE_DAY,
    @SerialName("mixed")
    MIXED
}

@Serializable
data class TransitDetailDto(
    val type: String? = null,
    val from: String? = null,
    val to: String? = null,
    @SerialName("departure_time")
    val departureTime: String? = null,
    @SerialName("arrival_time")
    val arrivalTime: String? = null,
    val provider: String? = null,
    @SerialName("booking_reference")
    val bookingReference: String? = null
)

@Serializable
data class TripDayCreateDto(
    @SerialName("trip_id")
    val tripId: Int,
    val date: String,
    @SerialName("day_number")
    val dayNumber: Int,
    @SerialName("day_type")
    val dayType: DayType = DayType.SIGHTSEEING,
    val title: String? = null,
    val notes: String? = null,
    val country: String? = null,
    val city: String? = null,
    @SerialName("transit_details")
    val transitDetails: List<TransitDetailDto> = emptyList()
)

@Serializable
data class TripDayUpdateDto(
    val date: String? = null,
    @SerialName("day_number")
    val dayNumber: Int? = null,
    @SerialName("day_type")
    val dayType: DayType? = null,
    val title: String? = null,
    val notes: String? = null,
    val country: String? = null,
    val city: String? = null,
    @SerialName("transit_details")
    val transitDetails: List<TransitDetailDto>? = null
)

@Serializable
data class TripDayResponseDto(
    val id: Int,
    @SerialName("trip_id")
    val tripId: Int,
    val date: String,
    @SerialName("day_number")
    val dayNumber: Int,
    @SerialName("day_type")
    val dayType: DayType,
    val title: String? = null,
    val notes: String? = null,
    val country: String? = null,
    val city: String? = null,
    @SerialName("transit_details")
    val transitDetails: List<TransitDetailDto> = emptyList(),
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class TripDayListResponseDto(
    val id: Int,
    @SerialName("trip_id")
    val tripId: Int,
    val date: String,
    @SerialName("day_number")
    val dayNumber: Int,
    @SerialName("day_type")
    val dayType: DayType,
    val title: String? = null,
    val country: String? = null,
    val city: String? = null,
    @SerialName("activity_count")
    val activityCount: Int = 0,
    @SerialName("booking_count")
    val bookingCount: Int = 0
)
