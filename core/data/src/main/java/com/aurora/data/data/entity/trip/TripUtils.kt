package com.aurora.data.data.entity.trip

fun createNewTripEntity(name: String): TripEntity {
    return TripEntity(
        name = name,
        lastUpdated = System.currentTimeMillis()
    )
}
