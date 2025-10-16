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
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.core.data.local.entities.TripMockData
import com.aurora.travlogue.feature.home.components.CreateTripDialog
import com.aurora.travlogue.feature.home.components.EmptyState
import com.aurora.travlogue.feature.home.components.TripList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToPlan: (String) -> Unit
) {
    val trips by viewModel.allTrips.collectAsState()
    val showCreateDialog by viewModel.showCreateDialog.collectAsState()

    HomeScreenContent(
        trips = trips,
        showCreateDialog = showCreateDialog,
        onNavigateToPlan = onNavigateToPlan,
        onDeleteTrip = { viewModel.deleteTrip(it) },
        onShowCreateDialog = { viewModel.showCreateDialog() },
        onHideCreateDialog = { viewModel.hideCreateDialog() },
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

@Preview(showBackground = true, name = "Home Screen - With Trips")
@Composable
private fun HomeScreenWithTripsPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = TripMockData.sampleTrips,
            showCreateDialog = false,
            onNavigateToPlan = {},
            onDeleteTrip = {},
            onShowCreateDialog = {},
            onHideCreateDialog = {},
            onCreateTrip = { _, _, _, _, _, _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Home Screen - Empty")
@Composable
private fun HomeScreenEmptyPreview() {
    MaterialTheme {
        HomeScreenContent(
            trips = emptyList(),
            showCreateDialog = false,
            onNavigateToPlan = {},
            onDeleteTrip = {},
            onShowCreateDialog = {},
            onHideCreateDialog = {},
            onCreateTrip = { _, _, _, _, _, _, _ -> }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    trips: List<Trip>,
    showCreateDialog: Boolean,
    onNavigateToPlan: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit,
    onShowCreateDialog: () -> Unit,
    onHideCreateDialog: () -> Unit,
    onCreateTrip: (String, String, DateType, String?, String?, String?, Int?) -> Unit
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
                onClick = onShowCreateDialog,
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
                EmptyState(onCreateTrip = onShowCreateDialog)
            } else {
                TripList(
                    trips = trips,
                    onTripClick = onNavigateToPlan,
                    onDeleteTrip = onDeleteTrip
                )
            }
        }

        if (showCreateDialog) {
            CreateTripDialog(
                onDismiss = onHideCreateDialog,
                onCreateTrip = onCreateTrip
            )
        }
    }
}