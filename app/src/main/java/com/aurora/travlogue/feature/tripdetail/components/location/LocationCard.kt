package com.aurora.travlogue.feature.tripdetail.components.location

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Card displaying a single location with order number
 */
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Order number badge
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = orderNumber.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            // Location details
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

                // Date (if available)
                if (location.date != null) {
                    Text(
                        text = formatLocationDate(location.date),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private fun formatLocationDate(dateString: String): String {
    return try {
        val date = LocalDate.parse(dateString)
        date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))
    } catch (e: Exception) {
        dateString
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
