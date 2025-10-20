package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import com.aurora.travlogue.feature.tripdetail.components.timeline.*
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Improved timeline with:
 * - All dates shown with badges
 * - Individual activity cards (no DayCard wrapper)
 * - "Nothing Planned" for empty days
 * - Chronological flow
 */
@Composable
fun TimelineTab(
    daySchedules: List<DaySchedule>,
    bookings: List<Booking>,
    gaps: List<Gap>,
    locations: List<Location>,
    expandedDays: Set<String>,
    onDayClicked: (String) -> Unit,
    onActivityClick: (Activity) -> Unit,
    onBookingClick: (Booking) -> Unit,
    onGapClick: (Gap) -> Unit,
    modifier: Modifier = Modifier
) {
    if (daySchedules.isEmpty() && bookings.isEmpty()) {
        EmptyTimelineState(modifier = modifier)
    } else {
        // Build complete timeline with all dates
        val timelineItems = buildCompleteTimeline(
            daySchedules,
            bookings,
            locations
        )

        // Pre-calculate which items should show dates
        val itemsWithDateFlags = timelineItems.mapIndexed { index, item ->
            val currentDate = extractDate(item.getDateTime())
            val showDate = if (currentDate != null) {
                val previousItems = timelineItems.subList(0, index)
                previousItems.none { extractDate(it.getDateTime()) == currentDate }
            } else {
                false
            }
            Pair(item, showDate)
        }

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            itemsIndexed(
                items = itemsWithDateFlags,
                key = { _, (item, _) -> item.getKey() }
            ) { index, (item, showDate) ->
                when (item) {
                    is TimelineItem.TransitArrival -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            CityArrivalCard(
                                location = item.location,
                                arrivalDateTime = item.arrivalDateTime,
                                arrivalBooking = item.arrivalBooking
                            )
                        }
                    }

                    is TimelineItem.CityWelcome -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            WelcomeCityCard(
                                location = item.location,
                                arrivalDateTime = item.arrivalDateTime
                            )
                        }
                    }

                    is TimelineItem.HotelCheckIn -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            HotelCheckInCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }

                    is TimelineItem.ActivityItem -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            ActivityTimelineCard(
                                activity = item.activity,
                                onClick = { onActivityClick(item.activity) }
                            )
                        }
                    }

                    is TimelineItem.NothingPlanned -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        ) {
                            NothingPlannedCard()
                        }
                    }

                    is TimelineItem.HotelCheckOut -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            HotelCheckOutCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }

                    is TimelineItem.CityGoodbye -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            GoodbyeCityCard(
                                location = item.location,
                                departureDateTime = item.departureDateTime
                            )
                        }
                    }

                    is TimelineItem.TransitDeparture -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            CityDepartureCard(
                                location = item.location,
                                departureDateTime = item.departureDateTime,
                                departureBooking = item.departureBooking
                            )
                        }
                    }

                    is TimelineItem.BookingItem -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            BookingTimelineCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Sealed class for timeline items
 */
private sealed class TimelineItem {
    data class TransitArrival(
        val location: Location,
        val arrivalDateTime: String,
        val arrivalBooking: Booking
    ) : TimelineItem()

    data class CityWelcome(
        val location: Location,
        val arrivalDateTime: String
    ) : TimelineItem()

    data class HotelCheckIn(
        val booking: Booking
    ) : TimelineItem()

    data class ActivityItem(
        val activity: Activity
    ) : TimelineItem()

    data class NothingPlanned(
        val date: String // YYYY-MM-DD format
    ) : TimelineItem()

    data class HotelCheckOut(
        val booking: Booking
    ) : TimelineItem()

    data class CityGoodbye(
        val location: Location,
        val departureDateTime: String
    ) : TimelineItem()

    data class TransitDeparture(
        val location: Location,
        val departureDateTime: String,
        val departureBooking: Booking
    ) : TimelineItem()

    data class BookingItem(
        val booking: Booking
    ) : TimelineItem()

    fun getSortableTimestamp(): String {
        return when (this) {
            is TransitArrival -> "${arrivalDateTime}:1"
            is CityWelcome -> "${arrivalDateTime}:2"
            is HotelCheckIn -> "${booking.startDateTime}:3"
            is ActivityItem -> {
                val date = activity.date ?: "9999-12-31"
                val time = activity.startTime ?: when (activity.timeSlot) {
                    TimeSlot.MORNING -> "09:00:00"
                    TimeSlot.AFTERNOON -> "13:00:00"
                    TimeSlot.EVENING -> "17:00:00"
                    TimeSlot.FULL_DAY -> "09:00:00"
                    null -> "12:00:00"
                }
                "${date}T${time}:4"
            }

            is NothingPlanned -> "${date}T06:00:00:5"
            is HotelCheckOut -> "${booking.endDateTime}:6"
            is CityGoodbye -> "${departureDateTime}:7"
            is TransitDeparture -> "${departureDateTime}:8"
            is BookingItem -> "${booking.startDateTime}:9"
        }
    }

    fun getDateTime(): String? {
        return when (this) {
            is TransitArrival -> arrivalDateTime
            is CityWelcome -> arrivalDateTime
            is HotelCheckIn -> booking.startDateTime
            is ActivityItem -> {
                activity.date?.let { date ->
                    val time = activity.startTime ?: when (activity.timeSlot) {
                        TimeSlot.MORNING -> "09:00:00"
                        TimeSlot.AFTERNOON -> "13:00:00"
                        TimeSlot.EVENING -> "17:00:00"
                        TimeSlot.FULL_DAY -> "09:00:00"
                        null -> "12:00:00"
                    }
                    "${date}T${time}"
                }
            }
            is NothingPlanned -> "${date}T06:00:00"
            is HotelCheckOut -> booking.endDateTime
            is CityGoodbye -> departureDateTime
            is TransitDeparture -> departureDateTime
            is BookingItem -> booking.startDateTime
        }
    }

    fun getKey(): String {
        return when (this) {
            is TransitArrival -> "transit-arrival-${location.id}-${arrivalBooking.id}"
            is CityWelcome -> "welcome-${location.id}"
            is HotelCheckIn -> "checkin-${booking.id}"
            is ActivityItem -> "activity-${activity.id}"
            is NothingPlanned -> "nothing-planned-${date}"
            is HotelCheckOut -> "checkout-${booking.id}"
            is CityGoodbye -> "goodbye-${location.id}"
            is TransitDeparture -> "transit-departure-${location.id}-${departureBooking.id}"
            is BookingItem -> "booking-${booking.id}"
        }
    }
}

/**
 * Build complete timeline with all dates in range
 * Shows "Nothing Planned" for days with no activities or bookings
 */
private fun buildCompleteTimeline(
    daySchedules: List<DaySchedule>,
    bookings: List<Booking>,
    locations: List<Location>
): List<TimelineItem> {
    val items = mutableListOf<TimelineItem>()

    // Extract all activities from day schedules
    val allActivities = daySchedules.flatMap { daySchedule ->
        daySchedule.activitiesByTimeSlot.values.flatten()
    }

    // Group bookings
    val transitBookings = bookings.filter {
        it.type in listOf(BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS)
    }
    val hotelBookings = bookings.filter { it.type == BookingType.HOTEL }
    val otherBookings = bookings.filter {
        it.type in listOf(BookingType.TICKET, BookingType.OTHER)
    }

    // Add city transitions and hotels for each location
    locations.sortedBy { it.order }.forEach { location ->
        val arrivalBooking = transitBookings.find { booking ->
            booking.toLocation?.contains(location.name, ignoreCase = true) == true
        }

        val hotelBooking = hotelBookings.find { booking ->
            val hotelLocation = booking.toLocation ?: booking.fromLocation
            hotelLocation?.contains(location.name, ignoreCase = true) == true
        }

        val departureBooking = transitBookings.find { booking ->
            booking.fromLocation?.contains(location.name, ignoreCase = true) == true
        }

        arrivalBooking?.let { arrival ->
            val arrivalTime = arrival.endDateTime ?: arrival.startDateTime
            items.add(TimelineItem.TransitArrival(location, arrivalTime, arrival))
            items.add(TimelineItem.CityWelcome(location, arrivalTime))
        }

        hotelBooking?.let { hotel ->
            items.add(TimelineItem.HotelCheckIn(hotel))
        }

        hotelBooking?.let { hotel ->
            items.add(TimelineItem.HotelCheckOut(hotel))
        }

        departureBooking?.let { departure ->
            items.add(TimelineItem.CityGoodbye(location, departure.startDateTime))
            items.add(TimelineItem.TransitDeparture(location, departure.startDateTime, departure))
        }
    }

    // Add other bookings
    otherBookings.forEach { booking ->
        items.add(TimelineItem.BookingItem(booking))
    }

    // Add all activities
    allActivities.forEach { activity ->
        items.add(TimelineItem.ActivityItem(activity))
    }

    // Get trip date range
    val tripStartDate = daySchedules.minByOrNull { it.date }?.date
    val tripEndDate = daySchedules.maxByOrNull { it.date }?.date

    // Fill in "Nothing Planned" for empty days
    if (tripStartDate != null && tripEndDate != null) {
        val start = LocalDate.parse(tripStartDate)
        val end = LocalDate.parse(tripEndDate)

        // Get all dates that have items
        val datesWithItems = items.mapNotNull { item ->
            item.getDateTime()?.let { extractDate(it) }
        }.toSet()

        // Generate all dates in range
        var currentDate = start
        while (!currentDate.isAfter(end)) {
            val dateString = currentDate.toString()

            // If no items exist for this date, add "Nothing Planned"
            if (dateString !in datesWithItems) {
                items.add(TimelineItem.NothingPlanned(dateString))
            }

            currentDate = currentDate.plusDays(1)
        }
    }

    // Sort chronologically
    return items.sortedWith(compareBy(
        { item ->
            try {
                val timestamp = item.getSortableTimestamp()
                val dateTimePart = timestamp.substringBeforeLast(":")
                // Try parsing as ZonedDateTime first (bookings with timezone)
                try {
                    ZonedDateTime.parse(dateTimePart).toInstant().toEpochMilli()
                } catch (e: Exception) {
                    // If that fails, parse as LocalDateTime (activities without timezone)
                    java.time.LocalDateTime.parse(dateTimePart).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
                }
            } catch (e: Exception) {
                Long.MAX_VALUE
            }
        },
        { item -> item.getSortableTimestamp().substringAfterLast(":").toIntOrNull() ?: 99 }
    ))
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
