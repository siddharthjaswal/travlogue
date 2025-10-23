package com.aurora.travlogue.core.domain.usecase

import com.aurora.travlogue.core.data.repository.TripRepository

/**
 * Use case to delete a trip
 */
class DeleteTripUseCase(private val repository: TripRepository) {
    suspend operator fun invoke(tripId: String) {
        repository.deleteTripById(tripId)
    }
}
