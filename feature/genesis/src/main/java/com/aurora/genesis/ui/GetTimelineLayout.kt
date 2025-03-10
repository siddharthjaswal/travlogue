package com.aurora.genesis.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GetTimelineLayout() {

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Let's get your dates sorted",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        // Start Date Picker
        Button(onClick = { showStartDatePicker = true }) {
            Text(text = if (startDate.isEmpty()) "Select Start Date" else "Start Date: $startDate")
        }

        // End Date Picker
        Button(onClick = { showEndDatePicker = true }) {
            Text(text = if (endDate.isEmpty()) "Select End Date" else "End Date: $endDate")
        }

        // Start Date Picker Dialog
        if (showStartDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showStartDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState()
                DatePicker(state = datePickerState)
                // Handle the selected date
                LaunchedEffect(datePickerState.selectedDateMillis) {
                    datePickerState.selectedDateMillis?.let { millis ->
                        startDate = millis.toFormattedDateString()
                    }
                }
            }
        }

        // End Date Picker Dialog
        if (showEndDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showEndDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState()
                DatePicker(state = datePickerState)
                // Handle the selected date
                LaunchedEffect(datePickerState.selectedDateMillis) {
                    datePickerState.selectedDateMillis?.let { millis ->
                        endDate = millis.toFormattedDateString()
                    }
                }
            }
        }
    }
}

// Helper function to format the date
private fun Long.toFormattedDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date(this))
}