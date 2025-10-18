package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.travlogue.core.common.PreviewData
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
        // Date badge (left side)
        if (showDate && dateTime != null) {
            CircularDateBadge(
                dateTime = dateTime,
                color = dotColor
            )
        } else {
            // Spacer to maintain alignment when date is hidden
            Spacer(modifier = Modifier.width(56.dp))
        }

        // Content (right side)
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 12.dp)
        ) {
            content()
        }
    }
}

/**
 * Circular date badge showing:
 * - Day number on top (e.g., "4")
 * - Weekday below (e.g., "Wed")
 */
@Composable
private fun CircularDateBadge(
    dateTime: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val (day, weekday) = parseDateToDisplay(dateTime)

    Surface(
        modifier = modifier.size(56.dp),
        shape = CircleShape,
        color = color.copy(alpha = 0.15f),
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Day number
            Text(
                text = day,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = color,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )

            // Weekday (3 letters)
            Text(
                text = weekday,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = color.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
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
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        val day = zonedDateTime.format(DateTimeFormatter.ofPattern("d"))
        val weekday = zonedDateTime.format(DateTimeFormatter.ofPattern("EEE"))
        Pair(day, weekday.uppercase())
    } catch (e: Exception) {
        // Fallback
        Pair("--", "---")
    }
}

/**
 * Extract date from ISO datetime for comparison
 * Returns date string in format: YYYY-MM-DD
 */
fun extractDate(isoDateTime: String?): String? {
    if (isoDateTime == null) return null
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        zonedDateTime.toLocalDate().toString()
    } catch (e: Exception) {
        // Try parsing as just a date
        try {
            LocalDate.parse(isoDateTime.substringBefore('T')).toString()
        } catch (e2: Exception) {
            null
        }
    }
}

/**
 * Larger timeline marker for major events (city arrivals/departures)
 * Shows date with more emphasis
 */
@Composable
fun TimelineMajorItem(
    dateTime: String?,
    showDate: Boolean = true,
    dotColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Date badge for major events (slightly larger)
        if (showDate && dateTime != null) {
            MajorCircularDateBadge(
                dateTime = dateTime,
                color = dotColor
            )
        } else {
            // Spacer to maintain alignment
            Spacer(modifier = Modifier.width(64.dp))
        }

        // Content
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 12.dp)
        ) {
            content()
        }
    }
}

/**
 * Larger circular badge for major events
 */
@Composable
private fun MajorCircularDateBadge(
    dateTime: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val (day, weekday) = parseDateToDisplay(dateTime)

    Surface(
        modifier = modifier.size(64.dp),
        shape = CircleShape,
        color = color.copy(alpha = 0.2f),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Day number (larger)
            Text(
                text = day,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = color,
                textAlign = TextAlign.Center
            )

            // Weekday (3 letters)
            Text(
                text = weekday,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = color.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                letterSpacing = 0.8.sp
            )
        }
    }
}

// ==================== Previews ====================

@Preview(name = "Timeline Item with Date Badge", showBackground = true)
@Composable
private fun TimelineItemPreview() {
    MaterialTheme {
        TimelineItem(
            dateTime = "2025-07-01T09:00:00+09:00",
            showDate = true,
            dotColor = MaterialTheme.colorScheme.primary
        ) {
            ActivityTimelineCard(
                activity = PreviewData.activitySensoJi,
                onClick = {}
            )
        }
    }
}

@Preview(name = "Timeline Item without Date Badge", showBackground = true)
@Composable
private fun TimelineItemNoBadgePreview() {
    MaterialTheme {
        TimelineItem(
            dateTime = "2025-07-01T14:00:00+09:00",
            showDate = false,
            dotColor = MaterialTheme.colorScheme.primary
        ) {
            ActivityTimelineCard(
                activity = PreviewData.activityShibuya,
                onClick = {}
            )
        }
    }
}

@Preview(name = "Timeline Major Item", showBackground = true)
@Composable
private fun TimelineMajorItemPreview() {
    MaterialTheme {
        TimelineMajorItem(
            dateTime = "2025-07-01T14:30:00+09:00",
            showDate = true,
            dotColor = MaterialTheme.colorScheme.primary
        ) {
            WelcomeCityCard(
                location = PreviewData.locationTokyo,
                arrivalDateTime = "2025-07-01T14:30:00+09:00"
            )
        }
    }
}
