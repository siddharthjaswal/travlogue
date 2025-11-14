package com.aurora.travlogue.core.data.remote

import com.aurora.travlogue.core.data.local.TokenStorage
import com.aurora.travlogue.core.data.remote.dto.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * API client for Logbook backend
 * Handles all HTTP communication with the backend API
 */
class LogbookApiClient(
    private val tokenStorage: TokenStorage,
    private val enableLogging: Boolean = false
) {
    companion object {
        const val BASE_URL = "https://api.travlogue.in/api/v1"
        const val TIMEOUT_MILLIS = 30_000L
    }

    private val httpClient: HttpClient by lazy {
        createHttpClient(tokenStorage, enableLogging)
    }

    // ==================== Auth APIs ====================

    /**
     * Authenticate with Google using ID token
     * Sends Google ID token to backend for validation and JWT token exchange
     */
    suspend fun authenticateWithGoogle(idToken: String): Result<AuthUserResponse> {
        return safeApiCall {
            httpClient.post("$BASE_URL/auth/google") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("idToken" to idToken))
            }.body()
        }
    }

    /**
     * Refresh access token using refresh token
     */
    suspend fun refreshToken(): Result<TokenRefreshResponse> {
        return try {
            val refreshToken = tokenStorage.getRefreshToken()
                ?: return Result.failure(Exception("No refresh token available"))

            val response: TokenRefreshResponse = httpClient.post("$BASE_URL/auth/refresh") {
                contentType(ContentType.Application.Json)
                setBody(TokenRefreshRequest(refreshToken))
            }.body()

            // Save new access token
            tokenStorage.saveAccessToken(response.accessToken)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get authenticated user profile
     */
    suspend fun getMe(): Result<UserDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/auth/me").body()
        }
    }

    /**
     * Logout user (client-side only, clears tokens)
     */
    suspend fun logout(): Result<Unit> {
        return try {
            // Call logout endpoint
            httpClient.post("$BASE_URL/auth/logout")
            // Clear local tokens
            tokenStorage.clearTokens()
            Result.success(Unit)
        } catch (e: Exception) {
            // Still clear tokens even if API call fails
            tokenStorage.clearTokens()
            Result.failure(e)
        }
    }

    // ==================== User APIs ====================

    /**
     * Get current user's profile
     */
    suspend fun getMyProfile(): Result<UserDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/users/me").body()
        }
    }

    /**
     * Update current user's profile
     */
    suspend fun updateMyProfile(update: UserUpdateDto): Result<UserDto> {
        return safeApiCall {
            httpClient.put("$BASE_URL/users/me") {
                contentType(ContentType.Application.Json)
                setBody(update)
            }.body()
        }
    }

    // ==================== Trip APIs ====================

    /**
     * List all trips for authenticated user
     */
    suspend fun listMyTrips(
        skip: Int = 0,
        limit: Int = 100,
        statusFilter: TripStatus? = null
    ): Result<List<TripListResponseDto>> {
        return safeApiCall {
            httpClient.get("$BASE_URL/trips/") {
                parameter("skip", skip)
                parameter("limit", limit)
                statusFilter?.let { parameter("status_filter", it.name.lowercase()) }
            }.body()
        }
    }

    /**
     * Get trip by ID
     */
    suspend fun getTrip(tripId: Int): Result<TripResponseDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/trips/$tripId").body()
        }
    }

    /**
     * Create new trip
     */
    suspend fun createTrip(trip: TripCreateDto): Result<TripResponseDto> {
        return safeApiCall {
            httpClient.post("$BASE_URL/trips/") {
                contentType(ContentType.Application.Json)
                setBody(trip)
            }.body()
        }
    }

    /**
     * Update trip
     */
    suspend fun updateTrip(tripId: Int, update: TripUpdateDto): Result<TripResponseDto> {
        return safeApiCall {
            httpClient.put("$BASE_URL/trips/$tripId") {
                contentType(ContentType.Application.Json)
                setBody(update)
            }.body()
        }
    }

    /**
     * Delete trip (soft delete)
     */
    suspend fun deleteTrip(tripId: Int): Result<Unit> {
        return safeApiCall {
            httpClient.delete("$BASE_URL/trips/$tripId")
            Unit
        }
    }

    /**
     * Search trips
     */
    suspend fun searchTrips(
        query: String,
        skip: Int = 0,
        limit: Int = 100
    ): Result<List<TripListResponseDto>> {
        return safeApiCall {
            httpClient.get("$BASE_URL/trips/search") {
                parameter("q", query)
                parameter("skip", skip)
                parameter("limit", limit)
            }.body()
        }
    }

    // ==================== TripDay APIs ====================

    /**
     * List trip days for a trip
     */
    suspend fun listTripDays(
        tripId: Int,
        dayType: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        skip: Int = 0,
        limit: Int = 100
    ): Result<List<TripDayListResponseDto>> {
        return safeApiCall {
            httpClient.get("$BASE_URL/trip-days/") {
                parameter("trip_id", tripId)
                dayType?.let { parameter("day_type", it) }
                startDate?.let { parameter("start_date", it) }
                endDate?.let { parameter("end_date", it) }
                parameter("skip", skip)
                parameter("limit", limit)
            }.body()
        }
    }

    /**
     * Get trip day by ID
     */
    suspend fun getTripDay(tripDayId: Int): Result<TripDayResponseDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/trip-days/$tripDayId").body()
        }
    }

    /**
     * Create trip day
     */
    suspend fun createTripDay(tripDay: TripDayCreateDto): Result<TripDayResponseDto> {
        return safeApiCall {
            httpClient.post("$BASE_URL/trip-days/") {
                contentType(ContentType.Application.Json)
                setBody(tripDay)
            }.body()
        }
    }

    /**
     * Update trip day
     */
    suspend fun updateTripDay(tripDayId: Int, update: TripDayUpdateDto): Result<TripDayResponseDto> {
        return safeApiCall {
            httpClient.put("$BASE_URL/trip-days/$tripDayId") {
                contentType(ContentType.Application.Json)
                setBody(update)
            }.body()
        }
    }

    /**
     * Delete trip day
     */
    suspend fun deleteTripDay(tripDayId: Int): Result<Unit> {
        return safeApiCall {
            httpClient.delete("$BASE_URL/trip-days/$tripDayId")
            Unit
        }
    }

    // ==================== Activity APIs ====================

    /**
     * List activities for a trip day
     */
    suspend fun listActivitiesByTripDay(
        tripDayId: Int,
        activityType: ActivityType? = null,
        activityStatus: ActivityStatus? = null,
        skip: Int = 0,
        limit: Int = 100
    ): Result<List<ActivityListResponseDto>> {
        return safeApiCall {
            httpClient.get("$BASE_URL/activities/trip-day/$tripDayId") {
                parameter("skip", skip)
                parameter("limit", limit)
                activityType?.let { parameter("activity_type", it.name.lowercase()) }
                activityStatus?.let { parameter("activity_status", it.name.lowercase()) }
            }.body()
        }
    }

    /**
     * Get activity by ID
     */
    suspend fun getActivity(activityId: Int): Result<ActivityResponseDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/activities/$activityId").body()
        }
    }

    /**
     * Create activity
     */
    suspend fun createActivity(activity: ActivityCreateDto): Result<ActivityResponseDto> {
        return safeApiCall {
            httpClient.post("$BASE_URL/activities/") {
                contentType(ContentType.Application.Json)
                setBody(activity)
            }.body()
        }
    }

    /**
     * Update activity
     */
    suspend fun updateActivity(activityId: Int, update: ActivityUpdateDto): Result<ActivityResponseDto> {
        return safeApiCall {
            httpClient.put("$BASE_URL/activities/$activityId") {
                contentType(ContentType.Application.Json)
                setBody(update)
            }.body()
        }
    }

    /**
     * Delete activity
     */
    suspend fun deleteActivity(activityId: Int): Result<Unit> {
        return safeApiCall {
            httpClient.delete("$BASE_URL/activities/$activityId")
            Unit
        }
    }

    /**
     * Reorder activities
     */
    suspend fun reorderActivities(tripDayId: Int, activityOrder: List<Int>): Result<List<ActivityListResponseDto>> {
        return safeApiCall {
            httpClient.post("$BASE_URL/activities/trip-day/$tripDayId/reorder") {
                contentType(ContentType.Application.Json)
                setBody(activityOrder)
            }.body()
        }
    }

    // ==================== Booking APIs ====================

    /**
     * List bookings for a trip day
     */
    suspend fun listBookingsByTripDay(
        tripDayId: Int,
        bookingType: BookingType? = null,
        bookingStatus: BookingStatus? = null,
        skip: Int = 0,
        limit: Int = 100
    ): Result<List<BookingListResponseDto>> {
        return safeApiCall {
            httpClient.get("$BASE_URL/bookings/trip-day/$tripDayId") {
                parameter("skip", skip)
                parameter("limit", limit)
                bookingType?.let { parameter("booking_type", it.name.lowercase()) }
                bookingStatus?.let { parameter("booking_status", it.name.lowercase()) }
            }.body()
        }
    }

    /**
     * Get booking by ID
     */
    suspend fun getBooking(bookingId: Int): Result<BookingResponseDto> {
        return safeApiCall {
            httpClient.get("$BASE_URL/bookings/$bookingId").body()
        }
    }

    /**
     * Create booking
     */
    suspend fun createBooking(booking: BookingCreateDto): Result<BookingResponseDto> {
        return safeApiCall {
            httpClient.post("$BASE_URL/bookings/") {
                contentType(ContentType.Application.Json)
                setBody(booking)
            }.body()
        }
    }

    /**
     * Update booking
     */
    suspend fun updateBooking(bookingId: Int, update: BookingUpdateDto): Result<BookingResponseDto> {
        return safeApiCall {
            httpClient.put("$BASE_URL/bookings/$bookingId") {
                contentType(ContentType.Application.Json)
                setBody(update)
            }.body()
        }
    }

    /**
     * Delete booking
     */
    suspend fun deleteBooking(bookingId: Int): Result<Unit> {
        return safeApiCall {
            httpClient.delete("$BASE_URL/bookings/$bookingId")
            Unit
        }
    }

    // ==================== Helper Functions ====================

    /**
     * Wrapper for safe API calls with error handling
     */
    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Close the HTTP client
     */
    fun close() {
        httpClient.close()
    }
}

/**
 * Factory function to create HttpClient with authentication
 */
fun createHttpClient(
    tokenStorage: TokenStorage,
    enableLogging: Boolean = false
): HttpClient {
    return HttpClient {
        // Content negotiation for JSON
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        // Authentication with Bearer token
        install(Auth) {
            bearer {
                loadTokens {
                    val accessToken = tokenStorage.getAccessToken()
                    val refreshToken = tokenStorage.getRefreshToken()
                    if (accessToken != null && refreshToken != null) {
                        BearerTokens(accessToken, refreshToken)
                    } else {
                        null
                    }
                }

                refreshTokens {
                    val refreshToken = tokenStorage.getRefreshToken()
                        ?: return@refreshTokens null

                    try {
                        val response: TokenRefreshResponse = client.post("${LogbookApiClient.BASE_URL}/auth/refresh") {
                            contentType(ContentType.Application.Json)
                            setBody(TokenRefreshRequest(refreshToken))
                        }.body()

                        // Save new access token
                        tokenStorage.saveAccessToken(response.accessToken)

                        BearerTokens(response.accessToken, refreshToken)
                    } catch (e: Exception) {
                        null
                    }
                }
            }
        }

        // Logging
        if (enableLogging) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }

        // Timeout configuration
        install(HttpTimeout) {
            requestTimeoutMillis = LogbookApiClient.TIMEOUT_MILLIS
            connectTimeoutMillis = LogbookApiClient.TIMEOUT_MILLIS
        }

        // Default request configuration
        defaultRequest {
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }
    }
}
