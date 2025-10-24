package com.aurora.travlogue.feature.tripdetail.components.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Activity
import com.aurora.travlogue.core.domain.model.Booking
import com.aurora.travlogue.core.domain.model.BookingType
import com.aurora.travlogue.core.domain.model.Gap
import com.aurora.travlogue.core.domain.model.Location
import com.aurora.travlogue.core.domain.model.TimeSlot
import com.aurora.travlogue.feature.tripdetail.components.timeline.*
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Timeline tab using shared TimelineItem from shared module
 * - All dates shown with badges
 * - Individual activity cards (no DayCard wrapper)
 * - "Nothing Planned" for empty days
 * - Chronological flow
 */
@Composable
fun TimelineTab(
    timelineItems: List<com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem>,
    locations: List<Location>,
    onActivityClick: (Activity) -> Unit,
    onBookingClick: (Booking) -> Unit,
    onGapClick: (Gap) -> Unit,
    onAddActivity: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (timelineItems.isEmpty()) {
        EmptyTimelineState(
            onAddActivity = onAddActivity,
            modifier = modifier
        )
    } else {

        // Pre-calculate which items should show dates
        val itemsWithDateFlags = timelineItems.mapIndexed { index, item ->
            val currentDate = extractDate(item.getDateTime())
            val showDate = if (currentDate != null) {
                val previousItems = timelineItems.subList(0, index)
                previousItems.none { extractDate(it.getDateTime()) == currentDate }
            } else {
                false
            }
            Pair(item, showDate)
        }

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            itemsIndexed(
                items = itemsWithDateFlags,
                key = { _, (item, _) -> item.getKey() }
            ) { index, (item, showDate) ->
                when (item) {
                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.TransitArrival -> {
                        TimelineItem(
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
                        TimelineItem(
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
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            HotelCheckInCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.ActivityItem -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.primary
                        ) {
                            ActivityTimelineCard(
                                activity = item.activity,
                                onClick = { onActivityClick(item.activity) }
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.NothingPlanned -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        ) {
                            NothingPlannedCard()
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.HotelCheckOut -> {
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.secondary
                        ) {
                            HotelCheckOutCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.CityGoodbye -> {
                        TimelineItem(
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
                        TimelineItem(
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
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            TransitCard(booking = item.booking)
                        }
                    }

                    is com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem.OriginDeparture -> {
                        TimelineItem(
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
                        TimelineItem(
                            dateTime = item.getDateTime(),
                            showDate = showDate,
                            dotColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            BookingTimelineCard(
                                booking = item.booking,
                                onClick = { onBookingClick(item.booking) }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Removed local TimelineItem sealed class and buildCompleteTimeline function
// Now using shared versions from:
// - com.aurora.travlogue.feature.tripdetail.domain.models.TimelineItem
// - com.aurora.travlogue.feature.tripdetail.domain.service.TimelineBuilder

@Composable
private fun EmptyTimelineState(
    onAddActivity: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "📅",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No schedule yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Add locations and activities to build your itinerary",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onAddActivity) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Activity")
            }
        }
    }
}

