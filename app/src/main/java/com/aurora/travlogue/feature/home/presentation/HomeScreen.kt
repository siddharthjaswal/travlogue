package com.aurora.travlogue.feature.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.feature.home.components.CreateTripDialog
import com.aurora.travlogue.feature.home.components.TripCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToPlan: (String) -> Unit
) {
    val trips by viewModel.allTrips.collectAsState()
    val showCreateDialog by viewModel.showCreateDialog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Trips") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showCreateDialog() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Trip"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (trips.isEmpty()) {
                EmptyState(
                    onCreateTrip = { viewModel.showCreateDialog() }
                )
            } else {
                TripList(
                    trips = trips,
                    onTripClick = onNavigateToPlan,
                    onDeleteTrip = { viewModel.deleteTrip(it) }
                )
            }
        }

        // Show create trip dialog
        if (showCreateDialog) {
            CreateTripDialog(
                onDismiss = { viewModel.hideCreateDialog() },
                onCreateTrip = { name, originCity, dateType, startDate, endDate, flexibleMonth, flexibleDuration ->
                    viewModel.createTrip(
                        name = name,
                        originCity = originCity,
                        dateType = dateType,
                        startDate = startDate,
                        endDate = endDate,
                        flexibleMonth = flexibleMonth,
                        flexibleDuration = flexibleDuration
                    )
                }
            )
        }
    }
}

@Composable
private fun TripList(
    trips: List<Trip>,
    onTripClick: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit
) {
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
                onClick = { onTripClick(trip.id) },
                onDelete = { onDeleteTrip(trip) }
            )
        }
    }
}

@Composable
private fun EmptyState(
    onCreateTrip: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "✈️",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No trips yet",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Start planning your next adventure by creating your first trip",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onCreateTrip,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Create Trip")
        }
    }
}