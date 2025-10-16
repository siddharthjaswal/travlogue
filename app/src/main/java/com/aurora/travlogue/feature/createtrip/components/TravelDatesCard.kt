package com.aurora.travlogue.feature.createtrip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.DateType
import java.time.LocalDate

/**
 * Card component for Travel Dates section
 * Contains date type selection and corresponding input fields
 */
@Composable
fun TravelDatesCard(
    selectedDateType: DateType,
    startDate: LocalDate?,
    endDate: LocalDate?,
    flexibleMonth: String,
    flexibleDuration: String,
    dateError: String?,
    onDateTypeSelected: (DateType) -> Unit,
    onStartDateSelected: (LocalDate) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
    onFlexibleMonthChanged: (String) -> Unit,
    onFlexibleDurationChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
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

            // Date type selection chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedDateType == DateType.FIXED,
                    onClick = { onDateTypeSelected(DateType.FIXED) },
                    label = { Text("Fixed Dates") },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedDateType == DateType.FLEXIBLE,
                    onClick = { onDateTypeSelected(DateType.FLEXIBLE) },
                    label = { Text("Flexible") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Fixed dates inputs
            if (selectedDateType == DateType.FIXED) {
                DatePickerField(
                    label = "Start Date",
                    selectedDate = startDate,
                    onDateSelected = onStartDateSelected
                )

                DatePickerField(
                    label = "End Date",
                    selectedDate = endDate,
                    onDateSelected = onEndDateSelected,
                    minDate = startDate
                )
            }

            // Flexible dates inputs
            if (selectedDateType == DateType.FLEXIBLE) {
                OutlinedTextField(
                    value = flexibleMonth,
                    onValueChange = onFlexibleMonthChanged,
                    label = { Text("Month *") },
                    placeholder = { Text("e.g., November 2025") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = flexibleDuration,
                    onValueChange = onFlexibleDurationChanged,
                    label = { Text("Duration (days) - Optional") },
                    placeholder = { Text("e.g., 7") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            // Date error message
            if (dateError != null) {
                Text(
                    text = dateError,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
