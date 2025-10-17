package com.aurora.travlogue.feature.tripdetail.domain.models

import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip

/**
 * Aggregated data for a trip with all related entities
 * Used to load all trip information in a single query
 */
data class TripDetailData(
    val trip: Trip,
    val locations: List<Location> = emptyList(),
    val activities: List<Activity> = emptyList(),
    val bookings: List<Booking> = emptyList()
) {
    val locationCount: Int get() = locations.size
    val activityCount: Int get() = activities.size
    val bookingCount: Int get() = bookings.size
    val hasContent: Boolean get() = locations.isNotEmpty() || activities.isNotEmpty() || bookings.isNotEmpty()
}
