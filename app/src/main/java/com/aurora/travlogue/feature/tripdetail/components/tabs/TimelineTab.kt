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
        // Build a single list of items (days + gaps) for stable rendering
        val timelineItems = buildTimelineItems(daySchedules, gaps)

        // Create location map for efficient lookups
        val locationMap = locations.associateBy { it.id }

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = timelineItems,
                key = { item ->
                    when (item) {
                        is TimelineItem.Day -> "day-${item.daySchedule.date}"
                        is TimelineItem.GapItem -> "gap-${item.gap.id}"
                    }
                }
            ) { item ->
                when (item) {
                    is TimelineItem.Day -> {
                        DayCard(
                            daySchedule = item.daySchedule,
                            isExpanded = item.daySchedule.date in expandedDays,
                            onDayClicked = { onDayClicked(item.daySchedule.date) },
                            onActivityClick = onActivityClick
                        )
                    }
                    is TimelineItem.GapItem -> {
                        CompactGapCard(
                            gap = item.gap,
                            fromLocation = locationMap[item.gap.fromLocationId],
                            toLocation = locationMap[item.gap.toLocationId],
                            onClick = { onGapClick(item.gap) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Sealed class representing items in the timeline
 */
private sealed class TimelineItem {
    data class Day(val daySchedule: DaySchedule) : TimelineItem()
    data class GapItem(val gap: Gap) : TimelineItem()
}

/**
 * Build a combined list of days and gaps in chronological order.
 *
 * Strategy: Show each gap only ONCE - right before the destination day
 * (i.e., after the last day of fromLocation, before first day of toLocation)
 */
private fun buildTimelineItems(
    daySchedules: List<DaySchedule>,
    gaps: List<Gap>
): List<TimelineItem> {
    val items = mutableListOf<TimelineItem>()
    val shownGaps = mutableSetOf<String>()

    daySchedules.forEachIndexed { index, daySchedule ->
        // Add the day
        items.add(TimelineItem.Day(daySchedule))

        // Check if we should show any gaps AFTER this day (before next day)
        if (index < daySchedules.size - 1) {
            val nextDay = daySchedules[index + 1]

            // Find gaps where:
            // - toLocationId matches the next day's location
            // - We haven't shown this gap yet
            gaps.forEach { gap ->
                if (gap.id !in shownGaps &&
                    gap.toLocationId == nextDay.location?.id) {
                    items.add(TimelineItem.GapItem(gap))
                    shownGaps.add(gap.id)
                }
            }
        }
    }

    return items
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
