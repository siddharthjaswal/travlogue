package com.aurora.travlogue.feature.tripdetail.components.header

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
        }
    }
}

private fun formatDateRange(trip: Trip): String {
    return when (trip.dateType) {
        DateType.FIXED -> {
            val startDate = trip.startDate?.let { LocalDate.parse(it) }
            val endDate = trip.endDate?.let { LocalDate.parse(it) }

            if (startDate != null && endDate != null) {
                val days = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1

                val sameYear = startDate.year == endDate.year
                val sameMonth = startDate.month == endDate.month

                val startFormatter = when {
                    sameYear && sameMonth -> DateTimeFormatter.ofPattern("MMM d")
                    sameYear -> DateTimeFormatter.ofPattern("MMM d")
                    else -> DateTimeFormatter.ofPattern("MMM d, yyyy")
                }

                val endFormatter = if (sameYear) {
                    DateTimeFormatter.ofPattern("MMM d, yyyy")
                } else {
                    DateTimeFormatter.ofPattern("MMM d, yyyy")
                }

                val formattedStart = startDate.format(startFormatter)
                val formattedEnd = endDate.format(endFormatter)

                "$formattedStart – $formattedEnd · $days ${if (days == 1) "day" else "days"}"
            } else {
                "Dates not set"
            }
        }

        DateType.FLEXIBLE -> {
            val duration = trip.flexibleDuration?.let { " · ≈$it days" } ?: ""
            trip.flexibleMonth?.let { "~$it$duration" } ?: "Flexible dates$duration"
        }
    }
}


// ==================== Previews ====================

@Preview(
    name = "Trip Header - Fixed Dates - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Trip Header - Fixed Dates - Night",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun TripHeaderSectionPreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFixed,
        )
    }
}

@Preview(name = "Trip Header - Flexible Dates", showBackground = true)
@Composable
private fun TripHeaderSectionFlexiblePreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFlexible,
        )
    }
}

@Preview(name = "Trip Header - Empty Trip", showBackground = true)
@Composable
private fun TripHeaderSectionEmptyPreview() {
    MaterialTheme {
        TripHeaderSection(
            trip = PreviewData.sampleTripFixed,
        )
    }
}
