package com.aurora.travlogue.desktop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripUiEvent
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.LocalDate
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    onNavigateBack: () -> Unit,
    viewModel: CreateTripViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                is CreateTripUiEvent.TripCreatedSuccess -> {
                    snackbarHostState.showSnackbar("Trip created successfully!")
                    onNavigateBack()
                }
                is CreateTripUiEvent.NavigateBack -> onNavigateBack()
                is CreateTripUiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Create Trip") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackPressed() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Trip Name
            OutlinedTextField(
                value = uiState.tripName,
                onValueChange = { viewModel.onTripNameChanged(it) },
                label = { Text("Trip Name") },
                placeholder = { Text("e.g., Summer Europe Adventure") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.tripNameError != null,
                supportingText = uiState.tripNameError?.let { { Text(it) } }
            )

            // Origin City
            OutlinedTextField(
                value = uiState.originCity,
                onValueChange = { viewModel.onOriginCityChanged(it) },
                label = { Text("Origin City") },
                placeholder = { Text("e.g., San Francisco") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.originCityError != null,
                supportingText = uiState.originCityError?.let { { Text(it) } }
            )

            // Date Type
            Text(
                text = "Date Type",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                DateType.entries.forEach { dateType ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = uiState.selectedDateType == dateType,
                            onClick = { viewModel.onDateTypeSelected(dateType) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = dateType.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = when (dateType) {
                                    DateType.FIXED -> "I have specific dates"
                                    DateType.FLEXIBLE -> "I have flexible dates"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }

            // Date Fields based on type
            when (uiState.selectedDateType) {
                DateType.FIXED -> {
                    Text(
                        text = "Trip Dates",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    // Start Date Input
                    var startDateText by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = startDateText,
                        onValueChange = { text ->
                            startDateText = text
                            // Try to parse as LocalDate (YYYY-MM-DD format)
                            try {
                                val date = LocalDate.parse(text)
                                viewModel.onStartDateSelected(date)
                            } catch (e: Exception) {
                                // Invalid format, ignore
                            }
                        },
                        label = { Text("Start Date") },
                        placeholder = { Text("YYYY-MM-DD") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.dateError != null
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // End Date Input
                    var endDateText by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = endDateText,
                        onValueChange = { text ->
                            endDateText = text
                            // Try to parse as LocalDate (YYYY-MM-DD format)
                            try {
                                val date = LocalDate.parse(text)
                                viewModel.onEndDateSelected(date)
                            } catch (e: Exception) {
                                // Invalid format, ignore
                            }
                        },
                        label = { Text("End Date") },
                        placeholder = { Text("YYYY-MM-DD") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.dateError != null
                    )

                    uiState.dateError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                DateType.FLEXIBLE -> {
                    Text(
                        text = "Flexible Dates",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    OutlinedTextField(
                        value = uiState.flexibleMonth,
                        onValueChange = { viewModel.onFlexibleMonthChanged(it) },
                        label = { Text("Month") },
                        placeholder = { Text("e.g., July 2025") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.dateError != null
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.flexibleDuration,
                        onValueChange = { viewModel.onFlexibleDurationChanged(it) },
                        label = { Text("Duration (days)") },
                        placeholder = { Text("e.g., 7") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    uiState.dateError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Create Button
            Button(
                onClick = { viewModel.onCreateTripClicked() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text("Create Trip")
            }
        }
    }
}
