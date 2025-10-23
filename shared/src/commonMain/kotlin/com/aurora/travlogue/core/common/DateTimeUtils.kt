package com.aurora.travlogue.core.common

import kotlinx.datetime.*
import kotlinx.datetime.format.*

/**
 * Utility object for handling date and time conversions in Travlogue - KMP version.
 *
 * Date/Time Format Strategy:
 * - Trip dates: ISO date strings ("2025-11-15")
 * - Booking times: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
 * - System timestamps: Long (milliseconds since epoch)
 */
object DateTimeUtils {

    // ==================== Date Conversions (for Trips, Locations, Activities) ====================

    /**
     * Convert LocalDate to ISO string format
     * Example: 2025-11-15 → "2025-11-15"
     */
    fun LocalDate.toIsoString(): String {
        return this.toString()
    }

    /**
     * Parse ISO date string to LocalDate
     * Example: "2025-11-15" → LocalDate(2025, 11, 15)
     */
    fun String.toLocalDate(): LocalDate {
        return LocalDate.parse(this)
    }

    /**
     * Format LocalDate for display
     * Example: 2025-11-15 → "Nov 15, 2025"
     */
    fun LocalDate.toDisplayString(): String {
        val monthName = when (this.month) {
            Month.JANUARY -> "Jan"
            Month.FEBRUARY -> "Feb"
            Month.MARCH -> "Mar"
            Month.APRIL -> "Apr"
            Month.MAY -> "May"
            Month.JUNE -> "Jun"
            Month.JULY -> "Jul"
            Month.AUGUST -> "Aug"
            Month.SEPTEMBER -> "Sep"
            Month.OCTOBER -> "Oct"
            Month.NOVEMBER -> "Nov"
            Month.DECEMBER -> "Dec"
            else -> ""
        }
        return "$monthName ${this.dayOfMonth}, ${this.year}"
    }

    /**
     * Parse ISO date string and format for display
     * Example: "2025-11-15" → "Nov 15, 2025"
     */
    fun String.formatDateForDisplay(): String {
        return this.toLocalDate().toDisplayString()
    }

    // ==================== DateTime Conversions (for Bookings) ====================

    /**
     * Parse ISO 8601 datetime string to Instant
     * Example: "2025-11-15T14:30:00+01:00" → Instant
     */
    fun String.toInstant(): Instant {
        return Instant.parse(this)
    }

    /**
     * Convert Instant to ISO 8601 string
     * Example: Instant → "2025-11-15T14:30:00Z"
     */
    fun Instant.toIsoString(): String {
        return this.toString()
    }

    /**
     * Convert Instant to LocalDateTime in specified timezone
     */
    fun Instant.toLocalDateTimeInTimezone(timezone: String): LocalDateTime {
        val tz = TimeZone.of(timezone)
        return this.toLocalDateTime(tz)
    }

    /**
     * Create Instant from LocalDateTime and timezone string
     * Example: (2025-11-15T14:30:00, "Europe/Madrid") → Instant
     */
    fun createInstant(localDateTime: LocalDateTime, timezone: String): Instant {
        val tz = TimeZone.of(timezone)
        return localDateTime.toInstant(tz)
    }

    /**
     * Get current time in specified timezone as ISO string
     * Example: ("Europe/Madrid") → "2025-11-15T14:30:00+01:00"
     */
    fun getCurrentTimeInTimezone(timezone: String): String {
        val now = Clock.System.now()
        val tz = TimeZone.of(timezone)
        val localDateTime = now.toLocalDateTime(tz)
        return createInstant(localDateTime, timezone).toString()
    }

    // ==================== Display Formatting ====================

    /**
     * Format booking time for display in system timezone
     * Example: "2025-11-15T14:30:00+01:00" → "Nov 15, 2025 at 2:30 PM"
     */
    fun String.formatBookingTimeForDisplay(): String {
        val instant = this.toInstant()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatLocalDateTimeForDisplay(localDateTime)
    }

    /**
     * Format LocalDateTime for display
     * Example: 2025-11-15T14:30 → "Nov 15, 2025 at 2:30 PM"
     */
    private fun formatLocalDateTimeForDisplay(dateTime: LocalDateTime): String {
        val date = dateTime.date.toDisplayString()
        val time = formatTimeOnly(dateTime.time)
        return "$date at $time"
    }

    /**
     * Format time only for display
     * Example: "2025-11-15T14:30:00+01:00" → "2:30 PM"
     */
    fun String.formatTimeForDisplay(): String {
        val instant = this.toInstant()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatTimeOnly(localDateTime.time)
    }

    /**
     * Format LocalTime to 12-hour format
     * Example: 14:30 → "2:30 PM"
     */
    private fun formatTimeOnly(time: LocalTime): String {
        val hour = time.hour
        val minute = time.minute

        val displayHour = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }

        val period = if (hour < 12) "AM" else "PM"
        val minuteStr = minute.toString().padStart(2, '0')

        return "$displayHour:$minuteStr $period"
    }

    /**
     * Get timezone display name (simplified for KMP)
     * Example: "Europe/Madrid" → "Europe/Madrid"
     */
    fun getTimezoneDisplayName(timezone: String): String {
        // kotlinx-datetime doesn't provide display names yet
        // Return the timezone ID as-is
        return timezone.replace("_", " ")
    }

    // ==================== Timestamp Conversions (for System Fields) ====================

    /**
     * Convert timestamp to LocalDate in system timezone
     * Example: 1699920000000L → LocalDate(2023, 11, 14)
     */
    fun Long.toLocalDate(): LocalDate {
        val instant = Instant.fromEpochMilliseconds(this)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    /**
     * Convert timestamp to formatted display string
     * Example: 1699920000000L → "Nov 14, 2023"
     */
    fun Long.toDisplayString(): String {
        return this.toLocalDate().toDisplayString()
    }

    // ==================== Date Calculations ====================

    /**
     * Calculate days between two ISO date strings
     * Example: ("2025-11-15", "2025-11-20") → 5
     */
    fun daysBetween(startDate: String, endDate: String): Long {
        val start = startDate.toLocalDate()
        val end = endDate.toLocalDate()
        return (end.toEpochDays() - start.toEpochDays()).toLong()
    }

    /**
     * Calculate duration between two booking times in minutes
     * Example: ("2025-11-15T14:30:00+01:00", "2025-11-15T16:45:00+01:00") → 135
     */
    fun durationInMinutes(startDateTime: String, endDateTime: String): Long {
        val start = startDateTime.toInstant()
        val end = endDateTime.toInstant()
        return (end - start).inWholeMinutes
    }

    /**
     * Format duration in minutes to human-readable string
     * Example: 135 → "2h 15m"
     */
    fun formatDuration(minutes: Long): String {
        val hours = minutes / 60
        val mins = minutes % 60
        return when {
            hours > 0 && mins > 0 -> "${hours}h ${mins}m"
            hours > 0 -> "${hours}h"
            else -> "${mins}m"
        }
    }

    // ==================== Validation ====================

    /**
     * Check if date string is valid ISO format
     */
    fun isValidIsoDate(dateString: String): Boolean {
        return try {
            dateString.toLocalDate()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Check if datetime string is valid ISO 8601 format
     */
    fun isValidIsoDateTime(dateTimeString: String): Boolean {
        return try {
            dateTimeString.toInstant()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Check if timezone string is valid IANA timezone
     */
    fun isValidTimezone(timezone: String): Boolean {
        return try {
            TimeZone.of(timezone)
            true
        } catch (e: Exception) {
            false
        }
    }

    // ==================== Helper Extensions ====================

    /**
     * Add days to a LocalDate
     */
    fun LocalDate.plusDays(days: Long): LocalDate {
        return LocalDate.fromEpochDays(this.toEpochDays() + days.toInt())
    }

    /**
     * Check if one date is after another
     */
    fun LocalDate.isAfter(other: LocalDate): Boolean {
        return this > other
    }

    /**
     * Check if one date is before another
     */
    fun LocalDate.isBefore(other: LocalDate): Boolean {
        return this < other
    }
}
