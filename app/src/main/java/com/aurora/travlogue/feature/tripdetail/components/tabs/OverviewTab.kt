package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Trip
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Overview tab showing trip statistics and notes
 */
@Composable
fun OverviewTab(
    trip: Trip,
    locationCount: Int,
    activityCount: Int,
    bookingCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Trip Statistics Card
        StatisticsCard(
            trip = trip,
            locationCount = locationCount,
            activityCount = activityCount,
            bookingCount = bookingCount
        )

        // Trip Notes Card (Placeholder)
        TripNotesCard()

        // Coming Soon Card
        ComingSoonCard()
    }
}

@Composable
private fun StatisticsCard(
    trip: Trip,
    locationCount: Int,
    activityCount: Int,
    bookingCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Trip Statistics",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Divider()

            // Duration
            if (trip.dateType == DateType.FIXED && trip.startDate != null && trip.endDate != null) {
                val start = LocalDate.parse(trip.startDate)
                val end = LocalDate.parse(trip.endDate)
                val days = ChronoUnit.DAYS.between(start, end).toInt() + 1
                StatRow(label = "Duration", value = "$days ${if (days == 1) "day" else "days"}")
            } else if (trip.dateType == DateType.FLEXIBLE && trip.flexibleDuration != null) {
                StatRow(label = "Estimated Duration", value = "~${trip.flexibleDuration} days")
            }

            StatRow(label = "Locations", value = locationCount.toString())
            StatRow(label = "Planned Activities", value = activityCount.toString())
            StatRow(label = "Bookings", value = bookingCount.toString())
        }
    }
}

@Composable
private fun StatRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "• $label",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun TripNotesCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Trip Notes",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Divider()

            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = "No notes yet. Add important information about your trip here.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ComingSoonCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Coming Soon",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "• Packing lists\n• Important contacts\n• Budget tracking\n• Weather insights\n• Document storage",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}
