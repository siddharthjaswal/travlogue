package com.aurora.travlogue.feature.tripdetail.components.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.feature.tripdetail.components.dialogs.DateTimePickerField
import java.time.ZoneId
import java.time.ZonedDateTime
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
            booking.endDateTime?.let {
                parseDateTime(it, booking.endTimezone ?: booking.timezone)
            }
        )
    }
    var useEndDateTime by remember { mutableStateOf(booking.endDateTime != null) }

    // Validation
    val isProviderValid = provider.isNotBlank()

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
                actions = {
                    IconButton(onClick = { showDeleteConfirmation = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Booking",
                            tint = MaterialTheme.colorScheme.error
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
                    if (isProviderValid) {
                        val priceValue = price.toDoubleOrNull()
                        onSave(
                            booking.copy(
                                type = selectedType,
                                confirmationNumber = confirmationNumber.takeIf { it.isNotBlank() },
                                provider = provider,
                                startDateTime = startDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                                endDateTime = endDateTime?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                                timezone = startDateTime.zone.id,
                                endTimezone = endDateTime?.zone?.id?.takeIf { it != startDateTime.zone.id },
                                fromLocation = fromLocation.takeIf { it.isNotBlank() },
                                toLocation = toLocation.takeIf { it.isNotBlank() },
                                price = priceValue,
                                currency = currency.takeIf { priceValue != null && it.isNotBlank() },
                                notes = notes.takeIf { it.isNotBlank() }
                            )
                        )
                    }
                },
                isSaveEnabled = isProviderValid
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
                },
                showTimezone = true
            )

            // End DateTime Toggle and Picker
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                val isCrossTimezoneBooking = selectedType in listOf(
                    BookingType.FLIGHT,
                    BookingType.TRAIN,
                    BookingType.BUS
                )

                DateTimePickerField(
                    label = if (isCrossTimezoneBooking) "End Date & Time (Arrival)" else "End Date & Time",
                    selectedDateTime = endDateTime,
                    onDateTimeSelected = { newDateTime ->
                        endDateTime = newDateTime
                    },
                    showTimezone = isCrossTimezoneBooking // Show timezone for cross-timezone bookings
                )

                if (isCrossTimezoneBooking && endDateTime != null) {
                    // Show a helpful message about timezone
                    Text(
                        text = "💡 Tip: Set the arrival timezone if different from departure",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
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

@Preview(name = "Edit Booking Dialog", showBackground = true, widthDp = 360)
@Composable
private fun EditBookingDialogPreview() {
    MaterialTheme {
        EditBookingDialog(
            booking = PreviewData.bookingFlight,
            onDismiss = {},
            onSave = { _ -> },
            onDelete = {}
        )
    }
}

@Preview(name = "Edit Booking Bottom Bar", showBackground = true, widthDp = 360)
@Composable
private fun EditBookingBottomActionBarPreview() {
    MaterialTheme {
        BottomActionBar(
            onCancelClick = {},
            onSaveClick = {},
            isSaveEnabled = true
        )
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
