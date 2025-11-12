package com.aurora.travlogue.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Activity DTOs matching Logbook API schema
 */

@Serializable
enum class ActivityType {
    @SerialName("sightseeing")
    SIGHTSEEING,
    @SerialName("dining")
    DINING,
    @SerialName("adventure")
    ADVENTURE,
    @SerialName("cultural")
    CULTURAL,
    @SerialName("entertainment")
    ENTERTAINMENT,
    @SerialName("shopping")
    SHOPPING,
    @SerialName("relaxation")
    RELAXATION,
    @SerialName("transportation")
    TRANSPORTATION,
    @SerialName("accommodation")
    ACCOMMODATION,
    @SerialName("business")
    BUSINESS,
    @SerialName("other")
    OTHER
}

@Serializable
enum class ActivityStatus {
    @SerialName("planned")
    PLANNED,
    @SerialName("booked")
    BOOKED,
    @SerialName("completed")
    COMPLETED,
    @SerialName("cancelled")
    CANCELLED
}

@Serializable
data class ActivityCreateDto(
    @SerialName("trip_day_id")
    val tripDayId: Int,
    val name: String,
    @SerialName("activity_type")
    val activityType: ActivityType,
    val time: String? = null,
    val duration: Double? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    @SerialName("booking_required")
    val bookingRequired: Boolean = false,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    val status: ActivityStatus = ActivityStatus.PLANNED,
    val notes: String? = null,
    @SerialName("display_order")
    val displayOrder: Int = 0
)

@Serializable
data class ActivityUpdateDto(
    val name: String? = null,
    @SerialName("activity_type")
    val activityType: ActivityType? = null,
    val time: String? = null,
    val duration: Double? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val cost: Double? = null,
    val currency: String? = null,
    @SerialName("booking_required")
    val bookingRequired: Boolean? = null,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    val status: ActivityStatus? = null,
    val notes: String? = null,
    @SerialName("display_order")
    val displayOrder: Int? = null
)

@Serializable
data class ActivityResponseDto(
    val id: Int,
    @SerialName("trip_day_id")
    val tripDayId: Int,
    val name: String,
    @SerialName("activity_type")
    val activityType: ActivityType,
    val time: String? = null,
    val duration: Double? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    @SerialName("booking_required")
    val bookingRequired: Boolean = false,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    val status: ActivityStatus = ActivityStatus.PLANNED,
    val notes: String? = null,
    @SerialName("display_order")
    val displayOrder: Int = 0,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class ActivityListResponseDto(
    val id: Int,
    @SerialName("trip_day_id")
    val tripDayId: Int,
    val name: String,
    @SerialName("activity_type")
    val activityType: ActivityType,
    val time: String? = null,
    val duration: Double? = null,
    val location: String? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    val status: ActivityStatus = ActivityStatus.PLANNED,
    @SerialName("display_order")
    val displayOrder: Int = 0
)
