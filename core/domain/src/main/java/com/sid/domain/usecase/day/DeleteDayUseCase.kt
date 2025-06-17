package com.sid.domain.usecase.day

import com.aurora.data.data.entity.day.DayEntity
import com.sid.domain.repository.day.DayRepository
import javax.inject.Inject

class DeleteDayUseCase @Inject constructor(
    private val dayRepository: DayRepository
) {
    /**
     * Deletes a day.
     * @param day The DayEntity to delete.
     */
    suspend operator fun invoke(day: DayEntity) {
        dayRepository.deleteDay(day)
    }
}
