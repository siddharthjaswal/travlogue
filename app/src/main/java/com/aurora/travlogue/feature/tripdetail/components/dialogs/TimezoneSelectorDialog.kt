package com.aurora.travlogue.feature.tripdetail.components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Timezone selector dialog with search functionality
 * Shows common timezones first, then all available timezones
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimezoneSelectorDialog(
    currentZone: ZoneId,
    onZoneSelected: (ZoneId) -> Unit,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    // Common timezones for quick access
    val commonTimezones = remember {
        listOf(
            "America/New_York",      // EST/EDT
            "America/Chicago",       // CST/CDT
            "America/Denver",        // MST/MDT
            "America/Los_Angeles",   // PST/PDT
            "America/Phoenix",       // MST (no DST)
            "America/Anchorage",     // AKST/AKDT
            "Pacific/Honolulu",      // HST
            "Europe/London",         // GMT/BST
            "Europe/Paris",          // CET/CEST
            "Europe/Berlin",         // CET/CEST
            "Europe/Rome",           // CET/CEST
            "Europe/Madrid",         // CET/CEST
            "Europe/Athens",         // EET/EEST
            "Europe/Moscow",         // MSK
            "Asia/Dubai",            // GST
            "Asia/Kolkata",          // IST
            "Asia/Bangkok",          // ICT
            "Asia/Singapore",        // SGT
            "Asia/Hong_Kong",        // HKT
            "Asia/Shanghai",         // CST
            "Asia/Tokyo",            // JST
            "Asia/Seoul",            // KST
            "Australia/Sydney",      // AEDT/AEST
            "Australia/Melbourne",   // AEDT/AEST
            "Australia/Perth",       // AWST
            "Pacific/Auckland",      // NZDT/NZST
        ).map { ZoneId.of(it) }
    }

    // All available timezones
    val allTimezones = remember {
        ZoneId.getAvailableZoneIds()
            .sorted()
            .map { ZoneId.of(it) }
    }

    // Filter timezones based on search query
    val filteredTimezones = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            commonTimezones
        } else {
            allTimezones.filter { zone ->
                zone.id.contains(searchQuery, ignoreCase = true) ||
                        formatTimezoneForSearch(zone).contains(searchQuery, ignoreCase = true)
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Select Timezone",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Search Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search timezones...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Section Header
                if (searchQuery.isBlank()) {
                    Text(
                        text = "Common Timezones",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                } else {
                    Text(
                        text = "${filteredTimezones.size} results",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Timezone List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(
                        items = filteredTimezones,
                        key = { it.id }
                    ) { zone ->
                        TimezoneItem(
                            zone = zone,
                            isSelected = zone == currentZone,
                            onClick = { onZoneSelected(zone) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Single timezone item in the list
 */
@Composable
private fun TimezoneItem(
    zone: ZoneId,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val now = ZonedDateTime.now(zone)
    val offset = now.format(DateTimeFormatter.ofPattern("XXX"))
    val displayName = zone.id.replace("_", " ")

    Surface(
        onClick = onClick,
        color = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
                Text(
                    text = now.format(DateTimeFormatter.ofPattern("h:mm a")),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }

            Text(
                text = offset,
                style = MaterialTheme.typography.labelLarge,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
        }
    }
}

@Preview(name = "Timezone Selector Dialog", showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun TimezoneSelectorDialogPreview() {
    MaterialTheme {
        TimezoneSelectorDialog(
            currentZone = ZoneId.of("Asia/Tokyo"),
            onZoneSelected = {},
            onDismiss = {}
        )
    }
}

@Preview(name = "Timezone Item", showBackground = true, widthDp = 360)
@Composable
private fun TimezoneItemPreview() {
    MaterialTheme {
        TimezoneItem(
            zone = ZoneId.of("Europe/London"),
            isSelected = true,
            onClick = {}
        )
    }
}

/**
 * Format timezone for search matching
 */
private fun formatTimezoneForSearch(zone: ZoneId): String {
    val now = ZonedDateTime.now(zone)
    val offset = now.format(DateTimeFormatter.ofPattern("XXX"))
    return "${zone.id} $offset"
}
