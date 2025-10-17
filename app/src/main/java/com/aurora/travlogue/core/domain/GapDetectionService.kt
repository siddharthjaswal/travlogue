package com.aurora.travlogue.core.domain

import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.GapType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for detecting gaps in trip itineraries.
 *
 * Detects:
 * 1. MISSING_TRANSIT - Location changes without transit bookings
 * 2. UNPLANNED_DAY - Days with no planned activities or locations
 */
@Singleton
class GapDetectionService @Inject constructor() {

    /**
     * Detect all gaps for a given trip.
     *
     * @param trip The trip to analyze
     * @param locations List of locations ordered by trip sequence
     * @param bookings List of bookings for the trip
     * @return List of detected gaps
     */
    fun detectGaps(
        trip: Trip,
        locations: List<Location>,
        bookings: List<Booking>
    ): List<Gap> {
        val gaps = mutableListOf<Gap>()

        // Only detect gaps for FIXED date trips
        if (trip.dateType == DateType.FIXED && trip.startDate != null && trip.endDate != null) {
            // Detect missing transit between locations
            gaps.addAll(detectMissingTransit(trip.id, locations, bookings))

            // Detect unplanned days
            gaps.addAll(detectUnplannedDays(trip, locations))
        }

        return gaps
    }

    /**
     * Detect missing transit between consecutive locations with different cities.
     *
     * Logic:
     * - Compare consecutive locations by order
     * - If location names differ, check for transit booking
     * - Transit bookings: FLIGHT, TRAIN, BUS
     * - Create gap if no transit found
     */
    private fun detectMissingTransit(
        tripId: String,
        locations: List<Location>,
        bookings: List<Booking>
    ): List<Gap> {
        val gaps = mutableListOf<Gap>()
        val sortedLocations = locations.sortedBy { it.order }

        // Get transit bookings (flights, trains, buses)
        val transitBookings = bookings.filter {
            it.type == BookingType.FLIGHT ||
            it.type == BookingType.TRAIN ||
            it.type == BookingType.BUS
        }

        // Check each consecutive pair of locations
        for (i in 0 until sortedLocations.size - 1) {
            val fromLocation = sortedLocations[i]
            val toLocation = sortedLocations[i + 1]

            // Skip if locations are in the same city
            if (fromLocation.name.equals(toLocation.name, ignoreCase = true)) {
                continue
            }

            // Check if there's a transit booking covering this route
            val hasTransit = transitBookings.any { booking ->
                // Check if booking connects these locations
                val fromMatches = booking.fromLocation?.contains(fromLocation.name, ignoreCase = true) == true
                val toMatches = booking.toLocation?.contains(toLocation.name, ignoreCase = true) == true

                fromMatches && toMatches
            }

            // Create gap if no transit found
            if (!hasTransit) {
                // Determine date range for the gap
                val fromDate = fromLocation.date ?: continue
                val toDate = toLocation.date ?: continue

                // Only create gap if toDate is after fromDate
                val from = LocalDate.parse(fromDate)
                val to = LocalDate.parse(toDate)

                if (to.isAfter(from)) {
                    gaps.add(
                        Gap(
                            tripId = tripId,
                            gapType = GapType.MISSING_TRANSIT,
                            fromLocationId = fromLocation.id,
                            toLocationId = toLocation.id,
                            fromDate = fromDate,
                            toDate = toDate,
                            isResolved = false
                        )
                    )
                }
            }
        }

        return gaps
    }

    /**
     * Detect unplanned days in the trip (days with no assigned location).
     *
     * Logic:
     * - Iterate through all days in trip date range
     * - Check if each day has a location assigned
     * - Create gap for consecutive unplanned days
     */
    private fun detectUnplannedDays(
        trip: Trip,
        locations: List<Location>
    ): List<Gap> {
        val gaps = mutableListOf<Gap>()

        val startDate = LocalDate.parse(trip.startDate)
        val endDate = LocalDate.parse(trip.endDate)
        val dayCount = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1

        // Map dates to locations
        val dateToLocation = locations
            .filter { it.date != null }
            .associateBy { it.date!! }

        var gapStartDate: LocalDate? = null
        var gapStartLocation: Location? = null

        for (dayIndex in 0 until dayCount) {
            val currentDate = startDate.plusDays(dayIndex.toLong())
            val dateString = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
            val hasLocation = dateToLocation.containsKey(dateString)

            if (!hasLocation) {
                // Start tracking unplanned day gap
                if (gapStartDate == null) {
                    gapStartDate = currentDate
                    // Find the last planned location before this gap
                    gapStartLocation = dateToLocation.entries
                        .filter { LocalDate.parse(it.key).isBefore(currentDate) }
                        .maxByOrNull { it.key }
                        ?.value
                }
            } else {
                // If we were tracking a gap, close it
                if (gapStartDate != null) {
                    val gapEndDate = currentDate.minusDays(1)
                    val endLocation = dateToLocation[dateString]

                    // Only create gap if we have both start and end locations
                    if (gapStartLocation != null && endLocation != null) {
                        gaps.add(
                            Gap(
                                tripId = trip.id,
                                gapType = GapType.UNPLANNED_DAY,
                                fromLocationId = gapStartLocation.id,
                                toLocationId = endLocation.id,
                                fromDate = gapStartDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                                toDate = gapEndDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                                isResolved = false
                            )
                        )
                    }

                    gapStartDate = null
                    gapStartLocation = null
                }
            }
        }

        // Handle gap that extends to the end of the trip
        if (gapStartDate != null && gapStartLocation != null) {
            // Find any location after the gap start
            val endLocation = locations.sortedBy { it.order }.lastOrNull()

            if (endLocation != null) {
                gaps.add(
                    Gap(
                        tripId = trip.id,
                        gapType = GapType.UNPLANNED_DAY,
                        fromLocationId = gapStartLocation.id,
                        toLocationId = endLocation.id,
                        fromDate = gapStartDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        toDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        isResolved = false
                    )
                )
            }
        }

        return gaps
    }

    /**
     * Calculate the duration of a gap in days.
     */
    fun getGapDurationDays(gap: Gap): Long {
        val from = LocalDate.parse(gap.fromDate)
        val to = LocalDate.parse(gap.toDate)
        return ChronoUnit.DAYS.between(from, to) + 1
    }

    /**
     * Get a human-readable description of the gap.
     */
    fun getGapDescription(gap: Gap, fromLocation: Location?, toLocation: Location?): String {
        return when (gap.gapType) {
            GapType.MISSING_TRANSIT -> {
                val from = fromLocation?.name ?: "Unknown"
                val to = toLocation?.name ?: "Unknown"
                "Missing transit from $from to $to"
            }
            GapType.UNPLANNED_DAY -> {
                val days = getGapDurationDays(gap)
                val dayText = if (days == 1L) "day" else "days"
                "$days unplanned $dayText"
            }
            GapType.TIME_CONFLICT -> {
                "Schedule conflict detected"
            }
        }
    }
}
