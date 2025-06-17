package com.sid.domain.usecase.day

import com.aurora.data.data.entity.day.DayEntity
import com.sid.domain.repository.day.DayRepository
import javax.inject.Inject

class GetDayByIdUseCase @Inject constructor(
    private val dayRepository: DayRepository
) {
    /**
     * Retrieves a day by its ID.
     * @param dayId The ID of the day to retrieve.
     * @return The DayEntity if found, null otherwise.
     */
    suspend operator fun invoke(dayId: Long): DayEntity? {
        return dayRepository.getDayById(dayId)
    }
}
