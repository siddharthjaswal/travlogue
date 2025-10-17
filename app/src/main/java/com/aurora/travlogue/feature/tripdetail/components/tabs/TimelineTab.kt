package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.feature.tripdetail.components.timeline.DayCard
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule

/**
 * Timeline tab showing day-by-day schedule
 */
@Composable
fun TimelineTab(
    daySchedules: List<DaySchedule>,
    expandedDays: Set<String>,
    onDayClicked: (String) -> Unit,
    onActivityClick: (com.aurora.travlogue.core.data.local.entities.Activity) -> Unit,
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
            items(
                items = daySchedules,
                key = { it.date }
            ) { daySchedule ->
                DayCard(
                    daySchedule = daySchedule,
                    isExpanded = daySchedule.date in expandedDays,
                    onDayClicked = { onDayClicked(daySchedule.date) },
                    onActivityClick = onActivityClick
                )
            }
        }
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
