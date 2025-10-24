package com.aurora.travlogue.feature.tripdetail.domain.service

import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import com.aurora.travlogue.core.domain.model.Location
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime

/**
 * Service for building timeline from day schedules, bookings, and locations
 * Shared across all platforms (Android, iOS, Desktop)
 */
class TimelineBuilder {

    /**
     * Build complete timeline with all dates in range
     * Shows "Nothing Planned" for days with no activities or bookings
     */
    fun buildCompleteTimeline(
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

        // Track which transit bookings we've added to avoid duplicates
        val processedTransitBookings = mutableSetOf<String>()

        // Add any transit bookings that don't match locations (e.g., departing from origin city)
        // These are typically the first and last legs of the journey
        transitBookings.forEach { booking ->
            val departureMatchesLocation = locations.any { location ->
                booking.fromLocation?.contains(location.name, ignoreCase = true) == true
            }
            val arrivalMatchesLocation = locations.any { location ->
                booking.toLocation?.contains(location.name, ignoreCase = true) == true
            }

            // If departing from a non-location (origin city), add departure card
            val fromLocation = booking.fromLocation
            if (!departureMatchesLocation && fromLocation != null) {
                // Extract origin city from fromLocation (e.g., "San Francisco (SFO)" -> "San Francisco")
                val originCity = fromLocation.substringBefore("(").trim()
                items.add(TimelineItem.OriginDeparture(booking, originCity))
                items.add(TimelineItem.InTransit(booking))
                processedTransitBookings.add(booking.id)
            }
            // If arriving at a non-location (return to origin), just add transit card
            else if (!arrivalMatchesLocation && booking.toLocation != null) {
                if (booking.id !in processedTransitBookings) {
                    items.add(TimelineItem.InTransit(booking))
                    processedTransitBookings.add(booking.id)
                }
            }
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

                // Add InTransit card after departure (only once per booking)
                if (departure.id !in processedTransitBookings) {
                    items.add(TimelineItem.InTransit(departure))
                    processedTransitBookings.add(departure.id)
                }
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
            while (currentDate <= end) {
                val dateString = currentDate.toString()

                // If no items exist for this date, add "Nothing Planned"
                if (dateString !in datesWithItems) {
                    items.add(TimelineItem.NothingPlanned(dateString))
                }

                currentDate = currentDate.plus(kotlinx.datetime.DatePeriod(days = 1))
            }
        }

        // Sort chronologically
        return items.sortedWith(compareBy(
            { item ->
                try {
                    val timestamp = item.getSortableTimestamp()
                    val dateTimePart = timestamp.substringBefore("|")
                    // Try parsing as Instant (bookings with timezone)
                    try {
                        Instant.parse(dateTimePart).toEpochMilliseconds()
                    } catch (e: Exception) {
                        // If that fails, parse as date-time string and assign a default time
                        try {
                            val localDateTime = kotlinx.datetime.LocalDateTime.parse(dateTimePart)
                            localDateTime.toInstant(TimeZone.UTC).toEpochMilliseconds()
                        } catch (e2: Exception) {
                            Long.MAX_VALUE
                        }
                    }
                } catch (e: Exception) {
                    Long.MAX_VALUE
                }
            },
            { item -> item.getSortableTimestamp().substringAfter("|").toDoubleOrNull() ?: 99.0 }
        ))
    }

    /**
     * Extract date from ISO datetime for comparison
     * Returns date string in format: YYYY-MM-DD
     */
    private fun extractDate(isoDateTime: String?): String? {
        if (isoDateTime == null) return null
        return try {
            val instant = Instant.parse(isoDateTime)
            instant.toLocalDateTime(TimeZone.UTC).date.toString()
        } catch (e: Exception) {
            // Try parsing as just a date
            try {
                LocalDate.parse(isoDateTime.substringBefore('T')).toString()
            } catch (e2: Exception) {
                null
            }
        }
    }
}
