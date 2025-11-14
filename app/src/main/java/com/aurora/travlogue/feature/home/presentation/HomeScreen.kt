package com.aurora.travlogue.feature.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.model.TripMockData
import com.aurora.travlogue.core.sync.NetworkConnectivityMonitor
import com.aurora.travlogue.feature.home.components.EmptyState
import com.aurora.travlogue.feature.home.components.TripList
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    networkMonitor: NetworkConnectivityMonitor = koinInject(),
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit,
    onNavigateToMock: () -> Unit = {}
) {
    val trips by viewModel.allTrips.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val isConnected by networkMonitor.isConnected.collectAsState(initial = true)

    HomeScreenContent(
        trips = trips,
        uiState = uiState,
        isConnected = isConnected,
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
            isConnected = true,
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
            isConnected = true,
            onNavigateToCreateTrip = {},
            onNavigateToPlan = {},
            onDeleteTrip = {},
            onNavigateToMock = {},
            onRefresh = {}
        )
    }
}

@Preview(showBackground = true, name = "Home Screen - Offline")
@Composable
private fun HomeScreenOfflinePreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = TripMockData.sampleTrips,
            uiState = com.aurora.travlogue.feature.home.presentation.HomeUiState(),
            isConnected = false,
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
    isConnected: Boolean,
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit,
    onNavigateToMock: () -> Unit = {},
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("My Trips")
                        // Offline indicator badge
                        if (!isConnected) {
                            Surface(
                                color = MaterialTheme.colorScheme.errorContainer,
                                shape = MaterialTheme.shapes.small
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CloudOff,
                                        contentDescription = "Offline",
                                        tint = MaterialTheme.colorScheme.onErrorContainer,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Offline",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        }
                    }
                },
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
                uiState.syncMessage?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            // Error message
            uiState.error?.let { error ->
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = error,
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