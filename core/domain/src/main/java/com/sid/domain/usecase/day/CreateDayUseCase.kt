package com.sid.domain.usecase.day

import com.aurora.data.data.entity.day.DayEntity
import com.sid.domain.repository.day.DayRepository
import javax.inject.Inject

class CreateDayUseCase @Inject constructor(
    private val dayRepository: DayRepository
) {
    /**
     * Creates a new day.
     * @param day The DayEntity to create.
     * @return The ID of the newly created day.
     */
    suspend operator fun invoke(day: DayEntity): Long {
        return dayRepository.createDay(day)
    }
}
