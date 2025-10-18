package com.aurora.travlogue.feature.tripdetail.components.header

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Trip
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Header section displaying trip overview and statistics
 */
@Composable
fun TripHeaderSection(
    trip: Trip,
    locationCount: Int,
    activityCount: Int,
    bookingCount: Int,
    notesCount: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Origin City
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FlightTakeoff,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "From ${trip.originCity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Date Range
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = formatDateRange(trip),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Statistics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatChip(
                    icon = Icons.Default.Place,
                    count = locationCount,
                    label = if (locationCount == 1) "Location" else "Locations",
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    icon = Icons.Default.LocalActivity,
                    count = activityCount,
                    label = if (activityCount == 1) "Activity" else "Activities",
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatChip(
                    icon = Icons.Default.ConfirmationNumber,
                    count = bookingCount,
                    label = if (bookingCount == 1) "Booking" else "Bookings",
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    icon = Icons.Default.Note,
                    count = notesCount,
                    label = if (notesCount == 1) "Note" else "Notes",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Column {
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }
        }
    }
}

private fun formatDateRange(trip: Trip): String {
    return when (trip.dateType) {
        DateType.FIXED -> {
            if (trip.startDate != null && trip.endDate != null) {
                val start = LocalDate.parse(trip.startDate)
                val end = LocalDate.parse(trip.endDate)
                val days = ChronoUnit.DAYS.between(start, end).toInt() + 1
                val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
                "${start.format(formatter)} - ${end.format(formatter)} ($days ${if (days == 1) "day" else "days"})"
            } else {
                "Dates not set"
            }
        }
        DateType.FLEXIBLE -> {
            val duration = trip.flexibleDuration?.let { " (≈$it days)" } ?: ""
            "~${trip.flexibleMonth ?: "Date flexible"}$duration"
        }
    }
}

// ==================== Previews ====================

@Preview(name = "Trip Header - Fixed Dates", showBackground = true)
@Composable
private fun TripHeaderSectionPreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFixed,
            locationCount = 3,
            activityCount = 12,
            bookingCount = 5,
            notesCount = 2
        )
    }
}

@Preview(name = "Trip Header - Flexible Dates", showBackground = true)
@Composable
private fun TripHeaderSectionFlexiblePreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFlexible,
            locationCount = 4,
            activityCount = 8,
            bookingCount = 3,
            notesCount = 0
        )
    }
}

@Preview(name = "Trip Header - Empty Trip", showBackground = true)
@Composable
private fun TripHeaderSectionEmptyPreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFixed,
            locationCount = 0,
            activityCount = 0,
            bookingCount = 0,
            notesCount = 0
        )
    }
}
