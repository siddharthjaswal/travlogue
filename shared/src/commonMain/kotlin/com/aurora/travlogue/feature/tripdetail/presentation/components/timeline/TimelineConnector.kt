package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Timeline item with circular date badge
 * Shows day number (4) and weekday (Wed) in a circular card
 */
@Composable
fun TimelineItem(
    dateTime: String?, // ISO format datetime
    showDate: Boolean = true, // Whether to show the date badge or hide it
    dotColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Date badge (left side) - 10% of width
        if (showDate && dateTime != null) {
            Box(
                modifier = Modifier.fillMaxWidth(0.1f),
                contentAlignment = Alignment.Center
            ) {
                CircularDateBadge(
                    dateTime = dateTime,
                    color = dotColor
                )
            }
        } else {
            // Spacer to maintain alignment when date is hidden
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        }

        // Content (right side) - 90% of width
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            content()
        }
    }
}

/**
 * Compact Circular date badge showing:
 * - Weekday on top of the circle (e.g., "Wed")
 * - Day number in the center (e.g., "4")
 * Circle wraps around the day number with fixed text size
 */
@Composable
private fun CircularDateBadge(
    dateTime: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val (day, weekday) = parseDateToDisplay(dateTime)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        // Weekday on top (3 letters)
        Text(
            text = weekday,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = color.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            letterSpacing = 0.3.sp
        )

        // Circle with day number
        Surface(
            shape = CircleShape,
            color = color.copy(alpha = 0.15f),
            shadowElevation = 1.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                // Day number with fixed size
                Text(
                    text = day,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    color = color,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }
    }
}

/**
 * Parse ISO datetime to day and weekday
 * Returns Pair(day, weekday)
 * Example: "4", "Wed"
 */
private fun parseDateToDisplay(isoDateTime: String): Pair<String, String> {
    return try {
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val day = localDateTime.dayOfMonth.toString()
        val weekday = localDateTime.dayOfWeek.name.take(3).uppercase()
        Pair(day, weekday)
    } catch (e: Exception) {
        try {
            // Try parsing as just a date
            val datePart = isoDateTime.substringBefore('T')
            val localDate = LocalDate.parse(datePart)
            val day = localDate.dayOfMonth.toString()
            val weekday = localDate.dayOfWeek.name.take(3).uppercase()
            Pair(day, weekday)
        } catch (e2: Exception) {
            // Fallback
            Pair("--", "---")
        }
    }
}

/**
 * Extract date from ISO datetime for comparison
 * Returns date string in format: YYYY-MM-DD
 */
fun extractDate(isoDateTime: String?): String? {
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
