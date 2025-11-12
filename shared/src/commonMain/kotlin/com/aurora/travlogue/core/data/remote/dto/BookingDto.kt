package com.aurora.travlogue.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Booking DTOs matching Logbook API schema
 */

@Serializable
enum class BookingType {
    @SerialName("accommodation")
    ACCOMMODATION,
    @SerialName("restaurant")
    RESTAURANT,
    @SerialName("tour")
    TOUR,
    @SerialName("transportation")
    TRANSPORTATION,
    @SerialName("entertainment")
    ENTERTAINMENT,
    @SerialName("attraction")
    ATTRACTION,
    @SerialName("service")
    SERVICE,
    @SerialName("rental")
    RENTAL,
    @SerialName("other")
    OTHER
}

@Serializable
enum class BookingStatus {
    @SerialName("pending")
    PENDING,
    @SerialName("confirmed")
    CONFIRMED,
    @SerialName("completed")
    COMPLETED,
    @SerialName("cancelled")
    CANCELLED
}

@Serializable
data class BookingCreateDto(
    @SerialName("trip_day_id")
    val tripDayId: Int? = null,
    @SerialName("activity_id")
    val activityId: Int? = null,
    @SerialName("booking_type")
    val bookingType: BookingType,
    val name: String,
    val provider: String? = null,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_reference")
    val bookingReference: String? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    @SerialName("booking_date")
    val bookingDate: String? = null,
    @SerialName("booking_time")
    val bookingTime: String? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    val status: BookingStatus = BookingStatus.PENDING,
    val notes: String? = null
)

@Serializable
data class BookingUpdateDto(
    @SerialName("booking_type")
    val bookingType: BookingType? = null,
    val name: String? = null,
    val provider: String? = null,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_reference")
    val bookingReference: String? = null,
    val cost: Double? = null,
    val currency: String? = null,
    @SerialName("booking_date")
    val bookingDate: String? = null,
    @SerialName("booking_time")
    val bookingTime: String? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    val status: BookingStatus? = null,
    val notes: String? = null
)

@Serializable
data class BookingResponseDto(
    val id: Int,
    @SerialName("trip_day_id")
    val tripDayId: Int? = null,
    @SerialName("activity_id")
    val activityId: Int? = null,
    @SerialName("booking_type")
    val bookingType: BookingType,
    val name: String,
    val provider: String? = null,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    @SerialName("booking_reference")
    val bookingReference: String? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    @SerialName("booking_date")
    val bookingDate: String? = null,
    @SerialName("booking_time")
    val bookingTime: String? = null,
    val location: String? = null,
    @SerialName("location_address")
    val locationAddress: String? = null,
    @SerialName("contact_phone")
    val contactPhone: String? = null,
    @SerialName("contact_email")
    val contactEmail: String? = null,
    @SerialName("booking_url")
    val bookingUrl: String? = null,
    val status: BookingStatus = BookingStatus.PENDING,
    val notes: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class BookingListResponseDto(
    val id: Int,
    @SerialName("trip_day_id")
    val tripDayId: Int? = null,
    @SerialName("activity_id")
    val activityId: Int? = null,
    @SerialName("booking_type")
    val bookingType: BookingType,
    val name: String,
    val provider: String? = null,
    @SerialName("confirmation_number")
    val confirmationNumber: String? = null,
    val cost: Double? = null,
    val currency: String = "USD",
    @SerialName("booking_date")
    val bookingDate: String? = null,
    val status: BookingStatus = BookingStatus.PENDING
)
