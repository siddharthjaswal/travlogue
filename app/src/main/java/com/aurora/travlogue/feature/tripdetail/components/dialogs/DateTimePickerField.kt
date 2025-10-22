package com.aurora.travlogue.feature.tripdetail.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * DateTime picker field with timezone support
 * Displays date, time, and timezone, opening pickers when clicked
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerField(
    label: String,
    selectedDateTime: ZonedDateTime?,
    onDateTimeSelected: (ZonedDateTime) -> Unit,
    modifier: Modifier = Modifier,
    showTimezone: Boolean = true
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showTimezonePicker by remember { mutableStateOf(false) }

    // Current values
    var currentDate by remember(selectedDateTime) {
        mutableStateOf(selectedDateTime?.toLocalDate() ?: LocalDate.now())
    }
    var currentTime by remember(selectedDateTime) {
        mutableStateOf(selectedDateTime?.toLocalTime() ?: LocalTime.now())
    }
    var currentZone by remember(selectedDateTime) {
        mutableStateOf(selectedDateTime?.zone ?: ZoneId.systemDefault())
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Date and Time Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Date Button
            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Time Button
            OutlinedButton(
                onClick = { showTimePicker = true },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a")),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Timezone Row
        if (showTimezone) {
            OutlinedButton(
                onClick = { showTimezonePicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "🌍 ${formatTimezone(currentZone)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = currentDate.toEpochDay() * 86400000
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            currentDate = LocalDate.ofEpochDay(millis / 86400000)
                            // Update the combined datetime
                            val newDateTime = ZonedDateTime.of(currentDate, currentTime, currentZone)
                            onDateTimeSelected(newDateTime)
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

    // Time Picker Dialog
    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.hour,
            initialMinute = currentTime.minute,
            is24Hour = false
        )

        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        currentTime = LocalTime.of(
                            timePickerState.hour,
                            timePickerState.minute
                        )
                        // Update the combined datetime
                        val newDateTime = ZonedDateTime.of(currentDate, currentTime, currentZone)
                        onDateTimeSelected(newDateTime)
                        showTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
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

    // Timezone Picker Dialog
    if (showTimezonePicker) {
        TimezoneSelectorDialog(
            currentZone = currentZone,
            onZoneSelected = { newZone ->
                currentZone = newZone
                // Update the combined datetime
                val newDateTime = ZonedDateTime.of(currentDate, currentTime, currentZone)
                onDateTimeSelected(newDateTime)
                showTimezonePicker = false
            },
            onDismiss = { showTimezonePicker = false }
        )
    }
}

@Preview(name = "Date Time Picker Field", showBackground = true, widthDp = 360)
@Composable
private fun DateTimePickerFieldPreview() {
    MaterialTheme {
        DateTimePickerField(
            label = "Departure",
            selectedDateTime = ZonedDateTime.now(),
            onDateTimeSelected = {},
            showTimezone = true
        )
    }
}

/**
 * Format timezone for display
 */
private fun formatTimezone(zoneId: ZoneId): String {
    val now = ZonedDateTime.now(zoneId)
    val offset = now.format(DateTimeFormatter.ofPattern("XXX"))
    val displayName = zoneId.id
    return "$displayName ($offset)"
}
