package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Bookings tab showing all trip bookings
 */
@Composable
fun BookingsTab(
    bookings: List<Booking>,
    onBookingClick: (Booking) -> Unit,
    modifier: Modifier = Modifier
) {
    if (bookings.isEmpty()) {
        EmptyBookingsState(modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = bookings,
                key = { it.id }
            ) { booking ->
                BookingCard(
                    booking = booking,
                    onBookingClick = onBookingClick
                )
            }
        }
    }
}

@Composable
private fun BookingCard(
    booking: Booking,
    onBookingClick: (Booking) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onBookingClick(booking) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Booking type and provider
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Text(
                        text = getBookingIcon(booking.type),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Column {
                    Text(
                        text = booking.type.name.replace("_", " "),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = booking.provider,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Date and time
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = formatBookingDateTime(booking.startDateTime, booking.timezone),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // From/To locations
                if (booking.fromLocation != null && booking.toLocation != null) {
                    Text(
                        text = "${booking.fromLocation} → ${booking.toLocation}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // End time (if different day)
                if (booking.endDateTime != null) {
                    Text(
                        text = "Until: ${formatBookingDateTime(booking.endDateTime, booking.timezone)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Confirmation and price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (booking.confirmationNumber != null) {
                    Column {
                        Text(
                            text = "Confirmation",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = booking.confirmationNumber,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                if (booking.price != null && booking.currency != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Price",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${booking.currency} ${booking.price}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Notes (if any)
            if (!booking.notes.isNullOrBlank()) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = booking.notes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyBookingsState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "No bookings yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Add flights, hotels, and other reservations",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getBookingIcon(type: BookingType): String {
    return when (type) {
        BookingType.FLIGHT -> "✈️"
        BookingType.HOTEL -> "🏨"
        BookingType.TRAIN -> "🚂"
        BookingType.BUS -> "🚌"
        BookingType.TICKET -> "🎫"
        BookingType.OTHER -> "📝"
    }
}

private fun formatBookingDateTime(dateTimeString: String, timezone: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateTimeString)
        val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a")
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        dateTimeString
    }
}
