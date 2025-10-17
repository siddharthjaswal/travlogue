package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.feature.tripdetail.presentation.components.GapCard
import com.aurora.travlogue.core.design.AppTheme
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Overview tab showing trip statistics, gaps, and notes
 */
@Composable
fun OverviewTab(
    trip: Trip,
    locationCount: Int,
    activityCount: Int,
    bookingCount: Int,
    gaps: List<Gap>,
    locations: List<Location>,
    onGapClick: (Gap) -> Unit,
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
            bookingCount = bookingCount,
            gapCount = gaps.count { !it.isResolved }
        )

        // Gaps Section (if any unresolved gaps exist)
        val unresolvedGaps = gaps.filter { !it.isResolved }
        if (unresolvedGaps.isNotEmpty()) {
            GapsSection(
                gaps = unresolvedGaps,
                locations = locations,
                onGapClick = onGapClick
            )
        }

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
    bookingCount: Int,
    gapCount: Int
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

            HorizontalDivider()

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

            // Show gap count if any exist
            if (gapCount > 0) {
                StatRow(
                    label = "Gaps Detected",
                    value = gapCount.toString(),
                    isWarning = true
                )
            }
        }
    }
}

@Composable
private fun StatRow(
    label: String,
    value: String,
    isWarning: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "• $label",
                style = MaterialTheme.typography.bodyMedium,
                color = if (isWarning) MaterialTheme.colorScheme.error
                       else MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (isWarning) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isWarning) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isWarning) MaterialTheme.colorScheme.error
                   else MaterialTheme.colorScheme.onSurface
        )
    }
}

// ==================== Previews ====================

@Preview(showBackground = true)
@Composable
private fun OverviewTabPreview_WithGaps() {
    AppTheme {
        OverviewTab(
            trip = PreviewData.sampleTripFixed,
            locationCount = 3,
            activityCount = 5,
            bookingCount = 3,
            gaps = PreviewData.sampleGaps,
            locations = PreviewData.sampleLocations,
            onGapClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OverviewTabPreview_NoGaps() {
    AppTheme {
        OverviewTab(
            trip = PreviewData.sampleTripFixed,
            locationCount = 3,
            activityCount = 5,
            bookingCount = 3,
            gaps = emptyList(),
            locations = PreviewData.sampleLocations,
            onGapClick = {}
        )
    }
}

@Composable
private fun GapsSection(
    gaps: List<Gap>,
    locations: List<Location>,
    onGapClick: (Gap) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
                Text(
                    text = "Gaps Detected",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f))

            Text(
                text = "We found ${gaps.size} ${if (gaps.size == 1) "gap" else "gaps"} in your itinerary. " +
                        "Review and resolve them to complete your trip plan.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Show up to 3 gaps
            gaps.take(3).forEach { gap ->
                val fromLocation = locations.find { it.id == gap.fromLocationId }
                val toLocation = locations.find { it.id == gap.toLocationId }

                GapCard(
                    gap = gap,
                    fromLocation = fromLocation,
                    toLocation = toLocation,
                    onClick = { onGapClick(gap) }
                )
            }

            // Show "View more" if there are more than 3 gaps
            if (gaps.size > 3) {
                Text(
                    text = "+ ${gaps.size - 3} more gaps",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
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
