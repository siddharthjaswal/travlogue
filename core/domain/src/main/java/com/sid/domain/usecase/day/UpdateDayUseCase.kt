package com.sid.domain.usecase.day

import com.aurora.data.data.entity.day.DayEntity
import com.sid.domain.repository.day.DayRepository
import javax.inject.Inject

class UpdateDayUseCase @Inject constructor(
    private val dayRepository: DayRepository
) {
    /**
     * Updates an existing day.
     * @param day The DayEntity to update.
     */
    suspend operator fun invoke(day: DayEntity) {
        dayRepository.updateDay(day)
    }
}
