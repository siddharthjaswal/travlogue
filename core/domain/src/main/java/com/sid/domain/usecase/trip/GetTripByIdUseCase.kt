package com.sid.domain.usecase.trip

import com.aurora.data.data.entity.trip.TripEntity
import com.sid.domain.repository.trip.TripRepository
import javax.inject.Inject

class GetTripByIdUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(tripId: Long?): TripEntity? {
        return tripRepository.getTripById(tripId)
    }
}
