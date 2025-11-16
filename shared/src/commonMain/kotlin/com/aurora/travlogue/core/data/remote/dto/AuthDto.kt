package com.aurora.travlogue.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Authentication DTOs matching Logbook API schema
 */

@Serializable
data class TokenRefreshRequest(
    @SerialName("refresh_token")
    val refreshToken: String
)

@Serializable
data class TokenRefreshResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String = "bearer"
)

@Serializable
data class AuthUserResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("token_type")
    val tokenType: String = "bearer",
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    val name: String? = null,
    val username: String? = null,
    @SerialName("profile_picture")
    val profilePicture: String? = null,
    val bio: String? = null,
    @SerialName("home_city")
    val homeCity: String? = null,
    @SerialName("home_country")
    val homeCountry: String? = null,
    @SerialName("is_active")
    val isActive: Boolean = true,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String? = null  // Optional - not always returned by backend
)

@Serializable
data class UserUpdateDto(
    val name: String? = null,
    val username: String? = null,
    @SerialName("profile_picture")
    val profilePicture: String? = null,
    val bio: String? = null,
    @SerialName("home_city")
    val homeCity: String? = null,
    @SerialName("home_country")
    val homeCountry: String? = null
)
