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
import com.aurora.travlogue.core.data.local.entities.ActivityType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityDialog(
    onDismiss: () -> Unit,
    onSave: (
        locationId: String,
        title: String,
        description: String?,
        date: String?,
        timeSlot: TimeSlot?,
        type: ActivityType
    ) -> Unit,
    locations: List<Location>,
    preselectedLocationId: String? = null,
    preselectedDate: String? = null
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedLocationId by remember { mutableStateOf(preselectedLocationId ?: locations.firstOrNull()?.id ?: "") }
    var selectedDate by remember { mutableStateOf(preselectedDate ?: "") }
    var selectedTimeSlot by remember { mutableStateOf<TimeSlot?>(TimeSlot.MORNING) }
    var selectedType by remember { mutableStateOf(ActivityType.ATTRACTION) }
    var showLocationDropdown by remember { mutableStateOf(false) }
    var showTimeSlotDropdown by remember { mutableStateOf(false) }
    var showTypeDropdown by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    // Validation
    val isTitleValid = title.isNotBlank()
    val isLocationValid = selectedLocationId.isNotBlank()
    val isFormValid = isTitleValid && isLocationValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Activity",
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
                            selectedLocationId,
                            title,
                            description.takeIf { it.isNotBlank() },
                            selectedDate.takeIf { it.isNotBlank() },
                            selectedTimeSlot,
                            selectedType
                        )
                    }
                },
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
            // Title Field
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Activity Title *") },
                placeholder = { Text("e.g., Visit Sagrada Familia") },
                isError = !isTitleValid && title.isNotEmpty(),
                supportingText = {
                    if (!isTitleValid && title.isNotEmpty()) {
                        Text("Title is required")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Location Dropdown
            ExposedDropdownMenuBox(
                expanded = showLocationDropdown,
                onExpandedChange = { showLocationDropdown = it }
            ) {
                OutlinedTextField(
                    value = locations.find { it.id == selectedLocationId }?.name ?: "Select Location",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Location *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showLocationDropdown) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = showLocationDropdown,
                    onDismissRequest = { showLocationDropdown = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text("${location.name}, ${location.country}") },
                            onClick = {
                                selectedLocationId = location.id
                                selectedDate = location.date ?: ""
                                showLocationDropdown = false
                            }
                        )
                    }
                }
            }

            // Date Field (Optional - can be inferred from location)
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                readOnly = true,
                label = { Text("Date (Optional)") },
                placeholder = { Text("Select date") },
                trailingIcon = {
                    TextButton(onClick = { showDatePicker = true }) {
                        Text("Pick")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Time Slot Dropdown
            ExposedDropdownMenuBox(
                expanded = showTimeSlotDropdown,
                onExpandedChange = { showTimeSlotDropdown = it }
            ) {
                OutlinedTextField(
                    value = selectedTimeSlot?.name?.replace("_", " ")?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Select Time Slot",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Time Slot") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTimeSlotDropdown) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = showTimeSlotDropdown,
                    onDismissRequest = { showTimeSlotDropdown = false }
                ) {
                    TimeSlot.entries.forEach { timeSlot ->
                        DropdownMenuItem(
                            text = { Text(timeSlot.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }) },
                            onClick = {
                                selectedTimeSlot = timeSlot
                                showTimeSlotDropdown = false
                            }
                        )
                    }
                }
            }

            // Activity Type Dropdown
            ExposedDropdownMenuBox(
                expanded = showTypeDropdown,
                onExpandedChange = { showTypeDropdown = it }
            ) {
                OutlinedTextField(
                    value = getActivityTypeDisplay(selectedType),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Activity Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTypeDropdown) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = showTypeDropdown,
                    onDismissRequest = { showTypeDropdown = false }
                ) {
                    ActivityType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(getActivityTypeDisplay(type)) },
                            onClick = {
                                selectedType = type
                                showTypeDropdown = false
                            }
                        )
                    }
                }
            }

            // Description Field
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description (Optional)") },
                placeholder = { Text("Add details about this activity...") },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = if (selectedDate.isNotBlank()) {
                LocalDate.parse(selectedDate).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
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
}

@Composable
private fun BottomActionBar(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    isSaveEnabled: Boolean
) {
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
                Text("Save")
            }
        }
    }
}

private fun getActivityTypeDisplay(type: ActivityType): String {
    return when (type) {
        ActivityType.ATTRACTION -> "🎨 Attraction"
        ActivityType.FOOD -> "🍴 Food & Dining"
        ActivityType.BOOKING -> "🎫 Booking"
        ActivityType.TRANSIT -> "🚗 Transit"
        ActivityType.OTHER -> "📝 Other"
    }
}
