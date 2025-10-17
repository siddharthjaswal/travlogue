package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.feature.tripdetail.components.timeline.DayCard
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import com.aurora.travlogue.feature.tripdetail.presentation.components.CompactGapCard
import com.aurora.travlogue.core.design.AppTheme
import java.time.LocalDate

/**
 * Timeline tab showing day-by-day schedule with gap detection
 */
@Composable
fun TimelineTab(
    daySchedules: List<DaySchedule>,
    gaps: List<Gap>,
    locations: List<Location>,
    expandedDays: Set<String>,
    onDayClicked: (String) -> Unit,
    onActivityClick: (com.aurora.travlogue.core.data.local.entities.Activity) -> Unit,
    onGapClick: (Gap) -> Unit,
    modifier: Modifier = Modifier
) {
    if (daySchedules.isEmpty()) {
        EmptyTimelineState(modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            daySchedules.forEachIndexed { index, daySchedule ->
                // Show day card
                item(key = "day-${daySchedule.date}") {
                    DayCard(
                        daySchedule = daySchedule,
                        isExpanded = daySchedule.date in expandedDays,
                        onDayClicked = { onDayClicked(daySchedule.date) },
                        onActivityClick = onActivityClick
                    )
                }

                // Show gap after this day (if exists)
                val gapsAfterThisDay = findGapsAfterDate(gaps, daySchedule.date)
                gapsAfterThisDay.forEach { gap ->
                    item(key = "gap-${gap.id}") {
                        val fromLocation = locations.find { it.id == gap.fromLocationId }
                        val toLocation = locations.find { it.id == gap.toLocationId }

                        CompactGapCard(
                            gap = gap,
                            fromLocation = fromLocation,
                            toLocation = toLocation,
                            onClick = { onGapClick(gap) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Find gaps that start on or after the given date.
 * Shows gaps between consecutive days.
 */
private fun findGapsAfterDate(gaps: List<Gap>, date: String): List<Gap> {
    return gaps.filter { gap ->
        val gapFromDate = LocalDate.parse(gap.fromDate)
        val currentDate = LocalDate.parse(date)

        // Show gap if it starts on this day
        gapFromDate == currentDate
    }
}

@Composable
private fun EmptyTimelineState(modifier: Modifier = Modifier) {
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
                text = "No schedule yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Add locations and activities to build your itinerary",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ==================== Previews ====================

@Preview(showBackground = true)
@Composable
private fun TimelineTabPreview_WithGaps() {
    AppTheme {
        TimelineTab(
            daySchedules = PreviewData.sampleDaySchedules,
            gaps = PreviewData.sampleGaps,
            locations = PreviewData.sampleLocations,
            expandedDays = setOf("2025-07-01"),
            onDayClicked = {},
            onActivityClick = {},
            onGapClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineTabPreview_Empty() {
    AppTheme {
        TimelineTab(
            daySchedules = emptyList(),
            gaps = emptyList(),
            locations = emptyList(),
            expandedDays = emptySet(),
            onDayClicked = {},
            onActivityClick = {},
            onGapClick = {}
        )
    }
}
