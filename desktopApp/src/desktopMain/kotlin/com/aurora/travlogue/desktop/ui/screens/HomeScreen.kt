package com.aurora.travlogue.desktop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.feature.home.presentation.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Home screen showing the list of all trips
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToMock: () -> Unit,
    onNavigateToTripDetail: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val trips by viewModel.allTrips.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var tripToDelete by remember { mutableStateOf<Trip?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Travlogue") },
                actions = {
                    // Mock button
                    TextButton(onClick = onNavigateToMock) {
                        Text("Mock")
                    }
                    // Refresh button
                    IconButton(onClick = { viewModel.refreshTrips() }) {
                        Icon(Icons.Default.Refresh, "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreateTrip) {
                Icon(Icons.Default.Add, "Create Trip")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.refreshTrips() }) {
                            Text("Retry")
                        }
                    }
                }
                trips.isEmpty() -> {
                    // Empty state
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No trips yet",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Create your first trip to get started",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = onNavigateToCreateTrip) {
                            Icon(Icons.Default.Add, null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Create Trip")
                        }
                    }
                }
                else -> {
                    // Trip list
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = trips,
                            key = { it.id }
                        ) { trip ->
                            TripCard(
                                trip = trip,
                                onClick = { onNavigateToTripDetail(trip.id) },
                                onDeleteClick = {
                                    tripToDelete = trip
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Delete confirmation dialog
    if (showDeleteDialog && tripToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                tripToDelete = null
            },
            title = { Text("Delete Trip?") },
            text = {
                Text("Are you sure you want to delete '${tripToDelete?.name}'? This action cannot be undone.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tripToDelete?.let { viewModel.deleteTrip(it) }
                        showDeleteDialog = false
                        tripToDelete = null
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        tripToDelete = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

/**
 * Card displaying a single trip
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripCard(
    trip: Trip,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Trip name
                Text(
                    text = trip.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Origin city
                Text(
                    text = "From ${trip.originCity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Dates
                val dateText = when (trip.dateType) {
                    DateType.FIXED -> {
                        if (trip.startDate != null && trip.endDate != null) {
                            "${trip.startDate} to ${trip.endDate}"
                        } else {
                            "Dates not set"
                        }
                    }
                    DateType.FLEXIBLE -> {
                        buildString {
                            append(trip.flexibleMonth ?: "Flexible dates")
                            if (trip.flexibleDuration != null) {
                                append(" (${trip.flexibleDuration} days)")
                            }
                        }
                    }
                }

                Text(
                    text = dateText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Delete button
            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete trip",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

// Extension function for retry (if not in ViewModel)
private fun HomeViewModel.retryLoading() {
    // The ViewModel automatically loads trips in init, so we don't need a separate retry method
    // This is a placeholder for future error handling
}
