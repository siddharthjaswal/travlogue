package com.aurora.travlogue.feature.tripdetail.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLocationDialog(
    location: Location,
    onDismiss: () -> Unit,
    onSave: (Location) -> Unit,
    onDelete: () -> Unit,
    tripStartDate: String?,
    tripEndDate: String?
) {
    var name by remember { mutableStateOf(location.name) }
    var country by remember { mutableStateOf(location.country) }
    var selectedDate by remember { mutableStateOf(location.date ?: "") }
    var selectedTimezone by remember {
        mutableStateOf<ZoneId?>(
            location.timezone?.let { ZoneId.of(it) }
        )
    }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimezonePicker by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    // Validation
    val isNameValid = name.isNotBlank()
    val isCountryValid = country.isNotBlank()
    val isFormValid = isNameValid && isCountryValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Location",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            BottomActionBar(
                onCancelClick = onDismiss,
                onSaveClick = {
                    if (isFormValid) {
                        onSave(
                            location.copy(
                                name = name,
                                country = country,
                                date = selectedDate.takeIf { it.isNotBlank() },
                                timezone = selectedTimezone?.id
                            )
                        )
                    }
                },
                onDeleteClick = { showDeleteConfirmation = true },
                isSaveEnabled = isFormValid
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Location Name Field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Location Name *") },
                placeholder = { Text("e.g., Barcelona") },
                isError = !isNameValid && name.isNotEmpty(),
                supportingText = {
                    if (!isNameValid && name.isNotEmpty()) {
                        Text("Location name is required")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Country Field
            OutlinedTextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("Country *") },
                placeholder = { Text("e.g., Spain") },
                isError = !isCountryValid && country.isNotEmpty(),
                supportingText = {
                    if (!isCountryValid && country.isNotEmpty()) {
                        Text("Country is required")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Date Field (Optional)
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                readOnly = true,
                label = { Text("Date (Optional)") },
                placeholder = { Text("When will you be here?") },
                trailingIcon = {
                    TextButton(onClick = { showDatePicker = true }) {
                        Text("Pick")
                    }
                },
                supportingText = {
                    if (tripStartDate != null && tripEndDate != null) {
                        Text(
                            "Trip dates: ${formatDate(tripStartDate)} - ${formatDate(tripEndDate)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Timezone Field (Optional)
            OutlinedTextField(
                value = selectedTimezone?.id?.replace("_", " ") ?: "",
                onValueChange = { },
                readOnly = true,
                label = { Text("Timezone (Optional)") },
                placeholder = { Text("Select timezone") },
                trailingIcon = {
                    TextButton(onClick = { showTimezonePicker = true }) {
                        Text("Pick")
                    }
                },
                supportingText = {
                    Text(
                        "Used for accurate activity scheduling",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Info Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Location Order: #${location.order}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "This is destination ${location.order} in your trip itinerary",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = if (selectedDate.isNotBlank()) {
                LocalDate.parse(selectedDate).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
            } else if (tripStartDate != null) {
                LocalDate.parse(tripStartDate).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
            } else {
                System.currentTimeMillis()
            }
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val instant = java.time.Instant.ofEpochMilli(millis)
                            val localDate = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                            selectedDate = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
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

    // Timezone Picker Dialog
    if (showTimezonePicker) {
        TimezoneSelectorDialog(
            currentZone = selectedTimezone ?: ZoneId.systemDefault(),
            onZoneSelected = { zone ->
                selectedTimezone = zone
                showTimezonePicker = false
            },
            onDismiss = { showTimezonePicker = false }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Location?") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Are you sure you want to delete \"$name\"?")
                    Text(
                        "⚠️ This will also delete all activities associated with this location.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        onDelete()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun BottomActionBar(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    isSaveEnabled: Boolean
) {
    Surface(
        tonalElevation = 3.dp,
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = onSaveClick,
                    enabled = isSaveEnabled,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save Changes")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onDeleteClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete Location")
            }
        }
    }
}

private fun formatDate(isoDate: String): String {
    return try {
        val date = LocalDate.parse(isoDate)
        date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))
    } catch (e: Exception) {
        isoDate
    }
}
