package com.aurora.travlogue.feature.tripdetail.components.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.ActivityType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
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
        type: ActivityType,
        startTime: String?,
        endTime: String?
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

    // Optional specific time fields
    var useSpecificTime by remember { mutableStateOf(false) }
    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var endTime by remember { mutableStateOf<LocalTime?>(null) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    // Get selected location for validation
    val selectedLocation = remember(selectedLocationId, locations) {
        locations.find { it.id == selectedLocationId }
    }

    // Time window validation
    val timeValidation = remember(selectedLocation, selectedDate, startTime, endTime) {
        validateActivityTimeWindow(selectedLocation, selectedDate, startTime, endTime)
    }

    // Validation
    val isTitleValid = title.isNotBlank()
    val isLocationValid = selectedLocationId.isNotBlank()
    val isFormValid = isTitleValid && isLocationValid && timeValidation.isValid

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
                            selectedType,
                            startTime?.format(DateTimeFormatter.ISO_LOCAL_TIME),
                            endTime?.format(DateTimeFormatter.ISO_LOCAL_TIME)
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
                isError = !timeValidation.isValid && selectedDate.isNotBlank(),
                supportingText = {
                    when {
                        !timeValidation.isValid && selectedDate.isNotBlank() -> {
                            Text(
                                text = timeValidation.errorMessage,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        selectedLocation?.arrivalDateTime != null && selectedLocation.departureDateTime != null -> {
                            val arrivalTime = try {
                                ZonedDateTime.parse(selectedLocation.arrivalDateTime)
                            } catch (e: Exception) { null }
                            val departureTime = try {
                                ZonedDateTime.parse(selectedLocation.departureDateTime)
                            } catch (e: Exception) { null }

                            if (arrivalTime != null && departureTime != null) {
                                Text(
                                    text = "Valid: ${formatDateTime(selectedLocation.arrivalDateTime)} - ${formatDateTime(selectedLocation.departureDateTime)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                },
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
                                // Auto-set time based on time slot if specific time is enabled
                                if (useSpecificTime) {
                                    startTime = when (timeSlot) {
                                        TimeSlot.MORNING -> LocalTime.of(9, 0)
                                        TimeSlot.AFTERNOON -> LocalTime.of(13, 0)
                                        TimeSlot.EVENING -> LocalTime.of(17, 0)
                                        TimeSlot.FULL_DAY -> LocalTime.of(9, 0)
                                    }
                                }
                                showTimeSlotDropdown = false
                            }
                        )
                    }
                }
            }

            // Optional Specific Time Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Add Specific Time (Optional)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = useSpecificTime,
                    onCheckedChange = { checked ->
                        useSpecificTime = checked
                        if (!checked) {
                            startTime = null
                            endTime = null
                        }
                    }
                )
            }

            // Specific Time Pickers
            if (useSpecificTime) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Start Time
                    OutlinedButton(
                        onClick = { showStartTimePicker = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = startTime?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "Start Time"
                        )
                    }

                    // End Time
                    OutlinedButton(
                        onClick = { showEndTimePicker = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = endTime?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "End Time"
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
                LocalDate.parse(selectedDate).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
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
                            val instant = Instant.ofEpochMilli(millis)
                            val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
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

    // Start Time Picker Dialog
    if (showStartTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = startTime?.hour ?: 9,
            initialMinute = startTime?.minute ?: 0,
            is24Hour = false
        )

        AlertDialog(
            onDismissRequest = { showStartTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        startTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                        showStartTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showStartTimePicker = false }) {
                    Text("Cancel")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimePicker(state = timePickerState)
                }
            }
        )
    }

    // End Time Picker Dialog
    if (showEndTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = endTime?.hour ?: (startTime?.plusHours(1)?.hour ?: 10),
            initialMinute = endTime?.minute ?: (startTime?.minute ?: 0),
            is24Hour = false
        )

        AlertDialog(
            onDismissRequest = { showEndTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        endTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                        showEndTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEndTimePicker = false }) {
                    Text("Cancel")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimePicker(state = timePickerState)
                }
            }
        )
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

@Preview(name = "Add Activity Dialog", showBackground = true, widthDp = 360)
@Composable
private fun AddActivityDialogPreview() {
    val locations = PreviewData.sampleLocations
    MaterialTheme {
        AddActivityDialog(
            onDismiss = {},
            onSave = { _, _, _, _, _, _, _, _ -> },
            locations = locations,
            preselectedLocationId = locations.first().id,
            preselectedDate = locations.first().date
        )
    }
}

@Preview(name = "Bottom Action Bar", showBackground = true, widthDp = 360)
@Composable
private fun BottomActionBarPreview() {
    MaterialTheme {
        BottomActionBar(
            onCancelClick = {},
            onSaveClick = {},
            isSaveEnabled = true
        )
    }
}
