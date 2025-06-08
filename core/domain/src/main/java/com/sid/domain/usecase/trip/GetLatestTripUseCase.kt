package com.sid.domain.usecase.trip

// TripRepository is in the same package, so direct import is not strictly needed
// import com.sid.domain.repository.trip.TripRepository
import com.aurora.data.data.entity.trip.TripEntity
import com.sid.domain.repository.trip.TripRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    /**
     * Fetches the most recently created trip.
     * @return A Flow emitting the latest TripEntity, or null if no trips exist.
     */
    operator fun invoke(): Flow<TripEntity?> {
        return tripRepository.getLatestTrip()
    }
}