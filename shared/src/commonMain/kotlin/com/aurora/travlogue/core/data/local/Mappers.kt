package com.aurora.travlogue.core.data.local

import com.aurora.travlogue.core.domain.model.*
import com.aurora.travlogue.database.*

/**
 * Extension functions to map between SQLDelight database types and domain models
 */

// Trip mappings
fun Trips.toDomainModel(): Trip {
    return Trip(
        id = id,
        name = name,
        originCity = originCity,
        dateType = DateType.valueOf(dateType),
        startDate = startDate,
        endDate = endDate,
        flexibleMonth = flexibleMonth,
        flexibleDuration = flexibleDuration?.toInt(),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Trip.toDbModel(): Trips {
    return Trips(
        id = id,
        name = name,
        originCity = originCity,
        dateType = dateType.name,
        startDate = startDate,
        endDate = endDate,
        flexibleMonth = flexibleMonth,
        flexibleDuration = flexibleDuration?.toLong(),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

// Location mappings
fun Locations.toDomainModel(): Location {
    return Location(
        id = id,
        tripId = tripId,
        name = name,
        country = country,
        date = date,
        latitude = latitude,
        longitude = longitude,
        order = order.toInt(),
        timezone = timezone,
        arrivalDateTime = arrivalDateTime,
        departureDateTime = departureDateTime
    )
}

fun Location.toDbModel(): Locations {
    return Locations(
        id = id,
        tripId = tripId,
        name = name,
        country = country,
        date = date,
        latitude = latitude,
        longitude = longitude,
        order = order.toLong(),
        timezone = timezone,
        arrivalDateTime = arrivalDateTime,
        departureDateTime = departureDateTime
    )
}

// Activity mappings
fun Activities.toDomainModel(): Activity {
    return Activity(
        id = id,
        locationId = locationId,
        title = title,
        description = description,
        date = date,
        timeSlot = timeSlot?.let { TimeSlot.valueOf(it) },
        type = ActivityType.valueOf(type),
        startTime = startTime,
        endTime = endTime
    )
}

fun Activity.toDbModel(): Activities {
    return Activities(
        id = id,
        locationId = locationId,
        title = title,
        description = description,
        date = date,
        timeSlot = timeSlot?.name,
        type = type.name,
        startTime = startTime,
        endTime = endTime
    )
}

// Booking mappings
fun Bookings.toDomainModel(): Booking {
    return Booking(
        id = id,
        tripId = tripId,
        type = BookingType.valueOf(type),
        confirmationNumber = confirmationNumber,
        provider = provider,
        startDateTime = startDateTime,
        endDateTime = endDateTime,
        timezone = timezone,
        endTimezone = endTimezone,
        fromLocation = fromLocation,
        toLocation = toLocation,
        price = price,
        currency = currency,
        notes = notes,
        imageUri = imageUri
    )
}

fun Booking.toDbModel(): Bookings {
    return Bookings(
        id = id,
        tripId = tripId,
        type = type.name,
        confirmationNumber = confirmationNumber,
        provider = provider,
        startDateTime = startDateTime,
        endDateTime = endDateTime,
        timezone = timezone,
        endTimezone = endTimezone,
        fromLocation = fromLocation,
        toLocation = toLocation,
        price = price,
        currency = currency,
        notes = notes,
        imageUri = imageUri
    )
}

// Gap mappings
fun Gaps.toDomainModel(): Gap {
    return Gap(
        id = id,
        tripId = tripId,
        gapType = GapType.valueOf(gapType),
        fromLocationId = fromLocationId,
        toLocationId = toLocationId,
        fromDate = fromDate,
        toDate = toDate,
        isResolved = isResolved != 0L
    )
}

fun Gap.toDbModel(): Gaps {
    return Gaps(
        id = id,
        tripId = tripId,
        gapType = gapType.name,
        fromLocationId = fromLocationId,
        toLocationId = toLocationId,
        fromDate = fromDate,
        toDate = toDate,
        isResolved = if (isResolved) 1L else 0L
    )
}

// TransitOption mappings
fun Transit_options.toDomainModel(): TransitOption {
    return TransitOption(
        id = id,
        gapId = gapId,
        mode = TransitMode.valueOf(mode),
        provider = provider,
        duration = duration.toInt(),
        price = price,
        currency = currency,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        fetchedAt = fetchedAt
    )
}

fun TransitOption.toDbModel(): Transit_options {
    return Transit_options(
        id = id,
        gapId = gapId,
        mode = mode.name,
        provider = provider,
        duration = duration.toLong(),
        price = price,
        currency = currency,
        departureTime = departureTime,
        arrivalTime = arrivalTime,
        fetchedAt = fetchedAt
    )
}
