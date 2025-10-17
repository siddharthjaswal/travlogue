package com.aurora.travlogue.core.common

import com.aurora.travlogue.core.common.DateTimeUtils.daysBetween
import com.aurora.travlogue.core.common.DateTimeUtils.durationInMinutes
import com.aurora.travlogue.core.common.DateTimeUtils.formatBookingTimeForDisplay
import com.aurora.travlogue.core.common.DateTimeUtils.formatDateForDisplay
import com.aurora.travlogue.core.common.DateTimeUtils.formatDuration
import com.aurora.travlogue.core.common.DateTimeUtils.formatTimeForDisplay
import com.aurora.travlogue.core.common.DateTimeUtils.isValidIsoDate
import com.aurora.travlogue.core.common.DateTimeUtils.isValidIsoDateTime
import com.aurora.travlogue.core.common.DateTimeUtils.isValidTimezone
import com.aurora.travlogue.core.common.DateTimeUtils.toDisplayString
import com.aurora.travlogue.core.common.DateTimeUtils.toIsoString
import com.aurora.travlogue.core.common.DateTimeUtils.toLocalDate
import com.aurora.travlogue.core.common.DateTimeUtils.toZonedDateTime
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Unit tests for DateTimeUtils
 *
 * Tests cover:
 * - Date conversions (ISO ↔ LocalDate)
 * - DateTime conversions (ISO 8601 ↔ ZonedDateTime)
 * - Display formatting
 * - Date calculations
 * - Validation
 */
class DateTimeUtilsTest {

    // ==================== Date Conversions ====================

    @Test
    fun `toIsoString converts LocalDate to ISO string`() {
        val date = LocalDate.of(2025, 7, 15)
        val result = date.toIsoString()
        assertThat(result).isEqualTo("2025-07-15")
    }

    @Test
    fun `toLocalDate parses ISO string to LocalDate`() {
        val isoString = "2025-07-15"
        val result = isoString.toLocalDate()
        assertThat(result).isEqualTo(LocalDate.of(2025, 7, 15))
    }

    @Test
    fun `toDisplayString formats LocalDate for display`() {
        val date = LocalDate.of(2025, 7, 15)
        val result = date.toDisplayString()
        assertThat(result).isEqualTo("Jul 15, 2025")
    }

    @Test
    fun `formatDateForDisplay converts ISO string to display format`() {
        val isoString = "2025-07-15"
        val result = isoString.formatDateForDisplay()
        assertThat(result).isEqualTo("Jul 15, 2025")
    }

    @Test
    fun `date conversion roundtrip preserves value`() {
        val original = LocalDate.of(2025, 12, 25)
        val isoString = original.toIsoString()
        val parsed = isoString.toLocalDate()
        assertThat(parsed).isEqualTo(original)
    }

    // ==================== DateTime Conversions ====================

    @Test
    fun `toIsoString converts ZonedDateTime to ISO 8601 string`() {
        val dateTime = ZonedDateTime.of(
            2025, 7, 15, 14, 30, 0, 0,
            ZoneId.of("Europe/Madrid")
        )
        val result = dateTime.toIsoString()
        // Europe/Madrid in July is UTC+2
        assertThat(result).contains("2025-07-15T14:30:00")
        assertThat(result).contains("+02:00")
    }

    @Test
    fun `toZonedDateTime parses ISO 8601 string`() {
        val isoString = "2025-07-15T14:30:00+02:00"
        val result = isoString.toZonedDateTime()
        assertThat(result.year).isEqualTo(2025)
        assertThat(result.monthValue).isEqualTo(7)
        assertThat(result.dayOfMonth).isEqualTo(15)
        assertThat(result.hour).isEqualTo(14)
        assertThat(result.minute).isEqualTo(30)
    }

    @Test
    fun `createZonedDateTime creates ZonedDateTime from LocalDateTime and timezone`() {
        val localDateTime = LocalDateTime.of(2025, 7, 15, 14, 30)
        val result = DateTimeUtils.createZonedDateTime(localDateTime, "Europe/Madrid")
        assertThat(result.year).isEqualTo(2025)
        assertThat(result.monthValue).isEqualTo(7)
        assertThat(result.dayOfMonth).isEqualTo(15)
        assertThat(result.hour).isEqualTo(14)
        assertThat(result.minute).isEqualTo(30)
        assertThat(result.zone.id).isEqualTo("Europe/Madrid")
    }

    @Test
    fun `getCurrentTimeInTimezone returns valid ISO string`() {
        val result = DateTimeUtils.getCurrentTimeInTimezone("Europe/Madrid")
        assertThat(result).isNotEmpty()
        assertThat(isValidIsoDateTime(result)).isTrue()
    }

    @Test
    fun `datetime conversion roundtrip preserves instant`() {
        val original = ZonedDateTime.of(
            2025, 7, 15, 14, 30, 0, 0,
            ZoneId.of("America/New_York")
        )
        val isoString = original.toIsoString()
        val parsed = isoString.toZonedDateTime()
        assertThat(parsed.toInstant()).isEqualTo(original.toInstant())
    }

    // ==================== Display Formatting ====================

    @Test
    fun `formatBookingTimeForDisplay formats datetime with date and time`() {
        val isoString = "2025-07-15T14:30:00+02:00"
        val result = isoString.formatBookingTimeForDisplay()
        // Result will be in system timezone, but should contain date and time
        assertThat(result).contains("Jul 15, 2025")
        assertThat(result).contains("at")
    }

    @Test
    fun `formatTimeForDisplay formats time only`() {
        val isoString = "2025-07-15T14:30:00+02:00"
        val result = isoString.formatTimeForDisplay()
        // Result will be in system timezone, should contain AM or PM
        assertThat(result).matches(".*[AP]M$")
    }

    @Test
    fun `getTimezoneDisplayName returns readable timezone name`() {
        val result = DateTimeUtils.getTimezoneDisplayName("America/New_York")
        // Should contain "Eastern" and "Time"
        assertThat(result).containsMatch("Eastern.*Time")
    }

    // ==================== Timestamp Conversions ====================

    @Test
    fun `Long toLocalDate converts timestamp to LocalDate`() {
        // Nov 14, 2023 midnight UTC = 1699920000000L
        val timestamp = 1699920000000L
        val result = timestamp.toLocalDate()
        // Result depends on system timezone, but should be Nov 13 or 14
        assertThat(result.year).isEqualTo(2023)
        assertThat(result.monthValue).isEqualTo(11)
        assertThat(result.dayOfMonth).isIn(listOf(13, 14))
    }

    @Test
    fun `Long toDisplayString converts timestamp to display format`() {
        val timestamp = 1699920000000L // Nov 14, 2023
        val result = timestamp.toDisplayString()
        assertThat(result).contains("2023")
        assertThat(result).contains("Nov")
    }

    // ==================== Date Calculations ====================

    @Test
    fun `daysBetween calculates days correctly`() {
        val start = "2025-07-01"
        val end = "2025-07-07"
        val result = daysBetween(start, end)
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `daysBetween returns 0 for same date`() {
        val date = "2025-07-15"
        val result = daysBetween(date, date)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `daysBetween returns negative for reversed dates`() {
        val start = "2025-07-07"
        val end = "2025-07-01"
        val result = daysBetween(start, end)
        assertThat(result).isEqualTo(-6)
    }

    @Test
    fun `durationInMinutes calculates minutes correctly`() {
        val start = "2025-07-15T14:30:00+02:00"
        val end = "2025-07-15T16:45:00+02:00"
        val result = durationInMinutes(start, end)
        assertThat(result).isEqualTo(135) // 2h 15m = 135 minutes
    }

    @Test
    fun `durationInMinutes works across timezones`() {
        // Both times represent the same instant in different timezones
        val start = "2025-07-15T14:30:00+02:00" // 12:30 UTC
        val end = "2025-07-15T15:00:00+00:00"   // 15:00 UTC
        val result = durationInMinutes(start, end)
        assertThat(result).isEqualTo(150) // 2.5 hours
    }

    @Test
    fun `formatDuration formats hours and minutes correctly`() {
        val result = formatDuration(135)
        assertThat(result).isEqualTo("2h 15m")
    }

    @Test
    fun `formatDuration formats hours only`() {
        val result = formatDuration(120)
        assertThat(result).isEqualTo("2h")
    }

    @Test
    fun `formatDuration formats minutes only`() {
        val result = formatDuration(45)
        assertThat(result).isEqualTo("45m")
    }

    @Test
    fun `formatDuration handles zero minutes`() {
        val result = formatDuration(0)
        assertThat(result).isEqualTo("0m")
    }

    // ==================== Validation ====================

    @Test
    fun `isValidIsoDate returns true for valid ISO date`() {
        assertThat(isValidIsoDate("2025-07-15")).isTrue()
        assertThat(isValidIsoDate("2023-01-01")).isTrue()
        assertThat(isValidIsoDate("2025-12-31")).isTrue()
    }

    @Test
    fun `isValidIsoDate returns false for invalid ISO date`() {
        assertThat(isValidIsoDate("2025-13-01")).isFalse() // Invalid month
        assertThat(isValidIsoDate("2025-07-32")).isFalse() // Invalid day
        assertThat(isValidIsoDate("07/15/2025")).isFalse() // Wrong format
        assertThat(isValidIsoDate("not-a-date")).isFalse()
        assertThat(isValidIsoDate("")).isFalse()
    }

    @Test
    fun `isValidIsoDateTime returns true for valid ISO 8601 datetime`() {
        assertThat(isValidIsoDateTime("2025-07-15T14:30:00+02:00")).isTrue()
        assertThat(isValidIsoDateTime("2025-07-15T14:30:00Z")).isTrue()
        assertThat(isValidIsoDateTime("2025-07-15T14:30:00-05:00")).isTrue()
    }

    @Test
    fun `isValidIsoDateTime returns false for invalid ISO 8601 datetime`() {
        assertThat(isValidIsoDateTime("2025-07-15")).isFalse() // Date only
        assertThat(isValidIsoDateTime("14:30:00")).isFalse() // Time only
        assertThat(isValidIsoDateTime("2025-07-15 14:30:00")).isFalse() // Wrong separator
        assertThat(isValidIsoDateTime("not-a-datetime")).isFalse()
        assertThat(isValidIsoDateTime("")).isFalse()
    }

    @Test
    fun `isValidTimezone returns true for valid IANA timezone`() {
        assertThat(isValidTimezone("America/New_York")).isTrue()
        assertThat(isValidTimezone("Europe/Madrid")).isTrue()
        assertThat(isValidTimezone("Asia/Tokyo")).isTrue()
        assertThat(isValidTimezone("UTC")).isTrue()
    }

    @Test
    fun `isValidTimezone returns false for invalid timezone`() {
        assertThat(isValidTimezone("Invalid/Timezone")).isFalse()
        assertThat(isValidTimezone("EST")).isFalse() // Abbreviated timezones not supported
        assertThat(isValidTimezone("")).isFalse()
    }

    // ==================== Edge Cases ====================

    @Test
    fun `handles leap year date`() {
        val leapDate = "2024-02-29"
        assertThat(isValidIsoDate(leapDate)).isTrue()
        val parsed = leapDate.toLocalDate()
        assertThat(parsed.year).isEqualTo(2024)
        assertThat(parsed.monthValue).isEqualTo(2)
        assertThat(parsed.dayOfMonth).isEqualTo(29)
    }

    @Test
    fun `handles non-leap year Feb 29 as invalid`() {
        val nonLeapDate = "2023-02-29"
        assertThat(isValidIsoDate(nonLeapDate)).isFalse()
    }

    @Test
    fun `handles year boundaries`() {
        assertThat(isValidIsoDate("2025-01-01")).isTrue()
        assertThat(isValidIsoDate("2025-12-31")).isTrue()
    }

    @Test
    fun `handles midnight time`() {
        val midnight = "2025-07-15T00:00:00+00:00"
        assertThat(isValidIsoDateTime(midnight)).isTrue()
    }

    @Test
    fun `handles end of day time`() {
        val endOfDay = "2025-07-15T23:59:59+00:00"
        assertThat(isValidIsoDateTime(endOfDay)).isTrue()
    }
}
