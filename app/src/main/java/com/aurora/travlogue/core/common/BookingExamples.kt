package com.aurora.travlogue.core.common

import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import com.aurora.travlogue.core.common.DateTimeUtils.formatBookingTimeForDisplay
import com.aurora.travlogue.core.common.DateTimeUtils.formatTimeForDisplay
import com.aurora.travlogue.core.common.DateTimeUtils.toIsoString
import com.aurora.travlogue.core.common.DateTimeUtils.createZonedDateTime
import com.aurora.travlogue.core.common.DateTimeUtils.durationInMinutes
import com.aurora.travlogue.core.common.DateTimeUtils.formatDuration
import java.time.LocalDateTime

/**
 * Example usage of the Booking entity with timezone support.
 *
 * This file demonstrates best practices for creating and displaying bookings.
 */
object BookingExamples {

    /**
     * Example 1: Creating a flight booking
     *
     * Flight from New York (JFK) to Madrid (MAD)
     * Departure: Nov 15, 2025 at 8:30 PM EST
     * Arrival: Nov 16, 2025 at 10:45 AM CET
     */
    fun createFlightBooking(tripId: String): Booking {
        // Departure time in New York timezone
        val departureTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 15, 20, 30),
            "America/New_York"
        )

        // Arrival time in Madrid timezone
        val arrivalTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 16, 10, 45),
            "Europe/Madrid"
        )

        return Booking(
            tripId = tripId,
            type = BookingType.FLIGHT,
            confirmationNumber = "ABC123",
            provider = "Iberia",
            startDateTime = departureTime.toIsoString(), // "2025-11-15T20:30:00-05:00"
            endDateTime = arrivalTime.toIsoString(),     // "2025-11-16T10:45:00+01:00"
            timezone = "America/New_York", // Departure timezone
            fromLocation = "New York (JFK)",
            toLocation = "Madrid (MAD)",
            price = 650.00,
            currency = "USD",
            notes = "Non-stop flight, 7h 15m duration",
            imageUri = null
        )
    }

    /**
     * Example 2: Creating a hotel booking
     *
     * Hotel in Barcelona
     * Check-in: Nov 17, 2025 at 3:00 PM CET
     * Check-out: Nov 20, 2025 at 11:00 AM CET
     */
    fun createHotelBooking(tripId: String): Booking {
        val checkInTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 17, 15, 0),
            "Europe/Madrid"
        )

        val checkOutTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 20, 11, 0),
            "Europe/Madrid"
        )

        return Booking(
            tripId = tripId,
            type = BookingType.HOTEL,
            confirmationNumber = "HTL456",
            provider = "Hotel Barcelona Plaza",
            startDateTime = checkInTime.toIsoString(),
            endDateTime = checkOutTime.toIsoString(),
            timezone = "Europe/Madrid",
            fromLocation = null,
            toLocation = null,
            price = 450.00,
            currency = "EUR",
            notes = "3 nights, Double room with breakfast included",
            imageUri = null
        )
    }

    /**
     * Example 3: Creating a train booking
     *
     * Train from Madrid to Barcelona
     * Departure: Nov 16, 2025 at 2:30 PM CET
     * Arrival: Nov 16, 2025 at 5:15 PM CET
     */
    fun createTrainBooking(tripId: String): Booking {
        val departureTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 16, 14, 30),
            "Europe/Madrid"
        )

        val arrivalTime = createZonedDateTime(
            LocalDateTime.of(2025, 11, 16, 17, 15),
            "Europe/Madrid"
        )

        return Booking(
            tripId = tripId,
            type = BookingType.TRAIN,
            confirmationNumber = "REN789",
            provider = "Renfe AVE",
            startDateTime = departureTime.toIsoString(),
            endDateTime = arrivalTime.toIsoString(),
            timezone = "Europe/Madrid",
            fromLocation = "Madrid Atocha",
            toLocation = "Barcelona Sants",
            price = 85.00,
            currency = "EUR",
            notes = "High-speed train, seat 14A",
            imageUri = null
        )
    }

    /**
     * Example 4: Displaying booking information
     *
     * Shows how to format booking times for display in the UI
     */
    fun displayBookingInfo(booking: Booking): String {
        return buildString {
            appendLine("=== ${booking.type} Booking ===")
            appendLine("Provider: ${booking.provider}")
            appendLine("Confirmation: ${booking.confirmationNumber}")
            appendLine()

            // Display departure time in user's local timezone
            appendLine("Departure: ${booking.startDateTime.formatBookingTimeForDisplay()}")

            val endDateTime = booking.endDateTime
            if (endDateTime != null) {
                appendLine("Arrival: ${endDateTime.formatBookingTimeForDisplay()}")

                // Calculate and display duration
                val durationMinutes = durationInMinutes(
                    booking.startDateTime,
                    endDateTime
                )
                appendLine("Duration: ${formatDuration(durationMinutes)}")
            }

            appendLine()

            if (booking.fromLocation != null) {
                appendLine("From: ${booking.fromLocation}")
            }
            if (booking.toLocation != null) {
                appendLine("To: ${booking.toLocation}")
            }

            if (booking.price != null) {
                appendLine("Price: ${booking.currency} ${booking.price}")
            }

            if (!booking.notes.isNullOrEmpty()) {
                appendLine()
                appendLine("Notes: ${booking.notes}")
            }
        }
    }

    /**
     * Example 5: Compact display for list items
     *
     * Shows how to display booking in a compact format (e.g., in a list)
     */
    fun compactBookingDisplay(booking: Booking): String {
        val time = booking.startDateTime.formatTimeForDisplay()
        val route = when {
            booking.fromLocation != null && booking.toLocation != null ->
                "${booking.fromLocation} → ${booking.toLocation}"
            else -> booking.provider
        }

        return "$time • $route"
    }
}

/**
 * Usage in ViewModel or UI:
 *
 * ```kotlin
 * // Creating a booking
 * viewModelScope.launch {
 *     val booking = BookingExamples.createFlightBooking(tripId)
 *     bookingRepository.insertBooking(booking)
 * }
 *
 * // Displaying booking info
 * Text(text = BookingExamples.displayBookingInfo(booking))
 *
 * // Compact display in list
 * items(bookings) { booking ->
 *     Text(text = BookingExamples.compactBookingDisplay(booking))
 * }
 * ```
 */
