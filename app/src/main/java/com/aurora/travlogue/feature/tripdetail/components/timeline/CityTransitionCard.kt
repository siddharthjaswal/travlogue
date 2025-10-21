package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Hotel
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
import com.aurora.travlogue.core.common.PreviewData.bookingFlight
import com.aurora.travlogue.core.common.PreviewData.bookingTrain
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Card showing arrival at a city
 */
@Composable
fun CityArrivalCard(
    location: Location,
    arrivalDateTime: String,
    arrivalBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                imageVector = Icons.Default.FlightLand,
                contentDescription = "Arrival",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Arrive in ${location.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Time and booking info
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDateTime(arrivalDateTime),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
//                    Text(
//                        text = "•",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Icon(
//                        imageVector = getBookingIcon(arrivalBooking.type),
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp),
//                        tint = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Text(
//                        text = arrivalBooking.provider,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
                }

                // From location
                if (arrivalBooking.fromLocation != null) {
                    Text(
                        text = "From ${arrivalBooking.fromLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(name = "CityArrivalCardPreview", showBackground = true)
@Composable
private fun CityArrivalCardPreview() {
    val location = Location(
        tripId = "1",
        name = "Tokyo",
        country = "Japan",
        date = "2025-11-20",
        latitude = 35.6895,
        longitude = 139.6917,
        order = 1
    )
    val arrivalBooking = bookingFlight
    CityArrivalCard(
        location = location,
        arrivalDateTime = "2025-11-20T14:30:00+09:00",
        arrivalBooking = arrivalBooking
    )
}

/**
 * Card showing departure from a city
 */
@Composable
fun CityDepartureCard(
    location: Location,
    departureDateTime: String,
    departureBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                imageVector = Icons.Default.FlightTakeoff,
                contentDescription = "Departure",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Depart from ${location.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Time and booking info
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDateTime(departureDateTime),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
//                    Text(
//                        text = "•",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Icon(
//                        imageVector = getBookingIcon(departureBooking.type),
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp),
//                        tint = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Text(
//                        text = departureBooking.provider,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
                }

                // To location
                if (departureBooking.toLocation != null) {
                    Text(
                        text = "To ${departureBooking.toLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(name = "CityDepartureCardPreview", showBackground = true)
@Composable
private fun CityDepartureCardPreview() {
    val location = Location(
        tripId = "1",
        name = "Tokyo",
        country = "Japan",
        date = "2025-11-25",
        latitude = 35.6895,
        longitude = 139.6917,
        order = 1
    )
    val departureBooking = bookingTrain
    CityDepartureCard(
        location = location,
        departureDateTime = "2025-11-25T09:00:00+09:00",
        departureBooking = departureBooking
    )
}

/**
 * Format ISO datetime to readable format
 */
private fun formatDateTime(isoDateTime: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        zonedDateTime.format(DateTimeFormatter.ofPattern("MMM d, h:mm a"))
    } catch (e: Exception) {
        isoDateTime
    }
}

/**
 * Card showing departure from origin city (not a Location in the trip)
 */
@Composable
fun OriginDepartureCard(
    originCity: String,
    departureDateTime: String,
    departureBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                imageVector = Icons.Default.FlightTakeoff,
                contentDescription = "Departure",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Depart from $originCity",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Time and booking info
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDateTime(departureDateTime),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
//                    Text(
//                        text = "•",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Icon(
//                        imageVector = getBookingIcon(departureBooking.type),
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp),
//                        tint = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Text(
//                        text = departureBooking.provider,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
                }

                // To location
                if (departureBooking.toLocation != null) {
                    Text(
                        text = "To ${departureBooking.toLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(name = "OriginDepartureCardPreview", showBackground = true)
@Composable
private fun OriginDepartureCardPreview() {
    val departureBooking = bookingFlight
    OriginDepartureCard(
        originCity = "San Francisco",
        departureDateTime = "2025-07-01T10:00:00-07:00",
        departureBooking = departureBooking
    )
}

/**
 * Get appropriate icon for booking type
 */
private fun getBookingIcon(type: BookingType) = when (type) {
    BookingType.FLIGHT -> Icons.Default.Flight
    BookingType.TRAIN -> Icons.Default.Train
    BookingType.BUS -> Icons.Default.DirectionsBus
    BookingType.HOTEL -> Icons.Default.Hotel
    BookingType.TICKET -> Icons.Default.ConfirmationNumber
    BookingType.OTHER -> Icons.Default.Event
}
