package com.aurora.travlogue.feature.tripdetail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.domain.model.Gap
import com.aurora.travlogue.core.domain.model.GapType
import com.aurora.travlogue.core.domain.model.Location
import com.aurora.travlogue.core.design.AppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Card component for displaying a gap in the trip itinerary.
 *
 * Shows:
 * - Gap type icon and title
 * - Duration
 * - From/To locations
 * - Date range
 */
@Composable
fun GapCard(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val gapInfo = getGapDisplayInfo(gap, fromLocation, toLocation)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gap icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = gapInfo.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Gap details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Title
                Text(
                    text = gapInfo.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Subtitle (locations)
                if (gapInfo.subtitle.isNotEmpty()) {
                    Text(
                        text = gapInfo.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Date range
                Text(
                    text = gapInfo.dateRange,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Duration badge
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = gapInfo.duration,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/**
 * Compact version of GapCard for inline display in timeline.
 */
@Composable
fun CompactGapCard(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val gapInfo = getGapDisplayInfo(gap, fromLocation, toLocation)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = gapInfo.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(20.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = gapInfo.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "${gapInfo.duration} • ${gapInfo.subtitle}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Data class for gap display information.
 */
private data class GapDisplayInfo(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val dateRange: String,
    val duration: String
)

/**
 * Get display information for a gap.
 */
private fun getGapDisplayInfo(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?
): GapDisplayInfo {
    val fromDate = LocalDate.parse(gap.fromDate)
    val toDate = LocalDate.parse(gap.toDate)
    val days = ChronoUnit.DAYS.between(fromDate, toDate) + 1

    val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
    val dateRange = "${fromDate.format(dateFormatter)} - ${toDate.format(dateFormatter)}"

    val duration = when {
        days == 1L -> "1 day"
        else -> "$days days"
    }

    return when (gap.gapType) {
        GapType.MISSING_TRANSIT -> {
            val from = fromLocation?.name ?: "Unknown"
            val to = toLocation?.name ?: "Unknown"
            GapDisplayInfo(
                icon = Icons.Outlined.DirectionsBus,
                title = "Missing Transit",
                subtitle = "$from → $to",
                dateRange = dateRange,
                duration = duration
            )
        }

        GapType.UNPLANNED_DAY -> {
            GapDisplayInfo(
                icon = Icons.Outlined.CalendarToday,
                title = "Unplanned Days",
                subtitle = "No activities or locations planned",
                dateRange = dateRange,
                duration = duration
            )
        }

        GapType.TIME_CONFLICT -> {
            GapDisplayInfo(
                icon = Icons.Outlined.Warning,
                title = "Schedule Conflict",
                subtitle = "Overlapping activities detected",
                dateRange = dateRange,
                duration = duration
            )
        }
    }
}

// ==================== Previews ====================

@Preview(showBackground = true)
@Composable
private fun GapCardPreview_MissingTransit() {
    AppTheme {
        GapCard(
            gap = PreviewData.gapMissingTransit,
            fromLocation = PreviewData.locationKyoto,
            toLocation = PreviewData.locationOsaka,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GapCardPreview_UnplannedDay() {
    AppTheme {
        GapCard(
            gap = PreviewData.gapUnplannedDay,
            fromLocation = PreviewData.locationTokyo,
            toLocation = PreviewData.locationKyoto,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GapCardPreview_TimeConflict() {
    AppTheme {
        GapCard(
            gap = PreviewData.gapTimeConflict,
            fromLocation = PreviewData.locationTokyo,
            toLocation = PreviewData.locationTokyo,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompactGapCardPreview_MissingTransit() {
    AppTheme {
        CompactGapCard(
            gap = PreviewData.gapMissingTransit,
            fromLocation = PreviewData.locationKyoto,
            toLocation = PreviewData.locationOsaka,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompactGapCardPreview_UnplannedDay() {
    AppTheme {
        CompactGapCard(
            gap = PreviewData.gapUnplannedDay,
            fromLocation = PreviewData.locationTokyo,
            toLocation = PreviewData.locationKyoto,
            onClick = {}
        )
    }
}
