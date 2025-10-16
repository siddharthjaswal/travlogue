package com.aurora.travlogue.navigation

import kotlinx.serialization.Serializable

/**
 * Base sealed interface for all app destinations
 * Uses Kotlin Serialization for type-safe navigation
 */
sealed interface AppDestination

/**
 * Home screen destination - shows list of all trips
 * No arguments required
 */
@Serializable
object Home : AppDestination

/**
 * Trip detail screen destination
 * Shows detailed information about a specific trip
 *
 * @param tripId The unique identifier of the trip to display
 */
@Serializable
data class TripDetail(
    val tripId: String
) : AppDestination

/**
 * Trip plan screen destination
 * Shows the itinerary builder for a specific trip
 *
 * @param tripId The unique identifier of the trip to plan
 * @param editMode Whether to open in edit mode (default: false)
 */
@Serializable
data class TripPlan(
    val tripId: String,
    val editMode: Boolean = false
) : AppDestination
