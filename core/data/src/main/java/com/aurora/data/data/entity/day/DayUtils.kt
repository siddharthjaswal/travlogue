package com.aurora.data.data.entity.day

import com.aurora.data.data.model.TransitMode

fun createDayEntity(
    tripId: Long,
    day: Int,
    date: Long? = null,
    arrivalTime: Long? = null,
    departureTime: Long? = null,
    transitMode: TransitMode? = null,
    place: String? = null,
    transitDetails: String? = null,
    notes: String? = null
): DayEntity {
    return DayEntity(
        tripId = tripId,
        place = place,
        date = date,
        day = day,
        arrivalTime = arrivalTime,
        departureTime = departureTime,
        transitMode = transitMode,
        transitDetails = transitDetails,
        notes = notes
    )
}
