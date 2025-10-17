package com.aurora.travlogue.core.common

import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.ActivityType
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Gap
import com.aurora.travlogue.core.data.local.entities.GapType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.TimeSlot
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.feature.tripdetail.domain.models.DaySchedule

/**
 * Preview data for Compose previews.
 * Provides realistic mock data for all entities.
 */
object PreviewData {

    // ==================== Trips ====================

    val sampleTripFixed = Trip(
        id = "trip-1",
        name = "Japan Adventure 2025",
        originCity = "San Francisco",
        dateType = DateType.FIXED,
        startDate = "2025-07-01",
        endDate = "2025-07-10",
        flexibleMonth = null,
        flexibleDuration = null
    )

    val sampleTripFlexible = Trip(
        id = "trip-2",
        name = "Europe Exploration",
        originCity = "New York",
        dateType = DateType.FLEXIBLE,
        startDate = null,
        endDate = null,
        flexibleMonth = "November 2025",
        flexibleDuration = 14
    )

    val sampleTrips = listOf(
        sampleTripFixed,
        sampleTripFlexible,
        Trip(
            id = "trip-3",
            name = "Quick Weekend Getaway",
            originCity = "Los Angeles",
            dateType = DateType.FIXED,
            startDate = "2025-08-15",
            endDate = "2025-08-17",
            flexibleMonth = null,
            flexibleDuration = null
        )
    )

    // ==================== Locations ====================

    val locationTokyo = Location(
        id = "loc-1",
        tripId = "trip-1",
        name = "Tokyo",
        country = "Japan",
        date = "2025-07-01",
        latitude = 35.6762,
        longitude = 139.6503,
        order = 1
    )

    val locationKyoto = Location(
        id = "loc-2",
        tripId = "trip-1",
        name = "Kyoto",
        country = "Japan",
        date = "2025-07-05",
        latitude = 35.0116,
        longitude = 135.7681,
        order = 2
    )

    val locationOsaka = Location(
        id = "loc-3",
        tripId = "trip-1",
        name = "Osaka",
        country = "Japan",
        date = "2025-07-08",
        latitude = 34.6937,
        longitude = 135.5023,
        order = 3
    )

    val sampleLocations = listOf(locationTokyo, locationKyoto, locationOsaka)

    // ==================== Activities ====================

    val activitySensoJi = Activity(
        id = "act-1",
        locationId = "loc-1",
        title = "Visit Senso-ji Temple",
        description = "Explore Tokyo's oldest temple in Asakusa",
        date = "2025-07-01",
        timeSlot = TimeSlot.MORNING,
        type = ActivityType.ATTRACTION
    )

    val activityShibuya = Activity(
        id = "act-2",
        locationId = "loc-1",
        title = "Shibuya Crossing & Shopping",
        description = "Experience the famous scramble crossing and explore Shibuya district",
        date = "2025-07-01",
        timeSlot = TimeSlot.AFTERNOON,
        type = ActivityType.ATTRACTION
    )

    val activityRamen = Activity(
        id = "act-3",
        locationId = "loc-1",
        title = "Ichiran Ramen Dinner",
        description = "Try the famous tonkotsu ramen",
        date = "2025-07-01",
        timeSlot = TimeSlot.EVENING,
        type = ActivityType.FOOD
    )

    val activityFushimiInari = Activity(
        id = "act-4",
        locationId = "loc-2",
        title = "Fushimi Inari Shrine",
        description = "Walk through thousands of red torii gates",
        date = "2025-07-05",
        timeSlot = TimeSlot.MORNING,
        type = ActivityType.ATTRACTION
    )

    val activityArashiyama = Activity(
        id = "act-5",
        locationId = "loc-2",
        title = "Arashiyama Bamboo Forest",
        description = "Stroll through the iconic bamboo grove",
        date = "2025-07-05",
        timeSlot = TimeSlot.AFTERNOON,
        type = ActivityType.ATTRACTION
    )

    val sampleActivities = listOf(
        activitySensoJi,
        activityShibuya,
        activityRamen,
        activityFushimiInari,
        activityArashiyama
    )

    // ==================== Bookings ====================

    val bookingFlight = Booking(
        id = "book-1",
        tripId = "trip-1",
        type = BookingType.FLIGHT,
        confirmationNumber = "JAL123456",
        provider = "Japan Airlines",
        startDateTime = "2025-07-01T10:00:00-07:00",
        endDateTime = "2025-07-02T14:30:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = "San Francisco (SFO)",
        toLocation = "Tokyo Narita (NRT)",
        price = 850.00,
        currency = "USD",
        notes = "Window seat, meal included",
        imageUri = null
    )

    val bookingHotel = Booking(
        id = "book-2",
        tripId = "trip-1",
        type = BookingType.HOTEL,
        confirmationNumber = "HTL789012",
        provider = "Tokyo Bay Hotel",
        startDateTime = "2025-07-01T15:00:00+09:00",
        endDateTime = "2025-07-05T11:00:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = null,
        toLocation = "Tokyo Shibuya",
        price = 120.00,
        currency = "USD",
        notes = "Late check-in arranged, breakfast included",
        imageUri = null
    )

    val bookingTrain = Booking(
        id = "book-3",
        tripId = "trip-1",
        type = BookingType.TRAIN,
        confirmationNumber = "JR345678",
        provider = "JR Tokaido Shinkansen",
        startDateTime = "2025-07-05T09:00:00+09:00",
        endDateTime = "2025-07-05T11:15:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = "Tokyo Station",
        toLocation = "Kyoto Station",
        price = 140.00,
        currency = "USD",
        notes = "Reserved seat, car 7",
        imageUri = null
    )

    val sampleBookings = listOf(bookingFlight, bookingHotel, bookingTrain)

    // ==================== Gaps ====================

    val gapMissingTransit = Gap(
        id = "gap-1",
        tripId = "trip-1",
        gapType = GapType.MISSING_TRANSIT,
        fromLocationId = "loc-2",
        toLocationId = "loc-3",
        fromDate = "2025-07-05",
        toDate = "2025-07-08",
        isResolved = false
    )

    val gapUnplannedDay = Gap(
        id = "gap-2",
        tripId = "trip-1",
        gapType = GapType.UNPLANNED_DAY,
        fromLocationId = "loc-1",
        toLocationId = "loc-2",
        fromDate = "2025-07-02",
        toDate = "2025-07-04",
        isResolved = false
    )

    val gapTimeConflict = Gap(
        id = "gap-3",
        tripId = "trip-1",
        gapType = GapType.TIME_CONFLICT,
        fromLocationId = "loc-1",
        toLocationId = "loc-1",
        fromDate = "2025-07-01",
        toDate = "2025-07-01",
        isResolved = false
    )

    val gapResolved = Gap(
        id = "gap-4",
        tripId = "trip-1",
        gapType = GapType.MISSING_TRANSIT,
        fromLocationId = "loc-1",
        toLocationId = "loc-2",
        fromDate = "2025-07-01",
        toDate = "2025-07-02",
        isResolved = true
    )

    val sampleGaps = listOf(gapMissingTransit, gapUnplannedDay)
    val sampleGapsWithResolved = listOf(gapMissingTransit, gapUnplannedDay, gapResolved)

    // ==================== Day Schedules ====================

    val dayScheduleDay1 = DaySchedule(
        date = "2025-07-01",
        dayNumber = 1,
        location = locationTokyo,
        activitiesByTimeSlot = mapOf(
            TimeSlot.MORNING to listOf(activitySensoJi),
            TimeSlot.AFTERNOON to listOf(activityShibuya),
            TimeSlot.EVENING to listOf(activityRamen)
        ),
        dayNotes = null
    )

    val dayScheduleDay2 = DaySchedule(
        date = "2025-07-02",
        dayNumber = 2,
        location = locationTokyo,
        activitiesByTimeSlot = emptyMap(),
        dayNotes = "Free day for exploring"
    )

    val dayScheduleDay5 = DaySchedule(
        date = "2025-07-05",
        dayNumber = 5,
        location = locationKyoto,
        activitiesByTimeSlot = mapOf(
            TimeSlot.MORNING to listOf(activityFushimiInari),
            TimeSlot.AFTERNOON to listOf(activityArashiyama)
        ),
        dayNotes = null
    )

    val sampleDaySchedules = listOf(dayScheduleDay1, dayScheduleDay2, dayScheduleDay5)

    // ==================== Complete Trip Data Sets ====================

    /**
     * Complete trip data for previews with all relationships.
     */
    data class CompleteTripData(
        val trip: Trip,
        val locations: List<Location>,
        val activities: List<Activity>,
        val bookings: List<Booking>,
        val gaps: List<Gap>,
        val daySchedules: List<DaySchedule>
    )

    val completeTripWithGaps = CompleteTripData(
        trip = sampleTripFixed,
        locations = sampleLocations,
        activities = sampleActivities,
        bookings = sampleBookings,
        gaps = sampleGaps,
        daySchedules = sampleDaySchedules
    )

    val completeTripWithoutGaps = CompleteTripData(
        trip = sampleTripFixed,
        locations = sampleLocations,
        activities = sampleActivities,
        bookings = sampleBookings,
        gaps = emptyList(),
        daySchedules = sampleDaySchedules
    )

    val emptyTrip = CompleteTripData(
        trip = sampleTripFixed,
        locations = emptyList(),
        activities = emptyList(),
        bookings = emptyList(),
        gaps = emptyList(),
        daySchedules = emptyList()
    )

    // ==================== Utility Functions ====================

    /**
     * Get activities for a specific location.
     */
    fun getActivitiesForLocation(locationId: String): List<Activity> {
        return sampleActivities.filter { it.locationId == locationId }
    }

    /**
     * Get location by ID.
     */
    fun getLocationById(locationId: String): Location? {
        return sampleLocations.find { it.id == locationId }
    }

    /**
     * Get gaps for a specific trip.
     */
    fun getGapsForTrip(tripId: String): List<Gap> {
        return sampleGaps.filter { it.tripId == tripId }
    }
}
