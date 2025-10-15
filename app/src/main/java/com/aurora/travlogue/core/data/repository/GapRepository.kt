package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.dao.GapDao
import com.aurora.travlogue.core.data.local.entities.Gap
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GapRepository @Inject constructor(
    private val gapDao: GapDao
) {

    fun getGapsByTripId(tripId: String): Flow<List<Gap>> {
        return gapDao.getGapsByTripId(tripId)
    }

    fun getUnresolvedGapsByTripId(tripId: String): Flow<List<Gap>> {
        return gapDao.getUnresolvedGapsByTripId(tripId)
    }

    suspend fun getGapsByTripIdSync(tripId: String): List<Gap> {
        return gapDao.getGapsByTripIdSync(tripId)
    }

    suspend fun getGapById(gapId: String): Gap? {
        return gapDao.getGapById(gapId)
    }

    suspend fun insertGap(gap: Gap) {
        gapDao.insertGap(gap)
    }

    suspend fun insertGaps(gaps: List<Gap>) {
        gapDao.insertGaps(gaps)
    }

    suspend fun updateGap(gap: Gap) {
        gapDao.updateGap(gap)
    }

    suspend fun deleteGap(gap: Gap) {
        gapDao.deleteGap(gap)
    }

    suspend fun deleteGapById(gapId: String) {
        gapDao.deleteGapById(gapId)
    }

    suspend fun deleteGapsByTripId(tripId: String) {
        gapDao.deleteGapsByTripId(tripId)
    }

    suspend fun markGapAsResolved(gapId: String) {
        gapDao.markGapAsResolved(gapId)
    }
}
