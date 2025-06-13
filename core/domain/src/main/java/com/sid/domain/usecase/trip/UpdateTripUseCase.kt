package com.sid.domain.usecase.trip

import com.aurora.data.data.entity.trip.TripEntity
import com.sid.domain.repository.trip.TripRepository
import javax.inject.Inject

class UpdateTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    /**
     * Updates an existing trip in the database.
     * @param trip The TripEntity with updated information.
     * @return True if the update was successful (one or more rows affected), false otherwise.
     */
    suspend operator fun invoke(trip: TripEntity): Boolean {
        return tripRepository.updateTrip(trip)
    }
}
