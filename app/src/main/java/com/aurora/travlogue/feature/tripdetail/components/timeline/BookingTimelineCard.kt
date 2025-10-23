package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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
    BookingType.FLIGHT -> Icons.Default.Flight
    BookingType.HOTEL -> Icons.Default.Hotel
    BookingType.TRAIN -> Icons.Default.Train
    BookingType.BUS -> Icons.Default.DirectionsBus
    BookingType.TICKET -> Icons.Default.ConfirmationNumber
    BookingType.OTHER -> Icons.Default.Event
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
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        zonedDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
    } catch (e: Exception) {
        isoDateTime
    }
}

private fun calculateDuration(startDateTime: String, endDateTime: String): String? {
    return try {
        val start = ZonedDateTime.parse(startDateTime)
        val end = ZonedDateTime.parse(endDateTime)

        val hours = ChronoUnit.HOURS.between(start, end)
        val minutes = ChronoUnit.MINUTES.between(start, end) % 60

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

// ==================== Previews ====================

@Preview(name = "Booking Card - Flight", showBackground = true)
@Composable
private fun BookingTimelineCardFlightPreview() {
    MaterialTheme {
        BookingTimelineCard(
            booking = PreviewData.bookingFlight,
            onClick = {}
        )
    }
}

@Preview(name = "Booking Card - Train", showBackground = true)
@Composable
private fun BookingTimelineCardTrainPreview() {
    MaterialTheme {
        BookingTimelineCard(
            booking = PreviewData.bookingTrain,
            onClick = {}
        )
    }
}

@Preview(name = "Booking Card - Hotel", showBackground = true)
@Composable
private fun BookingTimelineCardHotelPreview() {
    MaterialTheme {
        BookingTimelineCard(
            booking = PreviewData.bookingHotel,
            onClick = {}
        )
    }
}
