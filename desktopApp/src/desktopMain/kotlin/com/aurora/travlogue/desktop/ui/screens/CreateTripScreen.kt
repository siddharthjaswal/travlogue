package com.aurora.travlogue.desktop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripUiEvent
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripViewModel
import kotlinx.coroutines.flow.collectLatest
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
        viewModel.events.collectLatest { event ->
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
                            selected = uiState.dateType == dateType,
                            onClick = { viewModel.onDateTypeChanged(dateType) }
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
                                    DateType.PLANNING -> "I'm just planning"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }

            // Date Fields (only for FIXED and FLEXIBLE)
            if (uiState.dateType != DateType.PLANNING) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.startDate,
                        onValueChange = { viewModel.onStartDateChanged(it) },
                        label = { Text("Start Date") },
                        placeholder = { Text("YYYY-MM-DD") },
                        leadingIcon = { Icon(Icons.Default.Event, contentDescription = null) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        isError = uiState.startDateError != null,
                        supportingText = uiState.startDateError?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = uiState.endDate,
                        onValueChange = { viewModel.onEndDateChanged(it) },
                        label = { Text("End Date") },
                        placeholder = { Text("YYYY-MM-DD") },
                        leadingIcon = { Icon(Icons.Default.Event, contentDescription = null) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        isError = uiState.endDateError != null,
                        supportingText = uiState.endDateError?.let { { Text(it) } }
                    )
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
