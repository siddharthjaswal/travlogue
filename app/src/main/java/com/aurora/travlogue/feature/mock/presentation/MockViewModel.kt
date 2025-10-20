package com.aurora.travlogue.feature.mock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.local.entities.*
import com.aurora.travlogue.core.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MockViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {

    /**
     * Creates a complete trip with all details filled in (no gaps)
     */
    fun createCompleteTrip(onComplete: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Create trip
                val tripId = UUID.randomUUID().toString()
                val trip = Trip(
                    id = tripId,
                    name = "Complete Japan Adventure",
                    originCity = "San Francisco",
                    dateType = DateType.FIXED,
                    startDate = "2025-07-01",
                    endDate = "2025-07-10",
                    flexibleMonth = null,
                    flexibleDuration = null
                )
                tripRepository.insertTrip(trip)

                // Create locations
                val tokyoId = UUID.randomUUID().toString()
                val kyotoId = UUID.randomUUID().toString()
                val osakaId = UUID.randomUUID().toString()

                val tokyo = Location(
                    id = tokyoId,
                    tripId = tripId,
                    name = "Tokyo",
                    country = "Japan",
                    date = "2025-07-02", // Updated to match actual arrival date
                    latitude = 35.6762,
                    longitude = 139.6503,
                    order = 1,
                    timezone = "Asia/Tokyo",
                    arrivalDateTime = "2025-07-02T14:30:00+09:00", // Flight arrival (JAL123 endDateTime)
                    departureDateTime = "2025-07-05T09:00:00+09:00" // Train departure to Kyoto (JR456 startDateTime)
                )

                val kyoto = Location(
                    id = kyotoId,
                    tripId = tripId,
                    name = "Kyoto",
                    country = "Japan",
                    date = "2025-07-05",
                    latitude = 35.0116,
                    longitude = 135.7681,
                    order = 2,
                    timezone = "Asia/Tokyo",
                    arrivalDateTime = "2025-07-05T11:15:00+09:00", // Train arrival from Tokyo (JR456 endDateTime)
                    departureDateTime = "2025-07-08T09:30:00+09:00" // Train departure to Osaka (JR789 startDateTime)
                )

                val osaka = Location(
                    id = osakaId,
                    tripId = tripId,
                    name = "Osaka",
                    country = "Japan",
                    date = "2025-07-08",
                    latitude = 34.6937,
                    longitude = 135.5023,
                    order = 3,
                    timezone = "Asia/Tokyo",
                    arrivalDateTime = "2025-07-08T10:00:00+09:00", // Train arrival from Kyoto (JR789 endDateTime)
                    departureDateTime = "2025-07-10T18:00:00+09:00" // End of trip (estimated checkout/departure)
                )

                tripRepository.insertLocation(tokyo)
                tripRepository.insertLocation(kyoto)
                tripRepository.insertLocation(osaka)

                // Create activities
                val activities = listOf(
                    Activity(
                        locationId = tokyoId,
                        title = "Visit Senso-ji Temple",
                        description = "Explore Tokyo's oldest temple",
                        date = "2025-07-01",
                        timeSlot = TimeSlot.MORNING,
                        type = ActivityType.ATTRACTION,
                        startTime = "09:00",
                        endTime = "11:30"
                    ),
                    Activity(
                        locationId = tokyoId,
                        title = "Shibuya Crossing",
                        description = "Experience the famous scramble crossing",
                        date = "2025-07-02",
                        timeSlot = TimeSlot.AFTERNOON,
                        type = ActivityType.ATTRACTION,
                        startTime = "13:00",
                        endTime = "15:00"
                    ),
                    Activity(
                        locationId = tokyoId,
                        title = "Tsukiji Fish Market",
                        description = "Fresh sushi breakfast",
                        date = "2025-07-03",
                        timeSlot = TimeSlot.MORNING,
                        type = ActivityType.FOOD,
                        startTime = "09:00",
                        endTime = "10:30"
                    ),
                    Activity(
                        locationId = kyotoId,
                        title = "Fushimi Inari Shrine",
                        description = "Walk through thousands of torii gates",
                        date = "2025-07-05",
                        timeSlot = TimeSlot.MORNING,
                        type = ActivityType.ATTRACTION,
                        startTime = "09:00",
                        endTime = "12:00"
                    ),
                    Activity(
                        locationId = kyotoId,
                        title = "Arashiyama Bamboo Forest",
                        description = "Stroll through bamboo grove",
                        date = "2025-07-06",
                        timeSlot = TimeSlot.AFTERNOON,
                        type = ActivityType.ATTRACTION,
                        startTime = "13:00",
                        endTime = "15:30"
                    ),
                    Activity(
                        locationId = osakaId,
                        title = "Osaka Castle",
                        description = "Visit historic castle",
                        date = "2025-07-08",
                        timeSlot = TimeSlot.MORNING,
                        type = ActivityType.ATTRACTION,
                        startTime = "09:00",
                        endTime = "11:30"
                    ),
                    Activity(
                        locationId = osakaId,
                        title = "Dotonbori Food Street",
                        description = "Try street food",
                        date = "2025-07-09",
                        timeSlot = TimeSlot.EVENING,
                        type = ActivityType.FOOD,
                        startTime = "17:00",
                        endTime = "19:00"
                    )
                )

                activities.forEach { tripRepository.insertActivity(it) }

                // Create bookings (all transit covered!)
                val bookings = listOf(
                    // Flight to Tokyo
                    Booking(
                        tripId = tripId,
                        type = BookingType.FLIGHT,
                        confirmationNumber = "JAL123",
                        provider = "Japan Airlines",
                        startDateTime = "2025-07-01T10:00:00-07:00",
                        endDateTime = "2025-07-02T14:30:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = "San Francisco (SFO)",
                        toLocation = "Tokyo Narita (NRT)",
                        price = 850.00,
                        currency = "USD",
                        notes = "Window seat",
                        imageUri = null
                    ),
                    // Hotel Tokyo
                    Booking(
                        tripId = tripId,
                        type = BookingType.HOTEL,
                        confirmationNumber = "HTL001",
                        provider = "Tokyo Bay Hotel",
                        startDateTime = "2025-07-01T15:00:00+09:00",
                        endDateTime = "2025-07-05T11:00:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = null,
                        toLocation = "Tokyo Shibuya",
                        price = 120.00,
                        currency = "USD",
                        notes = "Breakfast included",
                        imageUri = null
                    ),
                    // Train Tokyo to Kyoto
                    Booking(
                        tripId = tripId,
                        type = BookingType.TRAIN,
                        confirmationNumber = "JR456",
                        provider = "JR Shinkansen",
                        startDateTime = "2025-07-05T09:00:00+09:00",
                        endDateTime = "2025-07-05T11:15:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = "Tokyo Station",
                        toLocation = "Kyoto Station",
                        price = 140.00,
                        currency = "USD",
                        notes = "Reserved seat",
                        imageUri = null
                    ),
                    // Hotel Kyoto
                    Booking(
                        tripId = tripId,
                        type = BookingType.HOTEL,
                        confirmationNumber = "HTL002",
                        provider = "Kyoto Traditional Inn",
                        startDateTime = "2025-07-05T15:00:00+09:00",
                        endDateTime = "2025-07-08T11:00:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = null,
                        toLocation = "Kyoto Gion",
                        price = 95.00,
                        currency = "USD",
                        notes = "Ryokan style",
                        imageUri = null
                    ),
                    // Train Kyoto to Osaka
                    Booking(
                        tripId = tripId,
                        type = BookingType.TRAIN,
                        confirmationNumber = "JR789",
                        provider = "JR Local",
                        startDateTime = "2025-07-08T09:30:00+09:00",
                        endDateTime = "2025-07-08T10:00:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = "Kyoto Station",
                        toLocation = "Osaka Station",
                        price = 15.00,
                        currency = "USD",
                        notes = "Local train",
                        imageUri = null
                    ),
                    // Hotel Osaka
                    Booking(
                        tripId = tripId,
                        type = BookingType.HOTEL,
                        confirmationNumber = "HTL003",
                        provider = "Osaka Business Hotel",
                        startDateTime = "2025-07-08T14:00:00+09:00",
                        endDateTime = "2025-07-10T11:00:00+09:00",
                        timezone = "Asia/Tokyo",
                        fromLocation = null,
                        toLocation = "Osaka Namba",
                        price = 85.00,
                        currency = "USD",
                        notes = null,
                        imageUri = null
                    )
                )

                bookings.forEach { tripRepository.insertBooking(it) }

                onComplete(tripId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Creates a trip with missing details that will trigger gap detection
     */
    fun createTripWithGaps(onComplete: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Create trip
                val tripId = UUID.randomUUID().toString()
                val trip = Trip(
                    id = tripId,
                    name = "Italy Trip (Incomplete)",
                    originCity = "New York",
                    dateType = DateType.FIXED,
                    startDate = "2025-08-01",
                    endDate = "2025-08-15",
                    flexibleMonth = null,
                    flexibleDuration = null
                )
                tripRepository.insertTrip(trip)

                // Create locations (with gaps in dates!)
                val romeId = UUID.randomUUID().toString()
                val florenceId = UUID.randomUUID().toString()
                val veniceId = UUID.randomUUID().toString()

                val rome = Location(
                    id = romeId,
                    tripId = tripId,
                    name = "Rome",
                    country = "Italy",
                    date = "2025-08-01",
                    latitude = 41.9028,
                    longitude = 12.4964,
                    order = 1,
                    timezone = "Europe/Rome",
                    arrivalDateTime = "2025-08-01T22:30:00+02:00", // Arrival from flight
                    departureDateTime = "2025-08-08T10:00:00+02:00" // Leaving for Florence (no booking)
                )

                // Gap: Days Aug 2-7 are unplanned!

                val florence = Location(
                    id = florenceId,
                    tripId = tripId,
                    name = "Florence",
                    country = "Italy",
                    date = "2025-08-08",
                    latitude = 43.7696,
                    longitude = 11.2558,
                    order = 2,
                    timezone = "Europe/Rome",
                    arrivalDateTime = "2025-08-08T12:00:00+02:00", // Estimated arrival (no booking)
                    departureDateTime = "2025-08-12T09:00:00+02:00" // Leaving for Venice (no booking)
                )

                // Gap: Days Aug 9-11 are unplanned!

                val venice = Location(
                    id = veniceId,
                    tripId = tripId,
                    name = "Venice",
                    country = "Italy",
                    date = "2025-08-12",
                    latitude = 45.4408,
                    longitude = 12.3155,
                    order = 3,
                    timezone = "Europe/Rome",
                    arrivalDateTime = "2025-08-12T11:00:00+02:00", // Estimated arrival (no booking)
                    departureDateTime = "2025-08-15T18:00:00+02:00" // End of trip
                )

                tripRepository.insertLocation(rome)
                tripRepository.insertLocation(florence)
                tripRepository.insertLocation(venice)

                // Create some activities (but not for all days)
                val activities = listOf(
                    Activity(
                        locationId = romeId,
                        title = "Visit Colosseum",
                        description = "Tour the ancient amphitheater",
                        date = "2025-08-01",
                        timeSlot = TimeSlot.MORNING,
                        type = ActivityType.ATTRACTION,
                        startTime = "09:00",
                        endTime = "11:30"
                    ),
                    Activity(
                        locationId = florenceId,
                        title = "Uffizi Gallery",
                        description = "Renaissance art museum",
                        date = "2025-08-08",
                        timeSlot = TimeSlot.AFTERNOON,
                        type = ActivityType.ATTRACTION,
                        startTime = "13:00",
                        endTime = "16:00"
                    ),
                    Activity(
                        locationId = veniceId,
                        title = "Gondola Ride",
                        description = "Tour canals by gondola",
                        date = "2025-08-12",
                        timeSlot = TimeSlot.EVENING,
                        type = ActivityType.ATTRACTION,
                        startTime = "17:00",
                        endTime = "18:30"
                    )
                )

                activities.forEach { tripRepository.insertActivity(it) }

                // Create bookings (but MISSING transit between cities!)
                val bookings = listOf(
                    // Flight to Rome
                    Booking(
                        tripId = tripId,
                        type = BookingType.FLIGHT,
                        confirmationNumber = "AA456",
                        provider = "American Airlines",
                        startDateTime = "2025-08-01T08:00:00-04:00",
                        endDateTime = "2025-08-01T22:30:00+02:00",
                        timezone = "Europe/Rome",
                        fromLocation = "New York (JFK)",
                        toLocation = "Rome (FCO)",
                        price = 950.00,
                        currency = "USD",
                        notes = null,
                        imageUri = null
                    ),
                    // Hotel Rome
                    Booking(
                        tripId = tripId,
                        type = BookingType.HOTEL,
                        confirmationNumber = "HTL101",
                        provider = "Rome City Hotel",
                        startDateTime = "2025-08-01T14:00:00+02:00",
                        endDateTime = "2025-08-08T11:00:00+02:00",
                        timezone = "Europe/Rome",
                        fromLocation = null,
                        toLocation = "Rome Centro",
                        price = 110.00,
                        currency = "USD",
                        notes = null,
                        imageUri = null
                    )
                    // NO TRAIN BOOKING from Rome to Florence! (Gap!)
                    // NO HOTEL in Florence! (Gap!)
                    // NO TRAIN BOOKING from Florence to Venice! (Gap!)
                )

                bookings.forEach { tripRepository.insertBooking(it) }

                onComplete(tripId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
