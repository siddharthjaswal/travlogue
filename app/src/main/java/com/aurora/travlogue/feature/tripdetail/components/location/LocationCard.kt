package com.aurora.travlogue.feature.tripdetail.components.location

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Card displaying a single location with order number
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocationCard(
    location: Location,
    orderNumber: Int,
    onLocationClick: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onLocationClick(location) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateBadge(location = location, fallbackOrderNumber = orderNumber)
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = location.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = location.country,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            val stayInfo = computeStayInfo(
                arrivalDateTime = location.arrivalDateTime,
                departureDateTime = location.departureDateTime,
                fallbackDate = location.date
            )

            if (stayInfo != null) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoChip(
                        icon = Icons.Default.CalendarToday,
                        contentDescription = "Stay",
                        label = stayInfo.label,
                        supportingLabel = stayInfo.supporting
                    )
                }
            }

            if (location.arrivalDateTime != null || location.departureDateTime != null) {
                Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    location.arrivalDateTime?.let { arrival ->
                        TimingRow(
                            icon = Icons.Default.FlightLand,
                            iconTint = MaterialTheme.colorScheme.primary,
                            label = "Arrival",
                            value = formatDateTime(arrival)
                        )
                    }
                    location.departureDateTime?.let { departure ->
                        TimingRow(
                            icon = Icons.Default.FlightTakeoff,
                            iconTint = MaterialTheme.colorScheme.secondary,
                            label = "Departure",
                            value = formatDateTime(departure)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DateBadge(
    location: Location,
    fallbackOrderNumber: Int
) {
    val badgeContent = remember(location.arrivalDateTime, location.date, fallbackOrderNumber) {
        computeBadgeContent(location, fallbackOrderNumber)
    }
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .widthIn(min = 60.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = badgeContent.primary,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            badgeContent.secondary?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun InfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    label: String,
    supportingLabel: String? = null
) {
    val displayText = supportingLabel?.takeIf { it.isNotBlank() }?.let {
        "$label • $it"
    } ?: label
    AssistChip(
        onClick = {},
        enabled = false,
        colors = AssistChipDefaults.assistChipColors(
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledLeadingIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(16.dp)
            )
        },
        label = {
            Text(
                text = displayText,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier.defaultMinSize(minHeight = 32.dp)
    )
}

@Composable
private fun TimingRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: androidx.compose.ui.graphics.Color,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            color = iconTint.copy(alpha = 0.12f),
            shape = MaterialTheme.shapes.small
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp)
            )
        }
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

private fun formatLocationDate(dateString: String): String {
    return try {
        val date = LocalDate.parse(dateString)
        date.format(DateTimeFormatter.ofPattern("MMM d"))
    } catch (e: Exception) {
        dateString
    }
}

/**
 * Formats ISO 8601 datetime with timezone to a readable format
 * Input: "2025-07-02T14:30:00+09:00"
 * Output: "Jul 2, 2:30 PM"
 */
private fun formatDateTime(dateTimeString: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateTimeString)
        zonedDateTime.format(DateTimeFormatter.ofPattern("MMM d, h:mm a"))
    } catch (e: Exception) {
        // Fallback to showing just the string if parsing fails
        dateTimeString
    }
}

private fun extractPrimaryDate(location: Location): LocalDate? {
    location.arrivalDateTime?.let {
        return runCatching { ZonedDateTime.parse(it).toLocalDate() }.getOrNull()
    }
    location.date?.let {
        return runCatching { LocalDate.parse(it) }.getOrNull()
    }
    return null
}

private data class StayInfo(
    val label: String,
    val supporting: String?
)

private data class BadgeContent(
    val primary: String,
    val secondary: String?
)

private fun computeBadgeContent(
    location: Location,
    fallbackOrderNumber: Int
): BadgeContent {
    val date = extractPrimaryDate(location)
    return if (date != null) {
        BadgeContent(
            primary = date.format(DateTimeFormatter.ofPattern("d")),
            secondary = date.format(DateTimeFormatter.ofPattern("EEE"))
        )
    } else {
        BadgeContent(
            primary = fallbackOrderNumber.toString(),
            secondary = null
        )
    }
}

private fun computeStayInfo(
    arrivalDateTime: String?,
    departureDateTime: String?,
    fallbackDate: String?
): StayInfo? {
    return when {
        arrivalDateTime != null && departureDateTime != null -> {
            runCatching {
                val arrival = ZonedDateTime.parse(arrivalDateTime)
                val departure = ZonedDateTime.parse(departureDateTime)
                val nights = java.time.temporal.ChronoUnit.DAYS.between(
                    arrival.toLocalDate(),
                    departure.toLocalDate()
                ).coerceAtLeast(0)
                val nightsInt = nights.toInt()
                val nightsLabel = if (nightsInt > 0) {
                    "$nightsInt ${if (nightsInt == 1) "night" else "nights"}"
                } else {
                    "Same-day visit"
                }
                StayInfo(label = nightsLabel, supporting = null)
            }.getOrNull()
        }

        fallbackDate != null -> StayInfo(
            label = formatLocationDate(fallbackDate),
            supporting = null
        )

        else -> null
    }
}


// ==================== Previews ====================

@Preview(name = "Location Card - With Date", showBackground = true)
@Composable
private fun LocationCardPreview_WithDate() {
    MaterialTheme {
        LocationCard(
            location = PreviewData.locationTokyo,
            orderNumber = 1,
            onLocationClick = {}
        )
    }
}

@Preview(name = "Location Card - Without Date", showBackground = true)
@Composable
private fun LocationCardPreview_WithoutDate() {
    MaterialTheme {
        LocationCard(
            location = PreviewData.locationTokyo.copy(date = null),
            orderNumber = 2,
            onLocationClick = {}
        )
    }
}

@Preview(name = "Location Card - Different Numbers", showBackground = true)
@Composable
private fun LocationCardPreview_DifferentNumbers() {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            LocationCard(
                location = PreviewData.locationTokyo,
                orderNumber = 1,
                onLocationClick = {}
            )
            LocationCard(
                location = PreviewData.locationKyoto,
                orderNumber = 2,
                onLocationClick = {}
            )
            LocationCard(
                location = PreviewData.locationOsaka,
                orderNumber = 3,
                onLocationClick = {}
            )
        }
    }
}
