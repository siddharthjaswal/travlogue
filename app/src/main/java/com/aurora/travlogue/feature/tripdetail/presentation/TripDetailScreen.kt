package com.aurora.travlogue.feature.tripdetail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.feature.tripdetail.components.dialogs.AddActivityDialog
import com.aurora.travlogue.feature.tripdetail.components.dialogs.AddBookingDialog
import com.aurora.travlogue.feature.tripdetail.components.dialogs.AddLocationDialog
import com.aurora.travlogue.feature.tripdetail.components.header.TripHeaderSection
import com.aurora.travlogue.feature.tripdetail.components.tabs.BookingsTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.LocationsTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.OverviewTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.TimelineTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailScreen(
    viewModel: TripDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Handle UI events
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                TripDetailUiEvent.NavigateBack -> onNavigateBack()
                is TripDetailUiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is TripDetailUiEvent.NavigateToEditTrip -> {
                    // TODO: Navigate to edit trip screen
                }
                is TripDetailUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    TripDetailScreenContent(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onBackPressed = viewModel::onBackPressed,
        onTabSelected = viewModel::onTabSelected,
        onDayClicked = viewModel::onDayClicked,
        onEditTrip = viewModel::onEditTrip,
        onRetry = viewModel::retryLoading,
        onAddActivity = viewModel::showAddActivityDialog,
        onAddLocation = viewModel::showAddLocationDialog,
        onAddBooking = viewModel::showAddBookingDialog
    )

    // Dialogs
    if (uiState.showAddActivityDialog) {
        AddActivityDialog(
            onDismiss = viewModel::hideAddActivityDialog,
            onSave = { locationId, title, description, date, timeSlot, type ->
                viewModel.addActivity(locationId, title, description, date, timeSlot, type)
                viewModel.hideAddActivityDialog()
            },
            locations = uiState.locations,
            preselectedLocationId = uiState.preselectedLocationId,
            preselectedDate = uiState.preselectedDate
        )
    }

    if (uiState.showAddLocationDialog) {
        AddLocationDialog(
            onDismiss = viewModel::hideAddLocationDialog,
            onSave = { name, country, date, order ->
                viewModel.addLocation(name, country, date, order)
                viewModel.hideAddLocationDialog()
            },
            currentLocationCount = uiState.locationCount,
            tripStartDate = uiState.trip?.startDate,
            tripEndDate = uiState.trip?.endDate
        )
    }

    if (uiState.showAddBookingDialog) {
        AddBookingDialog(
            onDismiss = viewModel::hideAddBookingDialog,
            onSave = { type, confirmationNumber, provider, startDateTime, endDateTime, timezone, fromLocation, toLocation, price, currency, notes ->
                viewModel.addBooking(type, confirmationNumber, provider, startDateTime, endDateTime, timezone, fromLocation, toLocation, price, currency, notes)
                viewModel.hideAddBookingDialog()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TripDetailScreenContent(
    uiState: TripDetailUiState,
    snackbarHostState: SnackbarHostState,
    onBackPressed: () -> Unit,
    onTabSelected: (TripDetailTab) -> Unit,
    onDayClicked: (String) -> Unit,
    onEditTrip: () -> Unit,
    onRetry: () -> Unit,
    onAddActivity: (String?, String?) -> Unit,
    onAddLocation: () -> Unit,
    onAddBooking: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.trip?.name ?: "Trip Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onEditTrip) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
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
            if (uiState.trip != null && !uiState.isLoading) {
                FloatingActionButton(
                    onClick = {
                        when (uiState.selectedTab) {
                            TripDetailTab.TIMELINE -> onAddActivity(null, null)
                            TripDetailTab.LOCATIONS -> onAddLocation()
                            TripDetailTab.BOOKINGS -> onAddBooking()
                            TripDetailTab.OVERVIEW -> {}  // No FAB for overview
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = when (uiState.selectedTab) {
                            TripDetailTab.TIMELINE -> "Add Activity"
                            TripDetailTab.LOCATIONS -> "Add Location"
                            TripDetailTab.BOOKINGS -> "Add Booking"
                            TripDetailTab.OVERVIEW -> null
                        }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    LoadingState()
                }
                uiState.error != null -> {
                    ErrorState(
                        message = uiState.error,
                        onRetry = onRetry
                    )
                }
                uiState.trip != null -> {
                    // Trip Header Section
                    TripHeaderSection(
                        trip = uiState.trip,
                        locationCount = uiState.locationCount,
                        activityCount = uiState.activityCount,
                        bookingCount = uiState.bookingCount,
                        notesCount = uiState.notesCount
                    )

                    // Tab Navigation
                    TabRow(
                        selectedTabIndex = uiState.selectedTab.ordinal,
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        TripDetailTab.entries.forEach { tab ->
                            Tab(
                                selected = uiState.selectedTab == tab,
                                onClick = { onTabSelected(tab) },
                                text = {
                                    Text(
                                        text = when (tab) {
                                            TripDetailTab.TIMELINE -> "Timeline"
                                            TripDetailTab.LOCATIONS -> "Locations"
                                            TripDetailTab.BOOKINGS -> "Bookings"
                                            TripDetailTab.OVERVIEW -> "Overview"
                                        }
                                    )
                                }
                            )
                        }
                    }

                    // Tab Content
                    when (uiState.selectedTab) {
                        TripDetailTab.TIMELINE -> {
                            TimelineTab(
                                daySchedules = uiState.daySchedules,
                                expandedDays = uiState.expandedDays,
                                onDayClicked = onDayClicked
                            )
                        }
                        TripDetailTab.LOCATIONS -> {
                            LocationsTab(
                                locations = uiState.locations
                            )
                        }
                        TripDetailTab.BOOKINGS -> {
                            BookingsTab(
                                bookings = uiState.bookings
                            )
                        }
                        TripDetailTab.OVERVIEW -> {
                            OverviewTab(
                                trip = uiState.trip,
                                locationCount = uiState.locationCount,
                                activityCount = uiState.activityCount,
                                bookingCount = uiState.bookingCount
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
