package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.design.AppTheme
import com.aurora.travlogue.feature.tripdetail.components.location.LocationCard

/**
 * Locations tab showing all trip locations
 */
@Composable
fun LocationsTab(
    locations: List<Location>,
    onLocationClick: (Location) -> Unit,
    onAddLocation: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (locations.isEmpty()) {
        EmptyLocationsState(
            onAddLocation = onAddLocation,
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(
                items = locations,
                key = { _, location -> location.id }
            ) { index, location ->
                LocationCard(
                    location = location,
                    orderNumber = index + 1,
                    onLocationClick = onLocationClick
                )
            }
        }
    }
}

@Composable
private fun EmptyLocationsState(
    onAddLocation: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                text = "📍",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No locations added",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Add destinations to your trip",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onAddLocation) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Location")
            }
        }
    }
}

// ==================== Previews ====================

@Preview(showBackground = true)
@Composable
private fun LocationsTabPreview_WithLocations() {
    AppTheme {
        LocationsTab(
            locations = PreviewData.sampleLocations,
            onLocationClick = {},
            onAddLocation = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LocationsTabPreview_Empty() {
    AppTheme {
        LocationsTab(
            locations = emptyList(),
            onLocationClick = {},
            onAddLocation = {}
        )
    }
}
