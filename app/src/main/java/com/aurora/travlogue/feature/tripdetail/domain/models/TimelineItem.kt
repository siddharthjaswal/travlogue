package com.aurora.travlogue.feature.tripdetail.domain.models

import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location

/**
 * Sealed class representing all possible items in the timeline.
 * Timeline shows a chronological view of the trip including:
 * - City arrivals/departures (from bookings)
 * - Day schedules with activities
 * - Bookings (flights, hotels, trains)
 * - Gaps (missing details)
 */
sealed class TimelineItem {

    /**
     * Represents arrival at a city
     * Derived from the first booking arriving at a location
     */
    data class CityArrival(
        val location: Location,
        val arrivalDateTime: String, // ISO format with timezone
        val arrivalBooking: Booking // The booking that brings you here
    ) : TimelineItem()

    /**
     * Represents a full day at a location with activities
     */
    data class DaySchedule(
        val daySchedule: com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule,
        val activities: List<Activity>
    ) : TimelineItem()

    /**
     * Represents departure from a city
     * Derived from the last booking leaving a location
     */
    data class CityDeparture(
        val location: Location,
        val departureDateTime: String, // ISO format with timezone
        val departureBooking: Booking // The booking that takes you away
    ) : TimelineItem()

    /**
     * Represents a booking (flight, hotel, train, etc.)
     * Shown in the timeline at the booking's start time
     */
    data class BookingItem(
        val booking: Booking
    ) : TimelineItem()

    /**
     * Represents a gap in the itinerary
     * (missing transit, unplanned days, etc.)
     */
    data class GapItem(
        val gap: Gap
    ) : TimelineItem()
}

/**
 * Helper to get a sortable timestamp for timeline ordering
 */
fun TimelineItem.getSortableTimestamp(): String {
    return when (this) {
        is TimelineItem.CityArrival -> arrivalDateTime
        is TimelineItem.DaySchedule -> "${daySchedule.date}T00:00:00"
        is TimelineItem.CityDeparture -> departureDateTime
        is TimelineItem.BookingItem -> booking.startDateTime
        is TimelineItem.GapItem -> gap.fromDate ?: "9999-12-31T23:59:59"
    }
}
