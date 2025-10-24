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
import androidx.compose.ui.unit.sp
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
        // Tab Row (matching Android order: Timeline - Locations - Bookings - Overview)
        TabRow(
            selectedTabIndex = when (uiState.selectedTab) {
                TripDetailTab.TIMELINE -> 0
                TripDetailTab.LOCATIONS -> 1
                TripDetailTab.BOOKINGS -> 2
                TripDetailTab.OVERVIEW -> 3
            }
        ) {
            Tab(
                selected = uiState.selectedTab == TripDetailTab.TIMELINE,
                onClick = { onTabSelected(TripDetailTab.TIMELINE) },
                text = { Text("Timeline") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.LOCATIONS,
                onClick = { onTabSelected(TripDetailTab.LOCATIONS) },
                text = { Text("Locations (${uiState.locationCount})") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.BOOKINGS,
                onClick = { onTabSelected(TripDetailTab.BOOKINGS) },
                text = { Text("Bookings (${uiState.bookingCount})") }
            )
            Tab(
                selected = uiState.selectedTab == TripDetailTab.OVERVIEW,
                onClick = { onTabSelected(TripDetailTab.OVERVIEW) },
                text = { Text("Overview") }
            )
        }

        // Tab Content
        when (uiState.selectedTab) {
            TripDetailTab.TIMELINE -> TimelineTab(uiState, onDayClicked)
            TripDetailTab.LOCATIONS -> LocationsTab(uiState)
            TripDetailTab.BOOKINGS -> BookingsTab(uiState)
            TripDetailTab.OVERVIEW -> OverviewTab(uiState)
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
    if (uiState.timelineItems.isEmpty()) {
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
        // Pre-calculate which items should show dates
        val itemsWithDateFlags = uiState.timelineItems.mapIndexed { index, item ->
            val currentDate = extractDate(item.getDateTime())
            val showDate = if (currentDate != null) {
                val previousItems = uiState.timelineItems.subList(0, index)
                previousItems.none { extractDate(it.getDateTime()) == currentDate }
            } else {
                false
            }
            Pair(item, showDate)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            itemsIndexed(
                items = itemsWithDateFlags,
                key = { _, (item, _) -> item.getKey() }
            ) { index, (item, showDate) ->
                when (item) {
                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.TransitArrival -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            CityArrivalCard(
                                location = item.location,
                                arrivalDateTime = item.arrivalDateTime,
                                arrivalBooking = item.arrivalBooking
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.CityWelcome -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            WelcomeCityCard(
                                location = item.location,
                                arrivalDateTime = item.arrivalDateTime
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.HotelCheckIn -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            HotelCheckInCard(
                                booking = item.booking
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.ActivityItem -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            ActivityTimelineCard(
                                activity = item.activity
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.NothingPlanned -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        ) {
                            NothingPlannedCard()
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.HotelCheckOut -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            HotelCheckOutCard(
                                booking = item.booking
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.CityGoodbye -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            GoodbyeCityCard(
                                location = item.location,
                                departureDateTime = item.departureDateTime
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.TransitDeparture -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            CityDepartureCard(
                                location = item.location,
                                departureDateTime = item.departureDateTime,
                                departureBooking = item.departureBooking
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.InTransit -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            TransitCard(booking = item.booking)
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.OriginDeparture -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            OriginDepartureCard(
                                originCity = item.originCity,
                                departureDateTime = item.booking.startDateTime,
                                departureBooking = item.booking
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.BookingItem -> {
                        TimelineItemWrapper(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            BookingTimelineCard(
                                booking = item.booking
                            )
                        }
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

// ==================== TIMELINE COMPONENTS ====================

/**
 * Timeline item wrapper with circular date badge
 */
@Composable
private fun TimelineItemWrapper(
    dateTime: String?,
    showDate: Boolean = true,
    dotColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Date badge (left side) - 10% of width
        if (showDate && dateTime != null) {
            Box(
                modifier = Modifier.fillMaxWidth(0.1f),
                contentAlignment = Alignment.Center
            ) {
                CircularDateBadge(
                    dateTime = dateTime,
                    color = dotColor
                )
            }
        } else {
            // Spacer to maintain alignment when date is hidden
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        }

        // Content (right side) - 90% of width
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            content()
        }
    }
}

/**
 * Circular date badge showing weekday and day number
 */
@Composable
private fun CircularDateBadge(
    dateTime: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val (day, weekday) = parseDateToDisplay(dateTime)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        // Weekday on top
        Text(
            text = weekday,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                letterSpacing = 0.3.sp
            ),
            color = color.copy(alpha = 0.8f)
        )

        // Circle with day number
        Surface(
            shape = androidx.compose.foundation.shape.CircleShape,
            color = color.copy(alpha = 0.15f),
            shadowElevation = 1.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    ),
                    color = color
                )
            }
        }
    }
}

private fun parseDateToDisplay(isoDateTime: String): Pair<String, String> {
    return try {
        val instant = kotlinx.datetime.Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
        val day = localDateTime.dayOfMonth.toString()
        val weekday = localDateTime.dayOfWeek.name.take(3).uppercase()
        Pair(day, weekday)
    } catch (e: Exception) {
        try {
            val datePart = isoDateTime.substringBefore('T')
            val localDate = kotlinx.datetime.LocalDate.parse(datePart)
            val day = localDate.dayOfMonth.toString()
            val weekday = localDate.dayOfWeek.name.take(3).uppercase()
            Pair(day, weekday)
        } catch (e2: Exception) {
            Pair("--", "---")
        }
    }
}

/**
 * Extract date from ISO datetime for comparison
 */
private fun extractDate(isoDateTime: String?): String? {
    if (isoDateTime == null) return null
    return try {
        val instant = kotlinx.datetime.Instant.parse(isoDateTime)
        instant.toLocalDateTime(kotlinx.datetime.TimeZone.UTC).date.toString()
    } catch (e: Exception) {
        try {
            kotlinx.datetime.LocalDate.parse(isoDateTime.substringBefore('T')).toString()
        } catch (e2: Exception) {
            null
        }
    }
}

@Composable
private fun WelcomeCityCard(
    location: Location,
    arrivalDateTime: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.WavingHand,
                contentDescription = "Welcome",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Welcome to ${location.name}!",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatWelcomeTime(arrivalDateTime, location),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun GoodbyeCityCard(
    location: Location,
    departureDateTime: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "👋",
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Goodbye ${location.name}!",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = formatGoodbyeTime(departureDateTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private fun formatWelcomeTime(isoDateTime: String, location: Location): String {
    return try {
        val instant = kotlinx.datetime.Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
        val hour = localDateTime.hour

        val greeting = when (hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..21 -> "Good evening"
            else -> "Welcome"
        }

        "$greeting! You've arrived"
    } catch (e: Exception) {
        "Arrived at ${location.name}"
    }
}

private fun formatGoodbyeTime(isoDateTime: String): String {
    return try {
        val instant = kotlinx.datetime.Instant.parse(isoDateTime)
        "Time to head out"
    } catch (e: Exception) {
        "Departing soon"
    }
}

@Composable
private fun CityArrivalCard(
    location: Location,
    arrivalDateTime: String,
    arrivalBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FlightLand,
                contentDescription = "Arrival",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Arrive in ${location.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatDateTime(arrivalDateTime),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                if (arrivalBooking.fromLocation != null) {
                    Text(
                        text = "From ${arrivalBooking.fromLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CityDepartureCard(
    location: Location,
    departureDateTime: String,
    departureBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FlightTakeoff,
                contentDescription = "Departure",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Depart from ${location.name}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatDateTime(departureDateTime),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )

                if (departureBooking.toLocation != null) {
                    Text(
                        text = "To ${departureBooking.toLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun OriginDepartureCard(
    originCity: String,
    departureDateTime: String,
    departureBooking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FlightTakeoff,
                contentDescription = "Departure",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Depart from $originCity",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatDateTime(departureDateTime),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )

                if (departureBooking.toLocation != null) {
                    Text(
                        text = "To ${departureBooking.toLocation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun TransitCard(
    booking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = getTransitIcon(booking.type),
                contentDescription = "In Transit",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "In Transit",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = booking.provider,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun HotelCheckInCard(
    booking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Hotel,
                contentDescription = "Check In",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Check In: ${booking.provider}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatDateTime(booking.startDateTime),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun HotelCheckOutCard(
    booking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Hotel,
                contentDescription = "Check Out",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Check Out: ${booking.provider}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                booking.endDateTime?.let {
                    Text(
                        text = formatDateTime(it),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityTimelineCard(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                activity.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (activity.startTime != null && activity.endTime != null) {
                    Spacer(modifier = Modifier.height(4.dp))
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
}

@Composable
private fun BookingTimelineCard(
    booking: Booking,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
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
        }
    }
}

@Composable
private fun NothingPlannedCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Nothing Planned",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
        }
    }
}

private fun formatDateTime(isoDateTime: String): String {
    return try {
        val instant = kotlinx.datetime.Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
        val month = localDateTime.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
        val day = localDateTime.dayOfMonth
        val hour = if (localDateTime.hour == 0 || localDateTime.hour == 12) 12 else localDateTime.hour % 12
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val amPm = if (localDateTime.hour < 12) "AM" else "PM"
        "$month $day, $hour:$minute $amPm"
    } catch (e: Exception) {
        isoDateTime
    }
}

private fun getTransitIcon(type: BookingType) = when (type) {
    BookingType.FLIGHT -> Icons.Default.Flight
    BookingType.TRAIN -> Icons.Default.Train
    BookingType.BUS -> Icons.Default.DirectionsBus
    else -> Icons.Default.Flight
}
