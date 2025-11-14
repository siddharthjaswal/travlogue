package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Activity
import com.aurora.travlogue.core.domain.model.ActivityType
import com.aurora.travlogue.core.domain.model.TimeSlot
import kotlinx.datetime.LocalTime

/**
 * Individual activity card for timeline
 * Replaces the DayCard wrapper
 */
@Composable
fun ActivityTimelineCard(
    activity: Activity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Activity icon
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = getActivityTypeColor(activity.type),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = getActivityTypeIcon(activity.type),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Activity title
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Time info (specific time or time slot)
                if (activity.startTime != null || activity.timeSlot != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = formatActivityTime(activity),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Description
                val description = activity.description
                if (!description.isNullOrBlank()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }

                // Activity type label
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = getActivityTypeColor(activity.type).copy(alpha = 0.2f),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(
                        text = getActivityTypeDisplay(activity.type),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

/**
 * Card shown when no activities are planned for a day
 */
@Composable
fun NothingPlannedCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Relaxation icon
            Text(
                text = "☕",
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = getCreativeEmptyMessage(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Perfect day to explore freely!",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}

/**
 * Format activity time from specific time or time slot
 */
private fun formatActivityTime(activity: Activity): String {
    // Specific time takes priority
    if (activity.startTime != null) {
        return try {
            val startTime = LocalTime.parse(activity.startTime!!)
            val endTime = activity.endTime?.let { LocalTime.parse(it) }

            val formattedStart = formatTime(startTime)
            if (endTime != null) {
                val formattedEnd = formatTime(endTime)
                "$formattedStart - $formattedEnd"
            } else {
                formattedStart
            }
        } catch (e: Exception) {
            activity.timeSlot?.let { getTimeSlotDisplay(it) } ?: "All day"
        }
    }

    // Fall back to time slot
    return activity.timeSlot?.let { getTimeSlotDisplay(it) } ?: "All day"
}

/**
 * Format LocalTime to 12-hour format
 */
private fun formatTime(time: LocalTime): String {
    val hour = if (time.hour == 0) 12 else if (time.hour > 12) time.hour - 12 else time.hour
    val minute = time.minute.toString().padStart(2, '0')
    val period = if (time.hour < 12) "AM" else "PM"
    return "$hour:$minute $period"
}

/**
 * Get icon for activity type
 */
private fun getActivityTypeIcon(type: ActivityType) = when (type) {
    ActivityType.SIGHTSEEING -> Icons.Default.Place
    ActivityType.DINING -> Icons.Default.Place
    ActivityType.ENTERTAINMENT -> Icons.Default.Info
    ActivityType.SHOPPING -> Icons.Default.Place
    ActivityType.RELAXATION -> Icons.Default.Info
    ActivityType.ATTRACTION -> Icons.Default.Place
    ActivityType.FOOD -> Icons.Default.Place
    ActivityType.BOOKING -> Icons.Default.Info
    ActivityType.TRANSIT -> Icons.Default.Place
    ActivityType.OTHER -> Icons.Default.Info
}

/**
 * Get color for activity type
 */
@Composable
private fun getActivityTypeColor(type: ActivityType) = when (type) {
    ActivityType.SIGHTSEEING -> MaterialTheme.colorScheme.primaryContainer
    ActivityType.DINING -> MaterialTheme.colorScheme.tertiaryContainer
    ActivityType.ENTERTAINMENT -> MaterialTheme.colorScheme.secondaryContainer
    ActivityType.SHOPPING -> MaterialTheme.colorScheme.primaryContainer
    ActivityType.RELAXATION -> MaterialTheme.colorScheme.surfaceVariant
    ActivityType.ATTRACTION -> MaterialTheme.colorScheme.primaryContainer
    ActivityType.FOOD -> MaterialTheme.colorScheme.tertiaryContainer
    ActivityType.BOOKING -> MaterialTheme.colorScheme.secondaryContainer
    ActivityType.TRANSIT -> MaterialTheme.colorScheme.primaryContainer
    ActivityType.OTHER -> MaterialTheme.colorScheme.surfaceVariant
}

/**
 * Get display text for activity type
 */
private fun getActivityTypeDisplay(type: ActivityType) = when (type) {
    ActivityType.SIGHTSEEING -> "Sightseeing"
    ActivityType.DINING -> "Dining"
    ActivityType.ENTERTAINMENT -> "Entertainment"
    ActivityType.SHOPPING -> "Shopping"
    ActivityType.RELAXATION -> "Relaxation"
    ActivityType.ATTRACTION -> "Attraction"
    ActivityType.FOOD -> "Dining"
    ActivityType.BOOKING -> "Booking"
    ActivityType.TRANSIT -> "Transit"
    ActivityType.OTHER -> "Activity"
}

/**
 * Get display text for time slot
 */
private fun getTimeSlotDisplay(timeSlot: TimeSlot) = when (timeSlot) {
    TimeSlot.MORNING -> "Morning (6 AM - 12 PM)"
    TimeSlot.AFTERNOON -> "Afternoon (12 PM - 5 PM)"
    TimeSlot.EVENING -> "Evening (5 PM - 10 PM)"
    TimeSlot.FULL_DAY -> "Full Day"
}

/**
 * Get creative messages for empty days
 */
private fun getCreativeEmptyMessage(): String {
    val messages = listOf(
        "Nothing planned for today",
        "Free day ahead",
        "Day off to relax",
        "Unscheduled adventures await",
        "Open day for spontaneity",
        "Flexible exploration day",
        "Rest & recharge day"
    )
    return messages.random()
}
