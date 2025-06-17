package com.sid.domain.usecase.day

import com.aurora.data.data.entity.day.DayEntity
import com.sid.domain.repository.day.DayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDaysForTripUseCase @Inject constructor(
    private val dayRepository: DayRepository
) {
    /**
     * Retrieves a flow of day lists for a given trip ID.
     * @param tripId The ID of the trip.
     * @return A Flow emitting a list of DayEntity objects.
     */
    operator fun invoke(tripId: Long): Flow<List<DayEntity>> {
        return dayRepository.getDaysForTrip(tripId)
    }
}
