package com.aurora.travlogue.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.DateTimeUtils.daysBetween
import com.aurora.travlogue.core.common.DateTimeUtils.formatDateForDisplay
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.model.TripMockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripCard(
    trip: Trip,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Trip name and delete button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = trip.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete trip",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Origin city
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "From ${trip.originCity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Date information
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))

                when (trip.dateType) {
                    DateType.FIXED -> {
                        val startDate = trip.startDate
                        val endDate = trip.endDate
                        if (startDate != null && endDate != null) {
                            val days = daysBetween(startDate, endDate) + 1
                            Text(
                                text = "${startDate.formatDateForDisplay()} - ${endDate.formatDateForDisplay()} ($days days)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            Text(
                                text = "Dates not set",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    DateType.FLEXIBLE -> {
                        val monthText = trip.flexibleMonth ?: "Unknown month"
                        val durationText = trip.flexibleDuration?.let { "$it days" } ?: ""
                        Text(
                            text = "$monthText${if (durationText.isNotEmpty()) ", $durationText" else ""}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Type badge
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                color = when (trip.dateType) {
                    DateType.FIXED -> MaterialTheme.colorScheme.primaryContainer
                    DateType.FLEXIBLE -> MaterialTheme.colorScheme.tertiaryContainer
                },
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = when (trip.dateType) {
                        DateType.FIXED -> "Fixed Dates"
                        DateType.FLEXIBLE -> "Flexible"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = when (trip.dateType) {
                        DateType.FIXED -> MaterialTheme.colorScheme.onPrimaryContainer
                        DateType.FLEXIBLE -> MaterialTheme.colorScheme.onTertiaryContainer
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Trip Card - Fixed Dates")
@Composable
private fun TripCardFixedPreview() {
    MaterialTheme {
        TripCard(
            trip = TripMockData.spainAdventure,
            onClick = {},
            onDelete = {}
        )
    }
}

@Preview(showBackground = true, name = "Trip Card - Flexible Dates")
@Composable
private fun TripCardFlexiblePreview() {
    MaterialTheme {
        TripCard(
            trip = TripMockData.japanCherryBlossom,
            onClick = {},
            onDelete = {}
        )
    }
}

@Preview(showBackground = true, name = "Trip Card - Flexible Without Duration")
@Composable
private fun TripCardFlexibleNoDurationPreview() {
    MaterialTheme {
        TripCard(
            trip = TripMockData.europeBackpacking,
            onClick = {},
            onDelete = {}
        )
    }
}
