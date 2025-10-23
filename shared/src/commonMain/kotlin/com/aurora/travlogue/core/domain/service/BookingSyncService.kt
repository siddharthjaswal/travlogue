package com.aurora.travlogue.core.domain.service

import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import com.aurora.travlogue.core.domain.model.Location

/**
 * Service responsible for synchronizing booking data with location arrival/departure times - KMP version.
 *
 * When transit bookings (flights, trains, buses) are added or updated, this service
 * automatically updates the corresponding location's arrivalDateTime and departureDateTime.
 *
 * Note: This is the domain service. Repository interaction will be handled by use cases.
 */
class BookingSyncService {

    /**
     * Determines which locations need to be updated based on bookings.
     * Returns a list of updated locations.
     *
     * @param locations All locations for the trip
     * @param bookings All bookings for the trip
     * @return List of locations that have been updated
     */
    fun syncBookingsWithLocations(
        locations: List<Location>,
        bookings: List<Booking>
    ): List<Location> {
        // Filter to only transit bookings (those that represent movement)
        val transitBookings = bookings.filter { it.isTransitBooking() }

        // Update each location based on transit bookings
        return locations.map { location ->
            updateLocationWithBookings(location, transitBookings)
        }.filter { updatedLocation ->
            // Only return locations that actually changed
            locations.find { it.id == updatedLocation.id } != updatedLocation
        }
    }

    /**
     * Updates a single location's arrival and departure times based on transit bookings.
     *
     * Logic:
     * - Arrival time: Set from the booking that arrives AT this location (toLocation matches)
     * - Departure time: Set from the booking that departs FROM this location (fromLocation matches)
     *
     * @param location The location to update
     * @param transitBookings All transit bookings for the trip
     * @return Updated location with new arrival/departure times, or original if no changes
     */
    fun updateLocationWithBookings(
        location: Location,
        transitBookings: List<Booking>
    ): Location {
        // Find booking that arrives at this location
        val arrivalBooking = transitBookings.find { booking ->
            booking.toLocation?.let { toLocation ->
                locationNamesMatch(location.name, toLocation)
            } ?: false
        }

        // Find booking that departs from this location
        val departureBooking = transitBookings.find { booking ->
            booking.fromLocation?.let { fromLocation ->
                locationNamesMatch(location.name, fromLocation)
            } ?: false
        }

        // Determine new arrival and departure times
        val newArrivalDateTime = arrivalBooking?.endDateTime ?: location.arrivalDateTime
        val newDepartureDateTime = departureBooking?.startDateTime ?: location.departureDateTime

        // Only update if something changed
        return if (newArrivalDateTime != location.arrivalDateTime ||
                   newDepartureDateTime != location.departureDateTime) {
            location.copy(
                arrivalDateTime = newArrivalDateTime,
                departureDateTime = newDepartureDateTime
            )
        } else {
            location
        }
    }

    /**
     * Determines if a location name matches a booking location string.
     * Handles variations like:
     * - "Tokyo" matches "Tokyo (NRT)"
     * - "Barcelona" matches "Barcelona El Prat"
     * - Case-insensitive matching
     *
     * @param locationName The location name from the Location entity
     * @param bookingLocation The location string from the Booking entity
     * @return true if they match, false otherwise
     */
    private fun locationNamesMatch(locationName: String, bookingLocation: String): Boolean {
        val cleanLocation = locationName.trim().lowercase()
        val cleanBooking = bookingLocation.trim().lowercase()

        // Exact match
        if (cleanLocation == cleanBooking) return true

        // Check if booking location contains the location name
        // e.g., "Tokyo" is in "Tokyo (NRT)"
        if (cleanBooking.contains(cleanLocation)) return true

        // Check if location name contains the booking location
        // Less common, but handles cases like location="Tokyo Narita" and booking="Tokyo"
        if (cleanLocation.contains(cleanBooking)) return true

        return false
    }

    /**
     * Extension function to determine if a booking represents transit/movement
     */
    private fun Booking.isTransitBooking(): Boolean {
        return type in listOf(BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS)
    }
}
