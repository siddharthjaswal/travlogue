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
    private val baseUrl: String = "https://api.travlogue.in/api/v1",
    private val enableLogging: Boolean = false,
    private val logger: io.ktor.client.plugins.logging.Logger? = null
) {
    companion object {
        const val TIMEOUT_MILLIS = 60_000L  // Increased to 60 seconds for Google OAuth verification
    }

    private val httpClient: HttpClient by lazy {
        createHttpClient(tokenStorage, baseUrl, enableLogging, logger)
    }

    // ==================== Auth APIs ====================

    /**
     * Authenticate with Google using ID token
     * Sends Google ID token to backend for validation and JWT token exchange
     */
    suspend fun authenticateWithGoogle(idToken: String): Result<AuthUserResponse> {
        println("🌐 LogbookApiClient: authenticateWithGoogle() called")
        println("🌐 API URL: $baseUrl/auth/google")
        println("🌐 ID Token (first 50 chars): ${idToken.take(50)}")

        val result = safeApiCall {
            println("🌐 Making HTTP POST request...")
            val response = httpClient.post("$baseUrl/auth/google") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("idToken" to idToken))
            }.body<AuthUserResponse>()
            println("🌐 HTTP request completed successfully!")
            response
        }

        result.fold(
            onSuccess = { println("🌐 ✅ Authentication successful") },
            onFailure = { error -> println("🌐 ❌ Authentication failed: ${error.message}") }
        )

        return result
    }

    /**
     * Refresh access token using refresh token
     */
    suspend fun refreshToken(): Result<TokenRefreshResponse> {
        return try {
            val refreshToken = tokenStorage.getRefreshToken()
                ?: return Result.failure(Exception("No refresh token available"))

            val response: TokenRefreshResponse = httpClient.post("$baseUrl/auth/refresh") {
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
            httpClient.get("$baseUrl/auth/me").body()
        }
    }

    /**
     * Logout user (client-side only, clears tokens)
     */
    suspend fun logout(): Result<Unit> {
        return try {
            // Call logout endpoint
            httpClient.post("$baseUrl/auth/logout")
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
            httpClient.get("$baseUrl/users/me").body()
        }
    }

    /**
     * Update current user's profile
     */
    suspend fun updateMyProfile(update: UserUpdateDto): Result<UserDto> {
        return safeApiCall {
            httpClient.put("$baseUrl/users/me") {
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
            httpClient.get("$baseUrl/trips/") {
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
            httpClient.get("$baseUrl/trips/$tripId").body()
        }
    }

    /**
     * Create new trip
     */
    suspend fun createTrip(trip: TripCreateDto): Result<TripResponseDto> {
        return safeApiCall {
            httpClient.post("$baseUrl/trips/") {
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
            httpClient.put("$baseUrl/trips/$tripId") {
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
            httpClient.delete("$baseUrl/trips/$tripId")
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
            httpClient.get("$baseUrl/trips/search") {
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
            httpClient.get("$baseUrl/trip-days/") {
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
            httpClient.get("$baseUrl/trip-days/$tripDayId").body()
        }
    }

    /**
     * Create trip day
     */
    suspend fun createTripDay(tripDay: TripDayCreateDto): Result<TripDayResponseDto> {
        return safeApiCall {
            httpClient.post("$baseUrl/trip-days/") {
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
            httpClient.put("$baseUrl/trip-days/$tripDayId") {
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
            httpClient.delete("$baseUrl/trip-days/$tripDayId")
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
            httpClient.get("$baseUrl/activities/trip-day/$tripDayId") {
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
            httpClient.get("$baseUrl/activities/$activityId").body()
        }
    }

    /**
     * Create activity
     */
    suspend fun createActivity(activity: ActivityCreateDto): Result<ActivityResponseDto> {
        return safeApiCall {
            httpClient.post("$baseUrl/activities/") {
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
            httpClient.put("$baseUrl/activities/$activityId") {
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
            httpClient.delete("$baseUrl/activities/$activityId")
            Unit
        }
    }

    /**
     * Reorder activities
     */
    suspend fun reorderActivities(tripDayId: Int, activityOrder: List<Int>): Result<List<ActivityListResponseDto>> {
        return safeApiCall {
            httpClient.post("$baseUrl/activities/trip-day/$tripDayId/reorder") {
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
            httpClient.get("$baseUrl/bookings/trip-day/$tripDayId") {
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
            httpClient.get("$baseUrl/bookings/$bookingId").body()
        }
    }

    /**
     * Create booking
     */
    suspend fun createBooking(booking: BookingCreateDto): Result<BookingResponseDto> {
        return safeApiCall {
            httpClient.post("$baseUrl/bookings/") {
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
            httpClient.put("$baseUrl/bookings/$bookingId") {
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
            httpClient.delete("$baseUrl/bookings/$bookingId")
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
    baseUrl: String,
    enableLogging: Boolean = false,
    logger: io.ktor.client.plugins.logging.Logger? = null
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

        // Custom interceptor to add Authorization header from TokenStorage
        // This ensures the Bearer token is always fresh for each request
        install(io.ktor.client.plugins.api.createClientPlugin("AuthTokenPlugin") {
            onRequest { request, _ ->
                // Get current access token from storage
                val accessToken = tokenStorage.getAccessToken()
                if (accessToken != null) {
                    request.headers.append(HttpHeaders.Authorization, "Bearer $accessToken")
                    println("🔑 Added Authorization header to request: ${request.url}")
                } else {
                    println("⚠️ No access token available for request: ${request.url}")
                }
            }
        })

        // Authentication with Bearer token (for token refresh functionality)
        install(Auth) {
            bearer {
                loadTokens {
                    println("🔄 Auth.loadTokens() called")
                    val accessToken = tokenStorage.getAccessToken()
                    val refreshToken = tokenStorage.getRefreshToken()
                    println("🔄 Access token: ${if (accessToken != null) "present (${accessToken.take(20)}...)" else "null"}")
                    println("🔄 Refresh token: ${if (refreshToken != null) "present" else "null"}")
                    if (accessToken != null && refreshToken != null) {
                        BearerTokens(accessToken, refreshToken)
                    } else {
                        null
                    }
                }

                refreshTokens {
                    println("🔄 Auth.refreshTokens() called")
                    val refreshToken = tokenStorage.getRefreshToken()
                        ?: return@refreshTokens null

                    try {
                        val response: TokenRefreshResponse = client.post("$baseUrl/auth/refresh") {
                            contentType(ContentType.Application.Json)
                            setBody(TokenRefreshRequest(refreshToken))
                        }.body()

                        // Save new access token
                        tokenStorage.saveAccessToken(response.accessToken)
                        println("🔄 ✅ Token refresh successful")

                        BearerTokens(response.accessToken, refreshToken)
                    } catch (e: Exception) {
                        println("🔄 ❌ Token refresh failed: ${e.message}")
                        null
                    }
                }
            }
        }

        // Logging
        if (enableLogging) {
            install(Logging) {
                this.logger = logger ?: io.ktor.client.plugins.logging.Logger.DEFAULT
                level = LogLevel.ALL  // Log everything: request/response headers, body, etc.
            }
        }

        // Timeout configuration
        install(HttpTimeout) {
            requestTimeoutMillis = LogbookApiClient.TIMEOUT_MILLIS
            connectTimeoutMillis = LogbookApiClient.TIMEOUT_MILLIS
            socketTimeoutMillis = LogbookApiClient.TIMEOUT_MILLIS  // Socket read/write timeout
        }

        // Default request configuration
        defaultRequest {
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }
    }
}
