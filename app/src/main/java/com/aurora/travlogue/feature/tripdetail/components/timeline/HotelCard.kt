package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Booking
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
            // Hotel icon
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
                        imageVector = Icons.Default.Hotel,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Check-in label
                Text(
                    text = "Check-in",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.SemiBold
                )

                // Hotel name
                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Check-in time
                Text(
                    text = formatCheckInTime(booking.startDateTime),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Confirmation number
                if (booking.confirmationNumber != null) {
                    Text(
                        text = "Confirmation: ${booking.confirmationNumber}",
                        style = MaterialTheme.typography.bodySmall,
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
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    booking.endDateTime?.let { endDateTime ->
                        Text(
                            text = formatStayDuration(booking.startDateTime, endDateTime),
                            style = MaterialTheme.typography.bodySmall,
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
                        imageVector = Icons.Default.Luggage,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Check-out label
                Text(
                    text = "Check-out",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )

                // Hotel name
                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Check-out time
                booking.endDateTime?.let { endDateTime ->
                    Text(
                        text = formatCheckOutTime(endDateTime),
                        style = MaterialTheme.typography.bodyMedium,
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
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        val time = zonedDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
        "Check-in after $time"
    } catch (e: Exception) {
        "Check-in time"
    }
}

/**
 * Format check-out time with a friendly message
 */
private fun formatCheckOutTime(isoDateTime: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        val time = zonedDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
        "Check-out by $time"
    } catch (e: Exception) {
        "Check-out time"
    }
}

/**
 * Calculate and format stay duration
 */
private fun formatStayDuration(startDateTime: String, endDateTime: String): String {
    return try {
        val start = ZonedDateTime.parse(startDateTime)
        val end = ZonedDateTime.parse(endDateTime)
        val nights = java.time.temporal.ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate())

        when {
            nights > 1 -> "$nights nights"
            nights == 1L -> "1 night"
            else -> "Same day"
        }
    } catch (e: Exception) {
        ""
    }
}

// ==================== Previews ====================

@Preview(name = "Hotel Check-in Card", showBackground = true)
@Composable
private fun HotelCheckInCardPreview() {
    MaterialTheme {
        HotelCheckInCard(
            booking = PreviewData.bookingHotel,
            onClick = {}
        )
    }
}

@Preview(name = "Hotel Check-out Card", showBackground = true)
@Composable
private fun HotelCheckOutCardPreview() {
    MaterialTheme {
        HotelCheckOutCard(
            booking = PreviewData.bookingHotel,
            onClick = {}
        )
    }
}
