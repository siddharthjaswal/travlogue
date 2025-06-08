package com.sid.domain.usecase.trip

import com.aurora.data.data.entity.trip.TripEntity
import com.sid.domain.repository.trip.TripRepository
import javax.inject.Inject

class CreateTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    /**
     * Creates a new trip.
     * @param trip The TripEntity to create.
     * @return The ID of the newly created trip.
     */
    suspend operator fun invoke(trip: TripEntity): Long {
        return tripRepository.createTrip(trip)
    }
}