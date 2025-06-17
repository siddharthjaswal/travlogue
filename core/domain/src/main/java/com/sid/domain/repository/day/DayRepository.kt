package com.sid.domain.repository.day

import com.aurora.data.data.dao.DayDao
import com.aurora.data.data.entity.day.DayEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface DayRepository {
    suspend fun createDay(day: DayEntity): Long
    suspend fun getDayById(dayId: Long): DayEntity?
    fun getDaysForTrip(tripId: Long): Flow<List<DayEntity>>
    suspend fun updateDay(day: DayEntity)
    suspend fun deleteDay(day: DayEntity)
}

@Singleton
class DayRepositoryImpl @Inject constructor(
    private val dayDao: DayDao,
) : DayRepository {
    override suspend fun createDay(day: DayEntity): Long {
        return dayDao.insert(day)
    }

    override suspend fun getDayById(dayId: Long): DayEntity? {
        return dayDao.getDayById(dayId)
    }

    override fun getDaysForTrip(tripId: Long): Flow<List<DayEntity>> {
        return dayDao.getDaysForTrip(tripId)
    }

    override suspend fun updateDay(day: DayEntity) {
        dayDao.update(day)
    }

    override suspend fun deleteDay(day: DayEntity) {
        dayDao.delete(day)
    }
}
