package com.aurora.travlogue.feature.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.model.TripMockData
import com.aurora.travlogue.feature.home.components.EmptyState
import com.aurora.travlogue.feature.home.components.TripList
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit,
    onNavigateToMock: () -> Unit = {}
) {
    val trips by viewModel.allTrips.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    HomeScreenContent(
        trips = trips,
        uiState = uiState,
        onNavigateToCreateTrip = onNavigateToCreateTrip,
        onNavigateToPlan = onNavigateToPlan,
        onDeleteTrip = { viewModel.deleteTrip(it) },
        onNavigateToMock = onNavigateToMock,
        onRefresh = { viewModel.refreshTrips() }
    )
}

@Preview(showBackground = true, name = "Home Screen - With Trips")
@Composable
private fun HomeScreenWithTripsPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = TripMockData.sampleTrips,
            uiState = com.aurora.travlogue.feature.home.presentation.HomeUiState(),
            onNavigateToCreateTrip = {},
            onNavigateToPlan = {},
            onDeleteTrip = {},
            onNavigateToMock = {},
            onRefresh = {}
        )
    }
}

@Preview(showBackground = true, name = "Home Screen - Empty")
@Composable
private fun HomeScreenEmptyPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = emptyList(),
            uiState = com.aurora.travlogue.feature.home.presentation.HomeUiState(),
            onNavigateToCreateTrip = {},
            onNavigateToPlan = {},
            onDeleteTrip = {},
            onNavigateToMock = {},
            onRefresh = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    trips: List<Trip>,
    uiState: com.aurora.travlogue.feature.home.presentation.HomeUiState,
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit,
    onNavigateToMock: () -> Unit = {},
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Trips") },
                actions = {
                    // Mock data generator icon (for testing)
                    IconButton(onClick = onNavigateToMock) {
                        Icon(
                            imageVector = Icons.Default.Science,
                            contentDescription = "Mock Data Generator",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateTrip,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Trip"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Sync indicator
            if (uiState.isSyncing) {
                LinearProgressIndicator(
                    progress = { uiState.syncProgress },
                    modifier = Modifier.fillMaxWidth()
                )
                if (uiState.syncMessage != null) {
                    Text(
                        text = uiState.syncMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            // Error message
            if (uiState.error != null) {
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = uiState.error,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Trip list
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (trips.isEmpty()) {
                    EmptyState(onCreateTrip = onNavigateToCreateTrip)
                } else {
                    TripList(
                        trips = trips,
                        onTripClick = onNavigateToPlan,
                        onDeleteTrip = onDeleteTrip
                    )
                }
            }
        }
    }
}