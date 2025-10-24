package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Card showing a booking in the timeline
 */
@Composable
fun BookingTimelineCard(
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
            // Booking type icon
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = getBookingTypeIcon(booking.type),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Booking type and provider
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getBookingTypeDisplay(booking.type),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = booking.provider,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Route (for transit bookings)
                if (booking.fromLocation != null && booking.toLocation != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val fromLocation = booking.fromLocation
                        val toLocation = booking.toLocation
                        if (fromLocation != null) {
                            Text(
                                text = fromLocation,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(10.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (toLocation != null) {
                            Text(
                                text = toLocation,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Time info
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatTime(booking.startDateTime),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium
                    )

                    val endDateTime = booking.endDateTime
                    if (endDateTime != null) {
                        Text(
                            text = "→",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = formatTime(endDateTime),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )

                        // Duration
                        val duration = calculateDuration(booking.startDateTime, endDateTime)
                        if (duration != null) {
                            Text(
                                text = "•",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = duration,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Confirmation number
                if (booking.confirmationNumber != null) {
                    Text(
                        text = "Conf: ${booking.confirmationNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Price (if available)
            if (booking.price != null && booking.currency != null) {
                Text(
                    text = "${booking.currency} ${String.format("%.2f", booking.price)}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

private fun getBookingTypeIcon(type: BookingType) = when (type) {
    BookingType.FLIGHT -> Icons.Default.Place
    BookingType.HOTEL -> Icons.Default.Place
    BookingType.TRAIN -> Icons.Default.Place
    BookingType.BUS -> Icons.Default.Place
    BookingType.TICKET -> Icons.Default.Info
    BookingType.OTHER -> Icons.Default.Info
}

private fun getBookingTypeDisplay(type: BookingType) = when (type) {
    BookingType.FLIGHT -> "Flight"
    BookingType.HOTEL -> "Hotel"
    BookingType.TRAIN -> "Train"
    BookingType.BUS -> "Bus"
    BookingType.TICKET -> "Ticket"
    BookingType.OTHER -> "Booking"
}

private fun formatTime(isoDateTime: String): String {
    return try {
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val period = if (localDateTime.hour < 12) "AM" else "PM"
        "$hour:$minute $period"
    } catch (e: Exception) {
        isoDateTime
    }
}

private fun calculateDuration(startDateTime: String, endDateTime: String): String? {
    return try {
        val start = Instant.parse(startDateTime)
        val end = Instant.parse(endDateTime)

        val durationMillis = end.toEpochMilliseconds() - start.toEpochMilliseconds()
        val hours = durationMillis / (1000 * 60 * 60)
        val minutes = (durationMillis / (1000 * 60)) % 60

        when {
            hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
            hours > 0 -> "${hours}h"
            minutes > 0 -> "${minutes}m"
            else -> null
        }
    } catch (e: Exception) {
        null
    }
}
