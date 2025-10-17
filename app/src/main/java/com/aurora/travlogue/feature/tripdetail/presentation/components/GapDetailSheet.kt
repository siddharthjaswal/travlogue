package com.aurora.travlogue.feature.tripdetail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.FlightTakeoff
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.GapType
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Bottom sheet showing gap details and action buttons.
 *
 * Actions:
 * - Add Transit (for MISSING_TRANSIT)
 * - Add Activity (for UNPLANNED_DAY)
 * - Mark as Resolved
 * - Dismiss
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GapDetailSheet(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onAddTransit: () -> Unit,
    onAddActivity: () -> Unit,
    onMarkResolved: () -> Unit,
    onDismissGap: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header with icon and title
            GapDetailHeader(gap, fromLocation, toLocation)

            HorizontalDivider()

            // Gap description
            GapDetailDescription(gap, fromLocation, toLocation)

            // Action buttons
            GapActions(
                gap = gap,
                onAddTransit = onAddTransit,
                onAddActivity = onAddActivity,
                onMarkResolved = onMarkResolved,
                onDismissGap = onDismissGap,
                onClose = onDismiss
            )
        }
    }
}

@Composable
private fun GapDetailHeader(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?
) {
    val icon = when (gap.gapType) {
        GapType.MISSING_TRANSIT -> Icons.Outlined.DirectionsBus
        GapType.UNPLANNED_DAY -> Icons.Outlined.CalendarToday
        GapType.TIME_CONFLICT -> Icons.Outlined.Warning
    }

    val title = when (gap.gapType) {
        GapType.MISSING_TRANSIT -> "Missing Transit"
        GapType.UNPLANNED_DAY -> "Unplanned Days"
        GapType.TIME_CONFLICT -> "Schedule Conflict"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(28.dp)
            )
        }

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = getDurationText(gap),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun GapDetailDescription(
    gap: Gap,
    fromLocation: Location?,
    toLocation: Location?
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (gap.gapType) {
            GapType.MISSING_TRANSIT -> {
                InfoRow(
                    label = "From",
                    value = fromLocation?.name ?: "Unknown location"
                )
                InfoRow(
                    label = "To",
                    value = toLocation?.name ?: "Unknown location"
                )
                InfoRow(
                    label = "Date Range",
                    value = getFormattedDateRange(gap)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "You're traveling between different cities without a transit booking. " +
                            "Add a flight, train, or bus booking to fill this gap.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            GapType.UNPLANNED_DAY -> {
                InfoRow(
                    label = "Unplanned Days",
                    value = getDurationText(gap)
                )
                InfoRow(
                    label = "Date Range",
                    value = getFormattedDateRange(gap)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "These days don't have any planned activities or assigned locations. " +
                            "Add activities or locations to plan these days.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            GapType.TIME_CONFLICT -> {
                InfoRow(
                    label = "Date Range",
                    value = getFormattedDateRange(gap)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Activities or bookings have overlapping times. " +
                            "Review and adjust the schedule to resolve this conflict.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun GapActions(
    gap: Gap,
    onAddTransit: () -> Unit,
    onAddActivity: () -> Unit,
    onMarkResolved: () -> Unit,
    onDismissGap: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Primary action button
        when (gap.gapType) {
            GapType.MISSING_TRANSIT -> {
                Button(
                    onClick = onAddTransit,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FlightTakeoff,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Add Transit Booking")
                }
            }

            GapType.UNPLANNED_DAY -> {
                Button(
                    onClick = onAddActivity,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Add Activity")
                }
            }

            GapType.TIME_CONFLICT -> {
                // No specific action for time conflicts yet
            }
        }

        // Secondary actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    onMarkResolved()
                    onClose()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Mark Resolved")
            }

            OutlinedButton(
                onClick = {
                    onDismissGap()
                    onClose()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Dismiss")
            }
        }

        // Cancel button
        TextButton(
            onClick = onClose,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

// Helper functions

private fun getDurationText(gap: Gap): String {
    val fromDate = LocalDate.parse(gap.fromDate)
    val toDate = LocalDate.parse(gap.toDate)
    val days = ChronoUnit.DAYS.between(fromDate, toDate) + 1

    return when {
        days == 1L -> "1 day"
        else -> "$days days"
    }
}

private fun getFormattedDateRange(gap: Gap): String {
    val fromDate = LocalDate.parse(gap.fromDate)
    val toDate = LocalDate.parse(gap.toDate)
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    return "${fromDate.format(formatter)} - ${toDate.format(formatter)}"
}
