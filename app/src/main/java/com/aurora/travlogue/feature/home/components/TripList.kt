package com.aurora.travlogue.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Trip
import com.aurora.travlogue.core.domain.model.TripMockData

@Composable
fun TripList(
    trips: List<Trip>,
    onTripClick: (String) -> Unit,
    onDeleteTrip: (Trip) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = trips,
            key = { it.id }
        ) { trip ->
            TripCard(
                trip = trip,
                onClick = { onTripClick(trip.id) },
                onDelete = { onDeleteTrip(trip) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TripListPreview() {
    TripList(
        trips = TripMockData.sampleTrips,
        onTripClick = {},
        onDeleteTrip = {}
    )
}

@Preview(showBackground = true, name = "Single Trip")
@Composable
private fun TripListSinglePreview() {
    TripList(
        trips = listOf(TripMockData.weekendGetaway),
        onTripClick = {},
        onDeleteTrip = {}
    )
}
