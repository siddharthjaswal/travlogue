package com.aurora.travlogue.feature.createtrip.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.domain.model.DateType
import org.koin.compose.viewmodel.koinViewModel
import com.aurora.travlogue.feature.createtrip.components.BottomActionBar
import com.aurora.travlogue.feature.createtrip.components.ComingSoonCard
import com.aurora.travlogue.feature.createtrip.components.TravelDatesCard
import com.aurora.travlogue.feature.createtrip.components.TripDetailsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    viewModel: CreateTripViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Handle UI events
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                CreateTripUiEvent.NavigateBack -> onNavigateBack()
                CreateTripUiEvent.TripCreatedSuccess -> {
                    snackbarHostState.showSnackbar("Trip created successfully!")
                    onNavigateBack()
                }
                is CreateTripUiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    CreateTripScreenContent(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onTripNameChanged = viewModel::onTripNameChanged,
        onOriginCityChanged = viewModel::onOriginCityChanged,
        onDateTypeSelected = viewModel::onDateTypeSelected,
        onStartDateSelected = viewModel::onStartDateSelected,
        onEndDateSelected = viewModel::onEndDateSelected,
        onFlexibleMonthChanged = viewModel::onFlexibleMonthChanged,
        onFlexibleDurationChanged = viewModel::onFlexibleDurationChanged,
        onCreateTripClicked = viewModel::onCreateTripClicked,
        onBackPressed = viewModel::onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTripScreenContent(
    uiState: CreateTripUiState,
    snackbarHostState: SnackbarHostState,
    onTripNameChanged: (String) -> Unit,
    onOriginCityChanged: (String) -> Unit,
    onDateTypeSelected: (DateType) -> Unit,
    onStartDateSelected: (kotlinx.datetime.LocalDate) -> Unit,
    onEndDateSelected: (kotlinx.datetime.LocalDate) -> Unit,
    onFlexibleMonthChanged: (String) -> Unit,
    onFlexibleDurationChanged: (String) -> Unit,
    onCreateTripClicked: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create New Trip") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomActionBar(
                isValid = uiState.isValid,
                isLoading = uiState.isLoading,
                onCancelClick = onBackPressed,
                onCreateClick = onCreateTripClicked
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TripDetailsCard(
                tripName = uiState.tripName,
                originCity = uiState.originCity,
                tripNameError = uiState.tripNameError,
                originCityError = uiState.originCityError,
                onTripNameChanged = onTripNameChanged,
                onOriginCityChanged = onOriginCityChanged
            )

            TravelDatesCard(
                selectedDateType = uiState.selectedDateType,
                startDate = uiState.startDate,
                endDate = uiState.endDate,
                flexibleMonth = uiState.flexibleMonth,
                flexibleDuration = uiState.flexibleDuration,
                dateError = uiState.dateError,
                onDateTypeSelected = onDateTypeSelected,
                onStartDateSelected = onStartDateSelected,
                onEndDateSelected = onEndDateSelected,
                onFlexibleMonthChanged = onFlexibleMonthChanged,
                onFlexibleDurationChanged = onFlexibleDurationChanged
            )

            ComingSoonCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateTripScreenPreview() {
    MaterialTheme {
        CreateTripScreenContent(
            uiState = CreateTripUiState(
                tripName = "Spain Adventure",
                originCity = "New York",
                selectedDateType = DateType.FIXED
            ),
            snackbarHostState = remember { SnackbarHostState() },
            onTripNameChanged = {},
            onOriginCityChanged = {},
            onDateTypeSelected = {},
            onStartDateSelected = {},
            onEndDateSelected = {},
            onFlexibleMonthChanged = {},
            onFlexibleDurationChanged = {},
            onCreateTripClicked = {},
            onBackPressed = {}
        )
    }
}
