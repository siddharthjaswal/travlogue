package com.aurora.travlogue.feature.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.core.data.local.entities.TripMockData
import com.aurora.travlogue.feature.home.components.EmptyState
import com.aurora.travlogue.feature.home.components.TripList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit
) {
    val trips by viewModel.allTrips.collectAsState()

    HomeScreenContent(
        trips = trips,
        onNavigateToCreateTrip = onNavigateToCreateTrip,
        onNavigateToPlan = onNavigateToPlan,
        onDeleteTrip = { viewModel.deleteTrip(it) }
    )
}

@Preview(showBackground = true, name = "Home Screen - With Trips")
@Composable
private fun HomeScreenWithTripsPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = TripMockData.sampleTrips,
            onNavigateToCreateTrip = {},
            onNavigateToPlan = {},
            onDeleteTrip = {}
        )
    }
}

@Preview(showBackground = true, name = "Home Screen - Empty")
@Composable
private fun HomeScreenEmptyPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = emptyList(),
            onNavigateToCreateTrip = {},
            onNavigateToPlan = {},
            onDeleteTrip = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    trips: List<Trip>,
    onNavigateToCreateTrip: () -> Unit,
    onNavigateToPlan: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit
) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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