package com.aurora.travlogue.core.domain.usecase

import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.Trip

/**
 * Use case to create a new trip
 */
class CreateTripUseCase(private val repository: TripRepository) {
    suspend operator fun invoke(trip: Trip) {
        repository.insertTrip(trip)
    }
}
