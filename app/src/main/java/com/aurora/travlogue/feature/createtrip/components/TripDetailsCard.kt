package com.aurora.travlogue.feature.createtrip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Card component for Trip Details section
 * Contains trip name and origin city input fields
 */
@Composable
fun TripDetailsCard(
    tripName: String,
    originCity: String,
    tripNameError: String?,
    originCityError: String?,
    onTripNameChanged: (String) -> Unit,
    onOriginCityChanged: (String) -> Unit,
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
                text = "Trip Details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = tripName,
                onValueChange = onTripNameChanged,
                label = { Text("Trip Name *") },
                placeholder = { Text("e.g., Spain Adventure 2025") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = tripNameError != null,
                supportingText = tripNameError?.let { { Text(it) } }
            )

            OutlinedTextField(
                value = originCity,
                onValueChange = onOriginCityChanged,
                label = { Text("Origin City *") },
                placeholder = { Text("e.g., New York") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = originCityError != null,
                supportingText = originCityError?.let { { Text(it) } }
            )
        }
    }
}
