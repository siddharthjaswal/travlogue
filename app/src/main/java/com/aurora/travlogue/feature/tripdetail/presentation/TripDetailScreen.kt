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
import com.aurora.travlogue.feature.tripdetail.components.dialogs.EditActivityDialog
import com.aurora.travlogue.feature.tripdetail.components.dialogs.EditBookingDialog
import com.aurora.travlogue.feature.tripdetail.components.dialogs.EditLocationDialog
import com.aurora.travlogue.feature.tripdetail.components.header.TripHeaderSection
import com.aurora.travlogue.feature.tripdetail.components.tabs.BookingsTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.LocationsTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.OverviewTab
import com.aurora.travlogue.feature.tripdetail.components.tabs.TimelineTab
import com.aurora.travlogue.feature.tripdetail.presentation.components.GapDetailSheet

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
        onAddBooking = viewModel::showAddBookingDialog,
        onActivityClick = viewModel::showEditActivityDialog,
        onLocationClick = viewModel::showEditLocationDialog,
        onBookingClick = viewModel::showEditBookingDialog,
        onGapClick = viewModel::showGapDetailDialog
    )

    // Dialogs
    if (uiState.showAddActivityDialog) {
        AddActivityDialog(
            onDismiss = viewModel::hideAddActivityDialog,
            onSave = { locationId, title, description, date, timeSlot, type, startTime, endTime ->
                viewModel.addActivity(
                    locationId,
                    title,
                    description,
                    date,
                    timeSlot,
                    type,
                    startTime,
                    endTime
                )
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
            onSave = { name, country, date, timezone, order ->
                viewModel.addLocation(name, country, date, timezone, order)
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
            onSave = { type, confirmationNumber, provider, startDateTime, endDateTime, timezone, endTimezone, fromLocation, toLocation, price, currency, notes ->
                viewModel.addBooking(
                    type,
                    confirmationNumber,
                    provider,
                    startDateTime,
                    endDateTime,
                    timezone,
                    endTimezone,
                    fromLocation,
                    toLocation,
                    price,
                    currency,
                    notes
                )
                viewModel.hideAddBookingDialog()
            }
        )
    }

    // Edit Dialogs
    uiState.editingActivity?.let { activity ->
        if (uiState.showEditActivityDialog) {
            EditActivityDialog(
                activity = activity,
                onDismiss = viewModel::hideEditActivityDialog,
                onSave = { updatedActivity ->
                    viewModel.updateActivity(updatedActivity)
                    viewModel.hideEditActivityDialog()
                },
                onDelete = {
                    viewModel.deleteActivity(activity.id)
                    viewModel.hideEditActivityDialog()
                },
                locations = uiState.locations
            )
        }
    }

    uiState.editingLocation?.let { location ->
        if (uiState.showEditLocationDialog) {
            EditLocationDialog(
                location = location,
                onDismiss = viewModel::hideEditLocationDialog,
                onSave = { updatedLocation ->
                    viewModel.updateLocation(updatedLocation)
                    viewModel.hideEditLocationDialog()
                },
                onDelete = {
                    viewModel.deleteLocation(location)
                    viewModel.hideEditLocationDialog()
                },
                tripStartDate = uiState.trip?.startDate,
                tripEndDate = uiState.trip?.endDate
            )
        }
    }

    uiState.editingBooking?.let { booking ->
        if (uiState.showEditBookingDialog) {
            EditBookingDialog(
                booking = booking,
                onDismiss = viewModel::hideEditBookingDialog,
                onSave = { updatedBooking ->
                    viewModel.updateBooking(updatedBooking)
                    viewModel.hideEditBookingDialog()
                },
                onDelete = {
                    viewModel.deleteBooking(booking)
                    viewModel.hideEditBookingDialog()
                }
            )
        }
    }

    // Gap Detail Sheet
    uiState.selectedGap?.let { gap ->
        if (uiState.showGapDetailDialog) {
            val sheetState = rememberModalBottomSheetState()
            val fromLocation = uiState.locations.find { it.id == gap.fromLocationId }
            val toLocation = uiState.locations.find { it.id == gap.toLocationId }

            GapDetailSheet(
                gap = gap,
                fromLocation = fromLocation,
                toLocation = toLocation,
                sheetState = sheetState,
                onDismiss = viewModel::hideGapDetailDialog,
                onAddTransit = {
                    viewModel.hideGapDetailDialog()
                    viewModel.showAddBookingDialog()
                },
                onAddActivity = {
                    viewModel.hideGapDetailDialog()
                    viewModel.showAddActivityDialog(gap.fromDate, gap.fromLocationId)
                },
                onMarkResolved = {
                    viewModel.markGapAsResolved(gap.id)
                },
                onDismissGap = {
                    viewModel.deleteGap(gap)
                }
            )
        }
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
    onAddBooking: () -> Unit,
    onActivityClick: (com.aurora.travlogue.core.data.local.entities.Activity) -> Unit,
    onLocationClick: (com.aurora.travlogue.core.data.local.entities.Location) -> Unit,
    onBookingClick: (com.aurora.travlogue.core.data.local.entities.Booking) -> Unit,
    onGapClick: (com.aurora.travlogue.core.data.local.entities.Gap) -> Unit
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
                        trip = uiState.trip
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
                                bookings = uiState.bookings,
                                gaps = uiState.gaps,
                                locations = uiState.locations,
                                expandedDays = uiState.expandedDays,
                                onDayClicked = onDayClicked,
                                onActivityClick = onActivityClick,
                                onBookingClick = onBookingClick,
                                onGapClick = onGapClick
                            )
                        }

                        TripDetailTab.LOCATIONS -> {
                            LocationsTab(
                                locations = uiState.locations,
                                onLocationClick = onLocationClick
                            )
                        }

                        TripDetailTab.BOOKINGS -> {
                            BookingsTab(
                                bookings = uiState.bookings,
                                onBookingClick = onBookingClick
                            )
                        }

                        TripDetailTab.OVERVIEW -> {
                            OverviewTab(
                                trip = uiState.trip,
                                locationCount = uiState.locationCount,
                                activityCount = uiState.activityCount,
                                bookingCount = uiState.bookingCount,
                                gaps = uiState.gaps,
                                locations = uiState.locations,
                                onGapClick = onGapClick
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
