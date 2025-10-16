package com.aurora.travlogue.feature.createtrip.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aurora.travlogue.core.common.DateTimeUtils.toIsoString
import com.aurora.travlogue.core.data.local.entities.DateType
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    viewModel: CreateTripViewModel = hiltViewModel(),
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
    onStartDateSelected: (LocalDate) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
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
            // Bottom action bar with Create button
            Surface(
                tonalElevation = 3.dp,
                shadowElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBackPressed,
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.isLoading
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = onCreateTripClicked,
                        modifier = Modifier.weight(1f),
                        enabled = uiState.isValid && !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Create Trip")
                        }
                    }
                }
            }
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
            // Trip Name Section
            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Trip Details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = uiState.tripName,
                        onValueChange = onTripNameChanged,
                        label = { Text("Trip Name *") },
                        placeholder = { Text("e.g., Spain Adventure 2025") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.tripNameError != null,
                        supportingText = uiState.tripNameError?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = uiState.originCity,
                        onValueChange = onOriginCityChanged,
                        label = { Text("Origin City *") },
                        placeholder = { Text("e.g., New York") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.originCityError != null,
                        supportingText = uiState.originCityError?.let { { Text(it) } }
                    )
                }
            }

            // Date Type Section
            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Travel Dates",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Date Type",
                        style = MaterialTheme.typography.labelLarge
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = uiState.selectedDateType == DateType.FIXED,
                            onClick = { onDateTypeSelected(DateType.FIXED) },
                            label = { Text("Fixed Dates") },
                            modifier = Modifier.weight(1f)
                        )
                        FilterChip(
                            selected = uiState.selectedDateType == DateType.FLEXIBLE,
                            onClick = { onDateTypeSelected(DateType.FLEXIBLE) },
                            label = { Text("Flexible") },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Fixed dates inputs
                    if (uiState.selectedDateType == DateType.FIXED) {
                        DatePickerField(
                            label = "Start Date",
                            selectedDate = uiState.startDate,
                            onDateSelected = onStartDateSelected
                        )

                        DatePickerField(
                            label = "End Date",
                            selectedDate = uiState.endDate,
                            onDateSelected = onEndDateSelected,
                            minDate = uiState.startDate
                        )
                    }

                    // Flexible dates inputs
                    if (uiState.selectedDateType == DateType.FLEXIBLE) {
                        OutlinedTextField(
                            value = uiState.flexibleMonth,
                            onValueChange = onFlexibleMonthChanged,
                            label = { Text("Month *") },
                            placeholder = { Text("e.g., November 2025") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = uiState.flexibleDuration,
                            onValueChange = onFlexibleDurationChanged,
                            label = { Text("Duration (days) - Optional") },
                            placeholder = { Text("e.g., 7") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    // Date error message
                    if (uiState.dateError != null) {
                        Text(
                            text = uiState.dateError,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            // Future enhancement placeholder
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Coming Soon",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "• Add travelers\n• Set budget\n• Upload cover photo\n• AI-powered suggestions",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerField(
    label: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    minDate: LocalDate? = null
) {
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { showDatePicker = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = selectedDate?.toIsoString() ?: "Select $label",
            modifier = Modifier.weight(1f)
        )
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate?.toEpochDay()?.times(86400000)
                ?: System.currentTimeMillis()
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = LocalDate.ofEpochDay(millis / 86400000)
                            if (minDate == null || !date.isBefore(minDate)) {
                                onDateSelected(date)
                            }
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
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
