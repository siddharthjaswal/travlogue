package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Expandable card for a single day in the timeline
 */
@Composable
fun DayCard(
    daySchedule: DaySchedule,
    isExpanded: Boolean,
    onDayClicked: () -> Unit,
    onActivityClick: (Activity) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onDayClicked),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Day Header
            DayHeader(
                daySchedule = daySchedule,
                isExpanded = isExpanded
            )

            // Expanded Content
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Divider()

                    if (daySchedule.hasActivities) {
                        // Show activities by time slot
                        daySchedule.activitiesByTimeSlot.forEach { (timeSlot, activities) ->
                            TimeSlotSection(
                                timeSlot = timeSlot,
                                activities = activities,
                                onActivityClick = onActivityClick
                            )
                        }
                    } else {
                        // Empty state
                        Text(
                            text = "No activities planned for this day",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    // Day Notes (if any)
                    if (!daySchedule.dayNotes.isNullOrBlank()) {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                        ) {
                            Text(
                                text = daySchedule.dayNotes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DayHeader(
    daySchedule: DaySchedule,
    isExpanded: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Day number and date
            Text(
                text = "Day ${daySchedule.dayNumber} • ${formatDate(daySchedule.date)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Location (if available)
            if (daySchedule.location != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = daySchedule.location.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Activity count preview
            if (!isExpanded && daySchedule.hasActivities) {
                val activityCount = daySchedule.activitiesByTimeSlot.values.flatten().size
                Text(
                    text = "$activityCount ${if (activityCount == 1) "activity" else "activities"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // Expand/Collapse icon
        Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Collapse" else "Expand",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun TimeSlotSection(
    timeSlot: TimeSlot,
    activities: List<Activity>,
    onActivityClick: (Activity) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Time slot label
        Text(
            text = when (timeSlot) {
                TimeSlot.MORNING -> "MORNING"
                TimeSlot.AFTERNOON -> "AFTERNOON"
                TimeSlot.EVENING -> "EVENING"
                TimeSlot.FULL_DAY -> "FULL DAY"
            },
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Activities
        activities.forEach { activity ->
            ActivityItem(
                activity = activity,
                onActivityClick = onActivityClick
            )
        }
    }
}

@Composable
private fun ActivityItem(
    activity: Activity,
    onActivityClick: (Activity) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onActivityClick(activity) }
            .padding(start = 8.dp, top = 4.dp, bottom = 4.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Activity type icon placeholder
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = getActivityIcon(activity.type),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        // Activity details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (!activity.description.isNullOrBlank()) {
                Text(
                    text = activity.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

private fun getActivityIcon(type: com.aurora.travlogue.core.data.local.entities.ActivityType): String {
    return when (type) {
        com.aurora.travlogue.core.data.local.entities.ActivityType.ATTRACTION -> "🎨"
        com.aurora.travlogue.core.data.local.entities.ActivityType.FOOD -> "🍴"
        com.aurora.travlogue.core.data.local.entities.ActivityType.BOOKING -> "🎫"
        com.aurora.travlogue.core.data.local.entities.ActivityType.TRANSIT -> "🚗"
        com.aurora.travlogue.core.data.local.entities.ActivityType.OTHER -> "📝"
    }
}

private fun formatDate(dateString: String): String {
    return try {
        if (dateString.startsWith("flexible-")) {
            "Flexible"
        } else {
            val date = LocalDate.parse(dateString)
            date.format(DateTimeFormatter.ofPattern("MMM d"))
        }
    } catch (e: Exception) {
        dateString
    }
}

// ==================== Previews ====================

@Preview(name = "Day Card - Collapsed", showBackground = true)
@Composable
private fun DayCardPreview_Collapsed() {
    MaterialTheme {
        DayCard(
            daySchedule = DaySchedule(
                date = "2025-07-01",
                dayNumber = 1,
                location = PreviewData.locationTokyo,
                activitiesByTimeSlot = mapOf(
                    TimeSlot.MORNING to listOf(PreviewData.activitySensoJi),
                    TimeSlot.AFTERNOON to listOf(PreviewData.activityShibuya)
                ),
                dayNotes = null
            ),
            isExpanded = false,
            onDayClicked = {},
            onActivityClick = {}
        )
    }
}

@Preview(name = "Day Card - Expanded", showBackground = true)
@Composable
private fun DayCardPreview_Expanded() {
    MaterialTheme {
        DayCard(
            daySchedule = DaySchedule(
                date = "2025-07-01",
                dayNumber = 1,
                location = PreviewData.locationTokyo,
                activitiesByTimeSlot = mapOf(
                    TimeSlot.MORNING to listOf(PreviewData.activitySensoJi),
                    TimeSlot.AFTERNOON to listOf(PreviewData.activityShibuya),
                    TimeSlot.EVENING to listOf(PreviewData.activityRamen)
                ),
                dayNotes = "Don't forget to buy a Suica card at the airport!"
            ),
            isExpanded = true,
            onDayClicked = {},
            onActivityClick = {}
        )
    }
}

@Preview(name = "Day Card - Empty", showBackground = true)
@Composable
private fun DayCardPreview_Empty() {
    MaterialTheme {
        DayCard(
            daySchedule = DaySchedule(
                date = "2025-07-02",
                dayNumber = 2,
                location = PreviewData.locationKyoto,
                activitiesByTimeSlot = emptyMap(),
                dayNotes = null
            ),
            isExpanded = true,
            onDayClicked = {},
            onActivityClick = {}
        )
    }
}
