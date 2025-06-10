package com.sid.domain.usecase.trip

import com.aurora.data.data.entity.trip.TripEntity
import com.aurora.data.data.entity.trip.TripPlanningStage
import com.sid.domain.repository.trip.TripRepository
import javax.inject.Inject

class GetTripPlanningStageUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    operator fun invoke(trip: TripEntity?): TripPlanningStage {
        return tripRepository.getTripStage(trip)
    }
}
