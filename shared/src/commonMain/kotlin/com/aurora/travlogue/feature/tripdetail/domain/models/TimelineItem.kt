package com.aurora.travlogue.feature.tripdetail.domain.models

import com.aurora.travlogue.core.domain.model.Activity
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.Location
import com.aurora.travlogue.core.domain.model.TimeSlot

/**
 * Sealed class for timeline items
 * Used by all platforms to build a chronological timeline
 */
sealed class TimelineItem {
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

    data class InTransit(
        val booking: Booking
    ) : TimelineItem()

    data class OriginDeparture(
        val booking: Booking,
        val originCity: String
    ) : TimelineItem()

    data class BookingItem(
        val booking: Booking
    ) : TimelineItem()

    fun getSortableTimestamp(): String {
        return when (this) {
            is TransitArrival -> "${arrivalDateTime}|1"
            is CityWelcome -> "${arrivalDateTime}|2"
            is HotelCheckIn -> "${booking.startDateTime}|3"
            is ActivityItem -> {
                val date = activity.date ?: "9999-12-31"
                val time = activity.startTime ?: when (activity.timeSlot) {
                    TimeSlot.MORNING -> "09:00:00"
                    TimeSlot.AFTERNOON -> "13:00:00"
                    TimeSlot.EVENING -> "17:00:00"
                    TimeSlot.FULL_DAY -> "09:00:00"
                    null -> "12:00:00"
                }
                "${date}T${time}|4"
            }

            is NothingPlanned -> "${date}T06:00:00|5"
            is HotelCheckOut -> "${booking.endDateTime}|6"
            is CityGoodbye -> "${departureDateTime}|7"
            is TransitDeparture -> "${departureDateTime}|8"
            is InTransit -> "${booking.startDateTime}|8.5" // Between departure and arrival
            is OriginDeparture -> "${booking.startDateTime}|0" // Very first item
            is BookingItem -> "${booking.startDateTime}|9"
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
            is InTransit -> booking.startDateTime
            is OriginDeparture -> booking.startDateTime
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
            is InTransit -> "in-transit-${booking.id}"
            is OriginDeparture -> "origin-departure-${booking.id}"
            is BookingItem -> "booking-${booking.id}"
        }
    }
}
