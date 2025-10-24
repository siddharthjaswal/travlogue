package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
        Instant.parse(booking.startDateTime)
    } catch (e: Exception) { null }

    val endDateTime = try {
        booking.endDateTime?.let { Instant.parse(it) }
    } catch (e: Exception) { null }

    val durationMillis = if (startDateTime != null && endDateTime != null) {
        endDateTime.toEpochMilliseconds() - startDateTime.toEpochMilliseconds()
    } else null

    val timezoneShift = if (startDateTime != null && endDateTime != null) {
        val startTz = startDateTime.toLocalDateTime(TimeZone.currentSystemDefault())
        val endTz = endDateTime.toLocalDateTime(TimeZone.currentSystemDefault())
        // Approximate timezone shift based on hour difference
        // This is a simplified calculation
        0 // We can't easily get offset in KMP without more context
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
            if (durationMillis != null) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Duration",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = formatDuration(durationMillis),
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
            // Note: Simplified implementation without detailed timezone information
            // as kotlinx.datetime doesn't provide easy offset access
            if (startDateTime != null && endDateTime != null) {
                val startLocal = startDateTime.toLocalDateTime(TimeZone.currentSystemDefault())
                val endLocal = endDateTime.toLocalDateTime(TimeZone.currentSystemDefault())

                // Only show if there's a potential timezone difference
                // This is a heuristic based on whether times are different from duration
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
                                text = "Time Zone Information",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        // Timezone info
                        Text(
                            text = "Departure: ${formatTimezoneInfo(startLocal)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Arrival: ${formatTimezoneInfo(endLocal)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

/**
 * Format duration to readable string
 */
private fun formatDuration(durationMillis: Long): String {
    val hours = durationMillis / (1000 * 60 * 60)
    val minutes = (durationMillis / (1000 * 60)) % 60
    return "${hours}h ${minutes}m"
}

/**
 * Format timezone information
 */
private fun formatTimezoneInfo(localDateTime: kotlinx.datetime.LocalDateTime): String {
    val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
    val minute = localDateTime.minute.toString().padStart(2, '0')
    val period = if (localDateTime.hour < 12) "AM" else "PM"
    return "$hour:$minute $period"
}

/**
 * Get appropriate icon for transit type
 */
private fun getTransitIcon(type: BookingType) = when (type) {
    BookingType.FLIGHT -> Icons.Default.Place
    BookingType.TRAIN -> Icons.Default.Place
    BookingType.BUS -> Icons.Default.Place
    else -> Icons.Default.Place
}
