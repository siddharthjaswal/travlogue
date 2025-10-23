package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs

/**
 * Card showing transit between locations with timezone transition information
 */
@Composable
fun TransitCard(
    booking: Booking,
    modifier: Modifier = Modifier
) {
    // Calculate transit duration and timezone shift
    val startDateTime = try {
        ZonedDateTime.parse(booking.startDateTime)
    } catch (e: Exception) { null }

    val endDateTime = try {
        ZonedDateTime.parse(booking.endDateTime)
    } catch (e: Exception) { null }

    val duration = if (startDateTime != null && endDateTime != null) {
        Duration.between(startDateTime, endDateTime)
    } else null

    val timezoneShift = if (startDateTime != null && endDateTime != null) {
        val startOffset = startDateTime.offset.totalSeconds / 3600
        val endOffset = endDateTime.offset.totalSeconds / 3600
        endOffset - startOffset
    } else null

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with icon
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = getTransitIcon(booking.type),
                    contentDescription = "Transit",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(32.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "IN TRANSIT",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = "${booking.fromLocation ?: "Origin"} → ${booking.toLocation ?: "Destination"}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Transit duration
            if (duration != null) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Duration",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = formatDuration(duration),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "• ${booking.provider}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Timezone transition box
            if (startDateTime != null && endDateTime != null && timezoneShift != null && timezoneShift != 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🕐",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Timezone Transition",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Timezone info
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // From timezone
                            Column {
                                Text(
                                    text = getTimezoneAbbreviation(startDateTime),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "UTC${formatOffset(startDateTime.offset.totalSeconds / 3600)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Arrow and shift
                            Text(
                                text = "→",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.tertiary
                            )

                            // To timezone
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = getTimezoneAbbreviation(endDateTime),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "UTC${formatOffset(endDateTime.offset.totalSeconds / 3600)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Timezone shift summary
                        Text(
                            text = "${if (timezoneShift > 0) "+" else ""}$timezoneShift hour${if (abs(timezoneShift) != 1) "s" else ""} timezone shift",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "TransitCard - Flight with Timezone Change", showBackground = true)
@Composable
private fun TransitCardPreview() {
    val booking = Booking(
        tripId = "1",
        type = BookingType.FLIGHT,
        confirmationNumber = "JAL123",
        provider = "Japan Airlines",
        startDateTime = "2025-07-01T10:00:00-07:00", // 10 AM PDT
        endDateTime = "2025-07-02T14:30:00+09:00", // 2:30 PM JST next day
        timezone = "Asia/Tokyo",
        fromLocation = "San Francisco (SFO)",
        toLocation = "Tokyo Narita (NRT)",
        price = 850.00,
        currency = "USD",
        notes = "Window seat",
        imageUri = null
    )
    TransitCard(booking = booking)
}

@Preview(name = "TransitCard - Train Same Timezone", showBackground = true)
@Composable
private fun TransitCardSameTimezonePreview() {
    val booking = Booking(
        tripId = "1",
        type = BookingType.TRAIN,
        confirmationNumber = "JR456",
        provider = "JR Shinkansen",
        startDateTime = "2025-07-05T09:00:00+09:00",
        endDateTime = "2025-07-05T11:15:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = "Tokyo Station",
        toLocation = "Kyoto Station",
        price = 140.00,
        currency = "USD",
        notes = "Reserved seat",
        imageUri = null
    )
    TransitCard(booking = booking)
}

/**
 * Format duration to readable string
 */
private fun formatDuration(duration: Duration): String {
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60
    return "${hours}h ${minutes}m"
}

/**
 * Get timezone abbreviation from ZonedDateTime
 */
private fun getTimezoneAbbreviation(zonedDateTime: ZonedDateTime): String {
    return zonedDateTime.format(DateTimeFormatter.ofPattern("zzz"))
}

/**
 * Format UTC offset for display
 */
private fun formatOffset(offsetHours: Int): String {
    return if (offsetHours >= 0) "+$offsetHours" else "$offsetHours"
}

/**
 * Get appropriate icon for transit type
 */
private fun getTransitIcon(type: BookingType) = when (type) {
    BookingType.FLIGHT -> Icons.Default.Flight
    BookingType.TRAIN -> Icons.Default.Train
    BookingType.BUS -> Icons.Default.DirectionsBus
    else -> Icons.Default.Flight
}
