package com.aurora.travlogue.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trip DTOs matching Logbook API schema
 */

@Serializable
enum class TripVisibility {
    @SerialName("public")
    PUBLIC,
    @SerialName("unlisted")
    UNLISTED,
    @SerialName("private")
    PRIVATE
}

@Serializable
enum class TripStatus {
    @SerialName("planning")
    PLANNING,
    @SerialName("upcoming")
    UPCOMING,
    @SerialName("ongoing")
    ONGOING,
    @SerialName("completed")
    COMPLETED,
    @SerialName("cancelled")
    CANCELLED
}

@Serializable
enum class TripType {
    @SerialName("solo")
    SOLO,
    @SerialName("couple")
    COUPLE,
    @SerialName("family")
    FAMILY,
    @SerialName("friends")
    FRIENDS,
    @SerialName("business")
    BUSINESS,
    @SerialName("group")
    GROUP,
    @SerialName("other")
    OTHER
}

@Serializable
data class TripCreateDto(
    val name: String,
    val description: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("origin_city")
    val originCity: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null,
    val destinations: List<String> = emptyList(),
    @SerialName("trip_type")
    val tripType: TripType? = null,
    val visibility: TripVisibility = TripVisibility.PRIVATE,
    val status: TripStatus = TripStatus.PLANNING,
    @SerialName("cover_image")
    val coverImage: String? = null,
    val budget: Double? = null,
    val currency: String = "USD",
    val tags: List<String> = emptyList()
)

@Serializable
data class TripUpdateDto(
    val name: String? = null,
    val description: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("origin_city")
    val originCity: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null,
    val destinations: List<String>? = null,
    @SerialName("trip_type")
    val tripType: TripType? = null,
    val visibility: TripVisibility? = null,
    val status: TripStatus? = null,
    @SerialName("cover_image")
    val coverImage: String? = null,
    val budget: Double? = null,
    val currency: String? = null,
    val tags: List<String>? = null
)

@Serializable
data class TripResponseDto(
    val id: Int,
    val name: String,
    val description: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("origin_city")
    val originCity: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null,
    val destinations: List<String> = emptyList(),
    @SerialName("countries_visited")
    val countriesVisited: List<String> = emptyList(),
    @SerialName("cities_visited")
    val citiesVisited: List<String> = emptyList(),
    @SerialName("trip_type")
    val tripType: TripType? = null,
    val visibility: TripVisibility = TripVisibility.PRIVATE,
    val status: TripStatus = TripStatus.PLANNING,
    @SerialName("cover_image")
    val coverImage: String? = null,
    val budget: Double? = null,
    val currency: String = "USD",
    val tags: List<String> = emptyList(),
    @SerialName("is_collaborative")
    val isCollaborative: Boolean = false,
    @SerialName("view_count")
    val viewCount: Int = 0,
    @SerialName("like_count")
    val likeCount: Int = 0,
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null
)

@Serializable
data class TripListResponseDto(
    val id: Int,
    val name: String,
    val description: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("countries_visited")
    val countriesVisited: List<String> = emptyList(),
    @SerialName("cities_visited")
    val citiesVisited: List<String> = emptyList(),
    @SerialName("trip_type")
    val tripType: TripType? = null,
    val status: TripStatus = TripStatus.PLANNING,
    @SerialName("cover_image")
    val coverImage: String? = null,
    @SerialName("view_count")
    val viewCount: Int = 0,
    @SerialName("like_count")
    val likeCount: Int = 0,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)
