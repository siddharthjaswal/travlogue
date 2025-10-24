package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Booking
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs

/**
 * Hotel check-in card shown after arriving in a city
 */
@Composable
fun HotelCheckInCard(
    booking: Booking,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hotel icon (using Home as Hotel is not available)
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Check-in label
                Text(
                    text = "Check-in",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.SemiBold
                )

                // Hotel name
                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Check-in time
                Text(
                    text = formatCheckInTime(booking.startDateTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Confirmation number
                if (booking.confirmationNumber != null) {
                    Text(
                        text = "Confirmation: ${booking.confirmationNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Price
            if (booking.price != null && booking.currency != null) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${booking.currency} ${String.format("%.0f", booking.price)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    booking.endDateTime?.let { endDateTime ->
                        Text(
                            text = formatStayDuration(booking.startDateTime, endDateTime),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

/**
 * Hotel check-out card shown before leaving a city
 */
@Composable
fun HotelCheckOutCard(
    booking: Booking,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Luggage icon
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Check-out label
                Text(
                    text = "Check-out",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )

                // Hotel name
                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Check-out time
                booking.endDateTime?.let { endDateTime ->
                    Text(
                        text = formatCheckOutTime(endDateTime),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Format check-in time with a friendly message
 */
private fun formatCheckInTime(isoDateTime: String): String {
    return try {
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val period = if (localDateTime.hour < 12) "AM" else "PM"

        "Check-in after $hour:$minute $period"
    } catch (e: Exception) {
        "Check-in time"
    }
}

/**
 * Format check-out time with a friendly message
 */
private fun formatCheckOutTime(isoDateTime: String): String {
    return try {
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val period = if (localDateTime.hour < 12) "AM" else "PM"

        "Check-out by $hour:$minute $period"
    } catch (e: Exception) {
        "Check-out time"
    }
}

/**
 * Calculate and format stay duration
 */
private fun formatStayDuration(startDateTime: String, endDateTime: String): String {
    return try {
        val start = Instant.parse(startDateTime)
        val end = Instant.parse(endDateTime)

        val startDate = start.toLocalDateTime(TimeZone.currentSystemDefault()).date
        val endDate = end.toLocalDateTime(TimeZone.currentSystemDefault()).date

        val nights = daysBetween(startDate, endDate)

        when {
            nights > 1 -> "$nights nights"
            nights == 1L -> "1 night"
            else -> "Same day"
        }
    } catch (e: Exception) {
        ""
    }
}

/**
 * Calculate days between two LocalDate instances
 */
private fun daysBetween(start: LocalDate, end: LocalDate): Long {
    val startEpochDays = start.toEpochDays()
    val endEpochDays = end.toEpochDays()
    return abs(endEpochDays - startEpochDays).toLong()
}
