package com.aurora.travlogue.core.common

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Utility object for handling date and time conversions in Travlogue.
 *
 * Date/Time Format Strategy:
 * - Trip dates: ISO date strings ("2025-11-15")
 * - Booking times: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
 * - System timestamps: Long (milliseconds since epoch)
 */
object DateTimeUtils {

    // ==================== Date Formats ====================

    private val ISO_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE // "2025-11-15"
    private val ISO_DATETIME_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME // "2025-11-15T14:30:00+01:00"
    private val DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy") // "Nov 15, 2025"
    private val DISPLAY_TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a") // "2:30 PM"
    private val DISPLAY_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a") // "Nov 15, 2025 at 2:30 PM"

    // ==================== Date Conversions (for Trips, Locations, Activities) ====================

    /**
     * Convert LocalDate to ISO string format
     * Example: 2025-11-15 → "2025-11-15"
     */
    fun LocalDate.toIsoString(): String {
        return this.format(ISO_DATE_FORMATTER)
    }

    /**
     * Parse ISO date string to LocalDate
     * Example: "2025-11-15" → LocalDate(2025, 11, 15)
     */
    fun String.toLocalDate(): LocalDate {
        return LocalDate.parse(this, ISO_DATE_FORMATTER)
    }

    /**
     * Format LocalDate for display
     * Example: 2025-11-15 → "Nov 15, 2025"
     */
    fun LocalDate.toDisplayString(): String {
        return this.format(DISPLAY_DATE_FORMATTER)
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
     * Convert ZonedDateTime to ISO 8601 string with timezone
     * Example: 2025-11-15T14:30:00+01:00[Europe/Madrid] → "2025-11-15T14:30:00+01:00"
     */
    fun ZonedDateTime.toIsoString(): String {
        return this.format(ISO_DATETIME_FORMATTER)
    }

    /**
     * Parse ISO 8601 datetime string to ZonedDateTime
     * Example: "2025-11-15T14:30:00+01:00" → ZonedDateTime
     */
    fun String.toZonedDateTime(): ZonedDateTime {
        return ZonedDateTime.parse(this, ISO_DATETIME_FORMATTER)
    }

    /**
     * Convert ZonedDateTime to user's local timezone
     * Example: 2025-11-15T14:30:00+01:00 → 2025-11-15T08:30:00-05:00 (if user is in EST)
     */
    fun ZonedDateTime.toLocalTimezone(): ZonedDateTime {
        return this.withZoneSameInstant(ZoneId.systemDefault())
    }

    /**
     * Create ZonedDateTime from LocalDateTime and timezone string
     * Example: (2025-11-15T14:30:00, "Europe/Madrid") → 2025-11-15T14:30:00+01:00[Europe/Madrid]
     */
    fun createZonedDateTime(localDateTime: LocalDateTime, timezone: String): ZonedDateTime {
        return localDateTime.atZone(ZoneId.of(timezone))
    }

    /**
     * Get current time in specified timezone as ISO string
     * Example: ("Europe/Madrid") → "2025-11-15T14:30:00+01:00"
     */
    fun getCurrentTimeInTimezone(timezone: String): String {
        return ZonedDateTime.now(ZoneId.of(timezone)).toIsoString()
    }

    // ==================== Display Formatting ====================

    /**
     * Format booking time for display in user's timezone
     * Example: "2025-11-15T14:30:00+01:00" → "Nov 15, 2025 at 2:30 PM"
     */
    fun String.formatBookingTimeForDisplay(): String {
        val zonedDateTime = this.toZonedDateTime().toLocalTimezone()
        return zonedDateTime.format(DISPLAY_DATETIME_FORMATTER)
    }

    /**
     * Format time only for display
     * Example: "2025-11-15T14:30:00+01:00" → "2:30 PM"
     */
    fun String.formatTimeForDisplay(): String {
        val zonedDateTime = this.toZonedDateTime().toLocalTimezone()
        return zonedDateTime.format(DISPLAY_TIME_FORMATTER)
    }

    /**
     * Get timezone display name
     * Example: "Europe/Madrid" → "Central European Time"
     */
    fun getTimezoneDisplayName(timezone: String): String {
        return ZoneId.of(timezone).getDisplayName(
            java.time.format.TextStyle.FULL,
            java.util.Locale.getDefault()
        )
    }

    // ==================== Timestamp Conversions (for System Fields) ====================

    /**
     * Convert timestamp to LocalDate
     * Example: 1699920000000L → LocalDate(2023, 11, 14)
     */
    fun Long.toLocalDate(): LocalDate {
        return Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
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
        return java.time.temporal.ChronoUnit.DAYS.between(start, end)
    }

    /**
     * Calculate duration between two booking times in minutes
     * Example: ("2025-11-15T14:30:00+01:00", "2025-11-15T16:45:00+01:00") → 135
     */
    fun durationInMinutes(startDateTime: String, endDateTime: String): Long {
        val start = startDateTime.toZonedDateTime()
        val end = endDateTime.toZonedDateTime()
        return java.time.temporal.ChronoUnit.MINUTES.between(start, end)
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
            dateTimeString.toZonedDateTime()
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
            ZoneId.of(timezone)
            true
        } catch (e: Exception) {
            false
        }
    }
}
