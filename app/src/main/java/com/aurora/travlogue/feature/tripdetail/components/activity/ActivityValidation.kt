package com.aurora.travlogue.feature.tripdetail.components.activity

import com.aurora.travlogue.core.domain.model.Location
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Data class to hold validation result
 */
data class TimeValidationResult(
    val isValid: Boolean,
    val errorMessage: String = ""
)

/**
 * Validates that an activity falls within the location's arrival and departure time window.
 *
 * Logic:
 * - If location has no arrival/departure times, always valid (no constraint)
 * - If activity has no date, always valid (will be assigned later)
 * - If activity has date but no specific time, validate date is within range
 * - If activity has specific start/end time, validate both date and time are within range
 */
fun validateActivityTimeWindow(
    location: Location?,
    activityDate: String?,
    activityStartTime: LocalTime?,
    activityEndTime: LocalTime?
): TimeValidationResult {
    // No location selected
    if (location == null) {
        return TimeValidationResult(isValid = true)
    }

    // Location has no time constraints
    if (location.arrivalDateTime == null || location.departureDateTime == null) {
        return TimeValidationResult(isValid = true)
    }

    // Activity has no date selected
    if (activityDate.isNullOrBlank()) {
        return TimeValidationResult(isValid = true)
    }

    // Parse location arrival and departure times
    val arrivalTime = try {
        ZonedDateTime.parse(location.arrivalDateTime)
    } catch (e: Exception) {
        return TimeValidationResult(isValid = true) // Can't validate, allow
    }

    val departureTime = try {
        ZonedDateTime.parse(location.departureDateTime)
    } catch (e: Exception) {
        return TimeValidationResult(isValid = true) // Can't validate, allow
    }

    // Parse activity date
    val activityLocalDate = try {
        LocalDate.parse(activityDate)
    } catch (e: Exception) {
        return TimeValidationResult(isValid = true) // Can't validate, allow
    }

    // If no specific time is set, just validate date is within range
    if (activityStartTime == null) {
        val arrivalDate = arrivalTime.toLocalDate()
        val departureDate = departureTime.toLocalDate()

        return when {
            activityLocalDate.isBefore(arrivalDate) -> {
                TimeValidationResult(
                    isValid = false,
                    errorMessage = "Activity cannot be before arrival at ${location.name} on ${formatDate(arrivalDate)}"
                )
            }
            activityLocalDate.isAfter(departureDate) -> {
                TimeValidationResult(
                    isValid = false,
                    errorMessage = "Activity cannot be after departure from ${location.name} on ${formatDate(departureDate)}"
                )
            }
            else -> TimeValidationResult(isValid = true)
        }
    }

    // Specific time is set - validate datetime precisely
    val activityStartDateTime = LocalDateTime.of(activityLocalDate, activityStartTime)
    val activityEndDateTime = if (activityEndTime != null) {
        LocalDateTime.of(activityLocalDate, activityEndTime)
    } else {
        activityStartDateTime.plusHours(1) // Default 1 hour duration
    }

    val arrivalLocalDateTime = arrivalTime.toLocalDateTime()
    val departureLocalDateTime = departureTime.toLocalDateTime()

    val arrivalDateTimeStr = location.arrivalDateTime
    val departureDateTimeStr = location.departureDateTime

    return when {
        activityStartDateTime.isBefore(arrivalLocalDateTime) -> {
            TimeValidationResult(
                isValid = false,
                errorMessage = "Activity starts before arrival at ${location.name} (${arrivalDateTimeStr?.let { formatDateTime(it) }})"
            )
        }
        activityEndDateTime.isAfter(departureLocalDateTime) -> {
            TimeValidationResult(
                isValid = false,
                errorMessage = "Activity ends after departure from ${location.name} (${departureDateTimeStr?.let { formatDateTime(it) }})"
            )
        }
        else -> TimeValidationResult(isValid = true)
    }
}

/**
 * Formats ZonedDateTime string to readable format
 * Input: "2025-07-02T14:30:00+09:00"
 * Output: "Jul 2, 2:30 PM"
 */
fun formatDateTime(dateTimeString: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateTimeString)
        zonedDateTime.format(DateTimeFormatter.ofPattern("MMM d, h:mm a"))
    } catch (e: Exception) {
        dateTimeString
    }
}

/**
 * Formats LocalDate to readable format
 * Input: 2025-07-02
 * Output: "Jul 2"
 */
fun formatDate(date: LocalDate): String {
    return date.format(DateTimeFormatter.ofPattern("MMM d"))
}
