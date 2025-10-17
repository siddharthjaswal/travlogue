package com.aurora.travlogue.feature.tripdetail.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.data.local.entities.BookingType
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookingDialog(
    onDismiss: () -> Unit,
    onSave: (
        type: BookingType,
        confirmationNumber: String?,
        provider: String,
        startDateTime: String,
        endDateTime: String?,
        timezone: String,
        fromLocation: String?,
        toLocation: String?,
        price: Double?,
        currency: String?,
        notes: String?
    ) -> Unit
) {
    var selectedType by remember { mutableStateOf(BookingType.FLIGHT) }
    var confirmationNumber by remember { mutableStateOf("") }
    var provider by remember { mutableStateOf("") }
    var fromLocation by remember { mutableStateOf("") }
    var toLocation by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("USD") }
    var notes by remember { mutableStateOf("") }
    var showTypeDropdown by remember { mutableStateOf(false) }

    // Simplified date/time for now - just use current datetime with system timezone
    val currentDateTime = remember {
        LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
    val systemTimezone = remember { ZoneId.systemDefault().id }

    // Validation
    val isProviderValid = provider.isNotBlank()
    val isFormValid = isProviderValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Booking",
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
                        val priceValue = price.toDoubleOrNull()
                        onSave(
                            selectedType,
                            confirmationNumber.takeIf { it.isNotBlank() },
                            provider,
                            currentDateTime,  // TODO: Add proper datetime picker
                            null,  // TODO: Add end datetime picker
                            systemTimezone,
                            fromLocation.takeIf { it.isNotBlank() },
                            toLocation.takeIf { it.isNotBlank() },
                            priceValue,
                            currency.takeIf { priceValue != null && it.isNotBlank() },
                            notes.takeIf { it.isNotBlank() }
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
            // Booking Type Dropdown
            ExposedDropdownMenuBox(
                expanded = showTypeDropdown,
                onExpandedChange = { showTypeDropdown = it }
            ) {
                OutlinedTextField(
                    value = getBookingTypeDisplay(selectedType),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Booking Type") },
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
                    BookingType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(getBookingTypeDisplay(type)) },
                            onClick = {
                                selectedType = type
                                showTypeDropdown = false
                            }
                        )
                    }
                }
            }

            // Provider Field
            OutlinedTextField(
                value = provider,
                onValueChange = { provider = it },
                label = { Text("Provider/Company *") },
                placeholder = { Text("e.g., United Airlines, Marriott") },
                isError = !isProviderValid && provider.isNotEmpty(),
                supportingText = {
                    if (!isProviderValid && provider.isNotEmpty()) {
                        Text("Provider is required")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Confirmation Number Field
            OutlinedTextField(
                value = confirmationNumber,
                onValueChange = { confirmationNumber = it },
                label = { Text("Confirmation Number") },
                placeholder = { Text("Optional") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // From/To Locations (for flights, trains, etc.)
            if (selectedType in listOf(BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS)) {
                OutlinedTextField(
                    value = fromLocation,
                    onValueChange = { fromLocation = it },
                    label = { Text("From (Optional)") },
                    placeholder = { Text("Departure location") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = toLocation,
                    onValueChange = { toLocation = it },
                    label = { Text("To (Optional)") },
                    placeholder = { Text("Arrival location") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Price and Currency
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = price,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d{0,2}\$"))) {
                            price = newValue
                        }
                    },
                    label = { Text("Price (Optional)") },
                    placeholder = { Text("0.00") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.weight(2f)
                )

                OutlinedTextField(
                    value = currency,
                    onValueChange = { currency = it },
                    label = { Text("Currency") },
                    placeholder = { Text("USD") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
            }

            // Notes Field
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (Optional)") },
                placeholder = { Text("Add additional details...") },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier.fillMaxWidth()
            )

            // Info Card - Datetime Note
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
                        text = "📅 Date & Time",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Currently set to now (${formatDateTime(currentDateTime)}). Full datetime picker coming soon!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
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

private fun getBookingTypeDisplay(type: BookingType): String {
    return when (type) {
        BookingType.FLIGHT -> "✈️ Flight"
        BookingType.HOTEL -> "🏨 Hotel"
        BookingType.TRAIN -> "🚂 Train"
        BookingType.BUS -> "🚌 Bus"
        BookingType.TICKET -> "🎫 Ticket"
        BookingType.OTHER -> "📝 Other"
    }
}

private fun formatDateTime(isoDateTime: String): String {
    return try {
        val dateTime = LocalDateTime.parse(isoDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        dateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a"))
    } catch (e: Exception) {
        isoDateTime
    }
}
