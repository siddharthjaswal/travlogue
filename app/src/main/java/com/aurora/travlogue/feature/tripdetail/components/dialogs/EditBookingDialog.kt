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
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookingDialog(
    booking: Booking,
    onDismiss: () -> Unit,
    onSave: (Booking) -> Unit,
    onDelete: () -> Unit
) {
    var selectedType by remember { mutableStateOf(booking.type) }
    var confirmationNumber by remember { mutableStateOf(booking.confirmationNumber ?: "") }
    var provider by remember { mutableStateOf(booking.provider) }
    var fromLocation by remember { mutableStateOf(booking.fromLocation ?: "") }
    var toLocation by remember { mutableStateOf(booking.toLocation ?: "") }
    var price by remember { mutableStateOf(booking.price?.toString() ?: "") }
    var currency by remember { mutableStateOf(booking.currency ?: "USD") }
    var notes by remember { mutableStateOf(booking.notes ?: "") }
    var showTypeDropdown by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    // DateTime state with timezone support
    var startDateTime by remember {
        mutableStateOf(
            parseDateTime(booking.startDateTime, booking.timezone)
        )
    }
    var endDateTime by remember {
        mutableStateOf(
            booking.endDateTime?.let { parseDateTime(it, booking.timezone) }
        )
    }
    var useEndDateTime by remember { mutableStateOf(booking.endDateTime != null) }

    // Validation
    val isProviderValid = provider.isNotBlank()
    val isFormValid = isProviderValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Booking",
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
                            booking.copy(
                                type = selectedType,
                                confirmationNumber = confirmationNumber.takeIf { it.isNotBlank() },
                                provider = provider,
                                startDateTime = startDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                                endDateTime = endDateTime?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                                timezone = startDateTime.zone.id,
                                fromLocation = fromLocation.takeIf { it.isNotBlank() },
                                toLocation = toLocation.takeIf { it.isNotBlank() },
                                price = priceValue,
                                currency = currency.takeIf { priceValue != null && it.isNotBlank() },
                                notes = notes.takeIf { it.isNotBlank() }
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

            // Divider
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Start DateTime Picker
            DateTimePickerField(
                label = "Start Date & Time *",
                selectedDateTime = startDateTime,
                onDateTimeSelected = { newDateTime ->
                    startDateTime = newDateTime
                    // Update endDateTime zone if it exists
                    endDateTime?.let { endDt ->
                        endDateTime = endDt.withZoneSameInstant(newDateTime.zone)
                    }
                },
                showTimezone = true
            )

            // End DateTime Toggle and Picker
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Add End Date & Time",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = useEndDateTime,
                    onCheckedChange = { checked ->
                        useEndDateTime = checked
                        if (checked && endDateTime == null) {
                            // Default end datetime to 2 hours after start
                            endDateTime = startDateTime.plusHours(2)
                        } else if (!checked) {
                            endDateTime = null
                        }
                    }
                )
            }

            if (useEndDateTime) {
                DateTimePickerField(
                    label = "End Date & Time",
                    selectedDateTime = endDateTime,
                    onDateTimeSelected = { newDateTime ->
                        endDateTime = newDateTime
                    },
                    showTimezone = false // Use same timezone as start
                )
            }

            // Divider
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

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
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Booking?") },
            text = {
                Text("Are you sure you want to delete this booking from $provider? This action cannot be undone.")
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
                Text("Delete Booking")
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

/**
 * Parse ISO datetime string with timezone into ZonedDateTime
 */
private fun parseDateTime(isoDateTime: String, timezoneId: String): ZonedDateTime {
    return try {
        ZonedDateTime.parse(isoDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    } catch (e: Exception) {
        // Fallback: use current time with specified timezone
        ZonedDateTime.now(ZoneId.of(timezoneId))
    }
}
