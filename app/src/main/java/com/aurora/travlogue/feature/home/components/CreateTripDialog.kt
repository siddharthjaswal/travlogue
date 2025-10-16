package com.aurora.travlogue.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.DateTimeUtils.toIsoString
import com.aurora.travlogue.core.data.local.entities.DateType
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripDialog(
    onDismiss: () -> Unit,
    onCreateTrip: (
        name: String,
        originCity: String,
        dateType: DateType,
        startDate: String?,
        endDate: String?,
        flexibleMonth: String?,
        flexibleDuration: Int?
    ) -> Unit
) {
    var tripName by remember { mutableStateOf("") }
    var originCity by remember { mutableStateOf("") }
    var selectedDateType by remember { mutableStateOf(DateType.FIXED) }

    // Fixed date fields
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    // Flexible date fields
    var flexibleMonth by remember { mutableStateOf("") }
    var flexibleDuration by remember { mutableStateOf("") }

    // Validation
    val isValid = tripName.isNotBlank() &&
            originCity.isNotBlank() &&
            when (selectedDateType) {
                DateType.FIXED -> startDate != null && endDate != null
                DateType.FLEXIBLE -> flexibleMonth.isNotBlank()
            }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Create New Trip",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Trip Name
                OutlinedTextField(
                    value = tripName,
                    onValueChange = { tripName = it },
                    label = { Text("Trip Name") },
                    placeholder = { Text("e.g., Spain Adventure 2025") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Origin City
                OutlinedTextField(
                    value = originCity,
                    onValueChange = { originCity = it },
                    label = { Text("Origin City") },
                    placeholder = { Text("e.g., New York") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Date Type Selector
                Text(
                    text = "Date Type",
                    style = MaterialTheme.typography.labelLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedDateType == DateType.FIXED,
                        onClick = { selectedDateType = DateType.FIXED },
                        label = { Text("Fixed Dates") },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = selectedDateType == DateType.FLEXIBLE,
                        onClick = { selectedDateType = DateType.FLEXIBLE },
                        label = { Text("Flexible") },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Fixed dates inputs
                if (selectedDateType == DateType.FIXED) {
                    DatePickerField(
                        label = "Start Date",
                        selectedDate = startDate,
                        onDateSelected = { startDate = it }
                    )

                    DatePickerField(
                        label = "End Date",
                        selectedDate = endDate,
                        onDateSelected = { endDate = it },
                        minDate = startDate
                    )
                }

                // Flexible dates inputs
                if (selectedDateType == DateType.FLEXIBLE) {
                    OutlinedTextField(
                        value = flexibleMonth,
                        onValueChange = { flexibleMonth = it },
                        label = { Text("Month") },
                        placeholder = { Text("e.g., November 2025") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = flexibleDuration,
                        onValueChange = { flexibleDuration = it.filter { char -> char.isDigit() } },
                        label = { Text("Duration (days) - Optional") },
                        placeholder = { Text("e.g., 7") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onCreateTrip(
                        tripName.trim(),
                        originCity.trim(),
                        selectedDateType,
                        startDate?.toIsoString(),
                        endDate?.toIsoString(),
                        if (selectedDateType == DateType.FLEXIBLE && flexibleMonth.isNotBlank()) flexibleMonth.trim() else null,
                        if (selectedDateType == DateType.FLEXIBLE && flexibleDuration.isNotBlank()) flexibleDuration.toIntOrNull() else null
                    )
                },
                enabled = isValid
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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
            text = selectedDate?.let { it.toIsoString() } ?: "Select $label",
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
