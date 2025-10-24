package com.aurora.travlogue.desktop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.*
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import com.aurora.travlogue.feature.tripdetail.presentation.TripDetailTab
import com.aurora.travlogue.feature.tripdetail.presentation.TripDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Trip detail screen showing comprehensive trip information with tabs
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailScreen(
    tripId: String,
    onNavigateBack: () -> Unit,
    viewModel: TripDetailViewModel = koinViewModel { parametersOf(tripId) }
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.trip?.name ?: "Trip Details",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackPressed() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.retryLoading() }) {
                            Text("Retry")
                        }
                    }
                }
                uiState.trip != null -> {
                    TripDetailContent(
                        uiState = uiState,
                        onTabSelected = { viewModel.onTabSelected(it) },
                        onDayClicked = { viewModel.onDayClicked(it) }
                    )
                }
            }
        }
    }

    // Handle navigation events
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiEvent.NavigateBack -> onNavigateBack()
                else -> { /* Handle other events */ }
            }
        }
    }
}

@Composable
private fun TripDetailContent(
    uiState: com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiState,
    onTabSelected: (TripDetailTab) -> Unit,
    onDayClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Tab Row
        TabRow(
            selectedTabIndex = when (uiState.selectedTab) {
                TripDetailTab.OVERVIEW -> 0
                TripDetailTab.TIMELINE -> 1
                TripDetailTab.BOOKINGS -> 2
                TripDetailTab.LOCATIONS -> 3
            }
        ) {
            Tab(
                selected = uiState.selectedTab == TripDetailTab.OVERVIEW,
                onClick = { onTabSelected(TripDetailTab.OVERVIEW) },
                text = { Text("Overview") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.TIMELINE,
                onClick = { onTabSelected(TripDetailTab.TIMELINE) },
                text = { Text("Timeline") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.BOOKINGS,
                onClick = { onTabSelected(TripDetailTab.BOOKINGS) },
                text = { Text("Bookings (${uiState.bookingCount})") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.LOCATIONS,
                onClick = { onTabSelected(TripDetailTab.LOCATIONS) },
                text = { Text("Locations (${uiState.locationCount})") }
            )
        }

        // Tab Content
        when (uiState.selectedTab) {
            TripDetailTab.OVERVIEW -> OverviewTab(uiState)
            TripDetailTab.TIMELINE -> TimelineTab(uiState, onDayClicked)
            TripDetailTab.BOOKINGS -> BookingsTab(uiState)
            TripDetailTab.LOCATIONS -> LocationsTab(uiState)
        }
    }
}

// ==================== OVERVIEW TAB ====================

@Composable
private fun OverviewTab(uiState: com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Stats Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Locations",
                    value = uiState.locationCount.toString(),
                    icon = Icons.Default.Place,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Activities",
                    value = uiState.activityCount.toString(),
                    icon = Icons.Default.List,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Bookings",
                    value = uiState.bookingCount.toString(),
                    icon = Icons.Default.Info,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            // Trip Info Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Trip Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InfoRow(label = "Origin", value = uiState.trip?.originCity ?: "")

                    val dateText = when (uiState.trip?.dateType) {
                        DateType.FIXED -> {
                            "${uiState.trip?.startDate ?: ""} to ${uiState.trip?.endDate ?: ""}"
                        }
                        DateType.FLEXIBLE -> {
                            buildString {
                                append(uiState.trip?.flexibleMonth ?: "")
                                if (uiState.trip?.flexibleDuration != null) {
                                    append(" (${uiState.trip?.flexibleDuration} days)")
                                }
                            }
                        }
                        null -> ""
                    }
                    InfoRow(label = "Dates", value = dateText)
                    InfoRow(label = "Date Type", value = uiState.trip?.dateType?.name ?: "")
                }
            }
        }

        // Gaps Section
        if (uiState.gaps.isNotEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Planning Gaps",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        uiState.gaps.forEach { gap ->
                            GapItem(gap)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

        // Locations Summary
        if (uiState.locations.isNotEmpty()) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Locations",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        uiState.locations.forEach { location ->
                            LocationSummaryItem(location)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

// ==================== TIMELINE TAB ====================

@Composable
private fun TimelineTab(
    uiState: com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiState,
    onDayClicked: (String) -> Unit
) {
    if (uiState.daySchedules.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No timeline available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = uiState.daySchedules,
                key = { it.date }
            ) { daySchedule ->
                DayScheduleCard(
                    daySchedule = daySchedule,
                    isExpanded = daySchedule.date in uiState.expandedDays,
                    onDayClicked = onDayClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DayScheduleCard(
    daySchedule: DaySchedule,
    isExpanded: Boolean,
    onDayClicked: (String) -> Unit
) {
    Card(
        onClick = { onDayClicked(daySchedule.date) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Day Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Day ${daySchedule.dayNumber}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = daySchedule.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                daySchedule.location?.let { location ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Place,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = location.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Activities (when expanded)
            if (isExpanded && daySchedule.activitiesByTimeSlot.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                daySchedule.activitiesByTimeSlot.forEach { (timeSlot, activities) ->
                    Text(
                        text = timeSlot.name.replace("_", " "),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    activities.forEach { activity ->
                        ActivityItem(activity)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

// ==================== BOOKINGS TAB ====================

@Composable
private fun BookingsTab(uiState: com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiState) {
    if (uiState.bookings.isEmpty()) {
        EmptyState(
            icon = Icons.Default.Info,
            message = "No bookings yet"
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = uiState.bookings,
                key = { it.id }
            ) { booking ->
                BookingCard(booking)
            }
        }
    }
}

@Composable
private fun BookingCard(booking: Booking) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Type badge and provider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = getBookingTypeColor(booking.type)
                ) {
                    Text(
                        text = booking.type.name,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }

                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (booking.confirmationNumber != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Confirmation: ${booking.confirmationNumber}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Route/Location info
            when (booking.type) {
                BookingType.FLIGHT, BookingType.TRAIN, BookingType.BUS -> {
                    val from = booking.fromLocation
                    val to = booking.toLocation
                    if (from != null && to != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = from,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 8.dp).size(16.dp)
                            )
                            Text(
                                text = to,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                BookingType.HOTEL -> {
                    booking.toLocation?.let { location ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Place,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = location,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                else -> {}
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dates
            Text(
                text = "Start: ${booking.startDateTime}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (booking.endDateTime != null) {
                Text(
                    text = "End: ${booking.endDateTime}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Price
            if (booking.price != null && booking.currency != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${booking.currency} ${booking.price}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Notes
            booking.notes?.let { notes ->
                if (notes.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = notes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// ==================== LOCATIONS TAB ====================

@Composable
private fun LocationsTab(uiState: com.aurora.travlogue.feature.tripdetail.presentation.TripDetailUiState) {
    if (uiState.locations.isEmpty()) {
        EmptyState(
            icon = Icons.Default.Place,
            message = "No locations yet"
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = uiState.locations,
                key = { it.id }
            ) { location ->
                LocationCard(location)
            }
        }
    }
}

@Composable
private fun LocationCard(location: Location) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Location name and country
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = location.country,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (location.date != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Date: ${location.date}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (location.arrivalDateTime != null || location.departureDateTime != null) {
                Spacer(modifier = Modifier.height(8.dp))
                if (location.arrivalDateTime != null) {
                    Text(
                        text = "Arrival: ${location.arrivalDateTime}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (location.departureDateTime != null) {
                    Text(
                        text = "Departure: ${location.departureDateTime}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (location.timezone != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Timezone: ${location.timezone}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// ==================== HELPER COMPOSABLES ====================

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun GapItem(gap: Gap) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Icon(
            Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = gap.gapType.name.replace("_", " "),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
            Text(
                text = "${gap.fromDate} to ${gap.toDate}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    }
}

@Composable
private fun LocationSummaryItem(location: Location) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Place,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = location.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = location.country,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ActivityItem(activity: Activity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            activity.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (activity.startTime != null && activity.endTime != null) {
                Text(
                    text = "${activity.startTime} - ${activity.endTime}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Surface(
            shape = RoundedCornerShape(4.dp),
            color = getActivityTypeColor(activity.type)
        ) {
            Text(
                text = activity.type.name,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
        }
    }
}

@Composable
private fun EmptyState(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ==================== HELPER FUNCTIONS ====================

@Composable
private fun getBookingTypeColor(type: BookingType): Color {
    return when (type) {
        BookingType.FLIGHT -> Color(0xFF2196F3)
        BookingType.HOTEL -> Color(0xFF4CAF50)
        BookingType.TRAIN -> Color(0xFFFF9800)
        BookingType.BUS -> Color(0xFF9C27B0)
        BookingType.TICKET -> Color(0xFFE91E63)
        BookingType.OTHER -> Color(0xFF607D8B)
    }
}

@Composable
private fun getActivityTypeColor(type: ActivityType): Color {
    return when (type) {
        ActivityType.ATTRACTION -> Color(0xFF2196F3)
        ActivityType.FOOD -> Color(0xFFFF5722)
        ActivityType.BOOKING -> Color(0xFF4CAF50)
        ActivityType.TRANSIT -> Color(0xFF9C27B0)
        ActivityType.OTHER -> Color(0xFF607D8B)
    }
}
