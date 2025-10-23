package com.aurora.travlogue.core.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * API client for Travlogue backend - KMP version using Ktor
 *
 * Note: This is a placeholder implementation. The actual API endpoints
 * will be implemented when the backend is available.
 */
class TravlogueApiClient(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://api.travlogue.app" // Placeholder URL
        const val TIMEOUT_MILLIS = 30_000L
    }

    /**
     * Example: Get all trips for a user
     * This is a placeholder that would call the actual API
     */
    suspend fun getTrips(): Result<List<TripDto>> {
        return try {
            val response = httpClient.get("$BASE_URL/trips")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Example: Sync trip data to backend
     */
    suspend fun syncTrip(trip: TripDto): Result<TripDto> {
        return try {
            val response = httpClient.post("$BASE_URL/trips") {
                contentType(ContentType.Application.Json)
                setBody(trip)
            }
            Result.success(response.body())
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
 * Factory function to create HttpClient with common configuration
 */
fun createHttpClient(enableLogging: Boolean = false): HttpClient {
    return HttpClient {
        // Content negotiation for JSON
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
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
            requestTimeoutMillis = TravlogueApiClient.TIMEOUT_MILLIS
            connectTimeoutMillis = TravlogueApiClient.TIMEOUT_MILLIS
        }

        // Default request configuration
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
@kotlinx.serialization.Serializable
data class TripDto(
    val id: String,
    val name: String,
    val originCity: String
)
