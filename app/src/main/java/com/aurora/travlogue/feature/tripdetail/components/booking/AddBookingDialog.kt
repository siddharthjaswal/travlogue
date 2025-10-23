package com.aurora.travlogue.feature.tripdetail.components.booking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.BookingType
import com.aurora.travlogue.feature.tripdetail.components.dialogs.DateTimePickerField
import java.time.ZonedDateTime
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
        endTimezone: String?,
        fromLocation: String?,
        toLocation: String?,
        price: Double?,
        currency: String?,
        notes: String?
    ) -> Unit,
    tripStartDate: String? = null,
    tripEndDate: String? = null
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

    // DateTime state with timezone support
    var startDateTime by remember {
        mutableStateOf<ZonedDateTime>(ZonedDateTime.now())
    }
    var endDateTime by remember {
        mutableStateOf<ZonedDateTime?>(null)
    }
    var useEndDateTime by remember { mutableStateOf(false) }

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
                            startDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                            endDateTime?.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                            startDateTime.zone.id,
                            endDateTime?.zone?.id?.takeIf { it != startDateTime.zone.id },
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

            // Divider
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Start DateTime Picker
            DateTimePickerField(
                label = "Start Date & Time *",
                selectedDateTime = startDateTime,
                onDateTimeSelected = { newDateTime ->
                    startDateTime = newDateTime
                },
                tripStartDate = tripStartDate,
                tripEndDate = tripEndDate,
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
                    tripStartDate = tripStartDate,
                    tripEndDate = tripEndDate,
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

@Preview(name = "Add Booking Dialog", showBackground = true, widthDp = 360)
@Composable
private fun AddBookingDialogPreview() {
    MaterialTheme {
        AddBookingDialog(
            onDismiss = {},
            onSave = { _, _, _, _, _, _, _, _, _, _, _, _ -> }
        )
    }
}

@Preview(name = "Booking Bottom Action Bar", showBackground = true, widthDp = 360)
@Composable
private fun AddBookingBottomActionBarPreview() {
    MaterialTheme {
        BottomActionBar(
            onCancelClick = {},
            onSaveClick = {},
            isSaveEnabled = true
        )
    }
}
