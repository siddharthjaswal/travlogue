package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.feature.tripdetail.components.timeline.BookingTimelineCard
import com.aurora.travlogue.feature.tripdetail.components.timeline.CityArrivalCard
import com.aurora.travlogue.feature.tripdetail.components.timeline.CityDepartureCard
import com.aurora.travlogue.feature.tripdetail.components.timeline.DayCard
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import com.aurora.travlogue.feature.tripdetail.presentation.components.CompactGapCard
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Enhanced timeline tab showing chronological view with:
 * - City arrivals/departures
 * - Day schedules
 * - Bookings
 * - Gaps
 */
@Composable
fun TimelineTab(
    daySchedules: List<DaySchedule>,
    bookings: List<Booking>,
    gaps: List<Gap>,
    locations: List<Location>,
    expandedDays: Set<String>,
    onDayClicked: (String) -> Unit,
    onActivityClick: (com.aurora.travlogue.core.data.local.entities.Activity) -> Unit,
    onBookingClick: (Booking) -> Unit,
    onGapClick: (Gap) -> Unit,
    modifier: Modifier = Modifier
) {
    if (daySchedules.isEmpty() && bookings.isEmpty()) {
        EmptyTimelineState(modifier = modifier)
    } else {
        // Build chronological timeline items
        val timelineItems = buildEnhancedTimelineItems(
            daySchedules,
            bookings,
            gaps,
            locations
        )

        // Create location map for efficient lookups
        val locationMap = locations.associateBy { it.id }

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = timelineItems,
                key = { item -> item.getKey() }
            ) { item ->
                when (item) {
                    is TimelineItem.CityArrival -> {
                        CityArrivalCard(
                            location = item.location,
                            arrivalDateTime = item.arrivalDateTime,
                            arrivalBooking = item.arrivalBooking
                        )
                    }

                    is TimelineItem.DaySchedule -> {
                        DayCard(
                            daySchedule = item.daySchedule,
                            isExpanded = item.daySchedule.date in expandedDays,
                            onDayClicked = { onDayClicked(item.daySchedule.date) },
                            onActivityClick = onActivityClick
                        )
                    }

                    is TimelineItem.CityDeparture -> {
                        CityDepartureCard(
                            location = item.location,
                            departureDateTime = item.departureDateTime,
                            departureBooking = item.departureBooking
                        )
                    }

                    is TimelineItem.BookingItem -> {
                        BookingTimelineCard(
                            booking = item.booking,
                            onClick = { onBookingClick(item.booking) }
                        )
                    }

                    is TimelineItem.GapItem -> {
                        CompactGapCard(
                            gap = item.gap,
                            fromLocation = locationMap[item.gap.fromLocationId],
                            toLocation = locationMap[item.gap.toLocationId],
                            onClick = { onGapClick(item.gap) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Sealed class representing items in the enhanced timeline
 */
private sealed class TimelineItem {
    data class CityArrival(
        val location: Location,
        val arrivalDateTime: String,
        val arrivalBooking: Booking
    ) : TimelineItem()

    data class DaySchedule(
        val daySchedule: com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
    ) : TimelineItem()

    data class CityDeparture(
        val location: Location,
        val departureDateTime: String,
        val departureBooking: Booking
    ) : TimelineItem()

    data class BookingItem(
        val booking: Booking
    ) : TimelineItem()

    data class GapItem(
        val gap: Gap
    ) : TimelineItem()

    /**
     * Get sortable timestamp for ordering
     */
    fun getSortableTimestamp(): String {
        return when (this) {
            is CityArrival -> arrivalDateTime
            is DaySchedule -> "${daySchedule.date}T06:00:00" // Morning of the day
            is CityDeparture -> departureDateTime
            is BookingItem -> booking.startDateTime
            is GapItem -> gap.fromDate ?: "9999-12-31"
        }
    }

    /**
     * Get unique key for LazyColumn
     */
    fun getKey(): String {
        return when (this) {
            is CityArrival -> "arrival-${location.id}-${arrivalBooking.id}"
            is DaySchedule -> "day-${daySchedule.date}"
            is CityDeparture -> "departure-${location.id}-${departureBooking.id}"
            is BookingItem -> "booking-${booking.id}"
            is GapItem -> "gap-${gap.id}"
        }
    }
}

/**
 * Build enhanced timeline combining days, bookings, city transitions, and gaps
 *
 * Strategy:
 * 1. Add city arrivals (from first booking arriving at each location)
 * 2. Add day schedules
 * 3. Add individual bookings (hotels shown inline, transit shown as city transitions)
 * 4. Add city departures (from last booking leaving each location)
 * 5. Add gaps
 * 6. Sort everything chronologically
 */
private fun buildEnhancedTimelineItems(
    daySchedules: List<DaySchedule>,
    bookings: List<Booking>,
    gaps: List<Gap>,
    locations: List<Location>
): List<TimelineItem> {
    val items = mutableListOf<TimelineItem>()
    val locationMap = locations.associateBy { it.id }

    // Track which bookings we've used for city arrivals/departures
    val usedForTransitions = mutableSetOf<String>()

    // Group bookings by location
    val bookingsByLocation = bookings.groupBy { booking ->
        // Try to match booking to location by name similarity
        val toLocation = booking.toLocation
        locations.find { location ->
            toLocation?.contains(location.name, ignoreCase = true) == true
        }?.id
    }

    // Add day schedules
    daySchedules.forEach { daySchedule ->
        items.add(TimelineItem.DaySchedule(daySchedule))

        // Find arrival booking for this location (first booking arriving here)
        daySchedule.location?.let { location ->
            val arrivalBookings = bookings.filter { booking ->
                booking.type in listOf(BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS) &&
                        booking.toLocation?.contains(location.name, ignoreCase = true) == true &&
                        booking.id !in usedForTransitions
            }

            // Use the earliest arrival
            arrivalBookings.minByOrNull { it.startDateTime }?.let { arrivalBooking ->
                items.add(
                    TimelineItem.CityArrival(
                        location = location,
                        arrivalDateTime = arrivalBooking.endDateTime ?: arrivalBooking.startDateTime,
                        arrivalBooking = arrivalBooking
                    )
                )
                usedForTransitions.add(arrivalBooking.id)
            }

            // Find departure booking for this location (last booking leaving)
            val departureBookings = bookings.filter { booking ->
                booking.type in listOf(BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS) &&
                        booking.fromLocation?.contains(location.name, ignoreCase = true) == true &&
                        booking.id !in usedForTransitions
            }

            // Use the latest departure
            departureBookings.maxByOrNull { it.startDateTime }?.let { departureBooking ->
                items.add(
                    TimelineItem.CityDeparture(
                        location = location,
                        departureDateTime = departureBooking.startDateTime,
                        departureBooking = departureBooking
                    )
                )
                usedForTransitions.add(departureBooking.id)
            }
        }
    }

    // Add remaining bookings (hotels, tickets, etc.)
    bookings.forEach { booking ->
        if (booking.id !in usedForTransitions) {
            // Hotels and other bookings show as separate items
            if (booking.type in listOf(BookingType.HOTEL, BookingType.TICKET, BookingType.OTHER)) {
                items.add(TimelineItem.BookingItem(booking))
            }
        }
    }

    // Add gaps
    gaps.forEach { gap ->
        items.add(TimelineItem.GapItem(gap))
    }

    // Sort chronologically
    return items.sortedBy { item ->
        try {
            val timestamp = item.getSortableTimestamp()
            ZonedDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            // Fallback for items without valid timestamps
            ZonedDateTime.now().plusYears(100)
        }
    }
}

@Composable
private fun EmptyTimelineState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "No schedule yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Add locations and activities to build your itinerary",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
