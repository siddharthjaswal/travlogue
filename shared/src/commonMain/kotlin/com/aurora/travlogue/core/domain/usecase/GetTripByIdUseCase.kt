package com.aurora.travlogue.core.domain.usecase

import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.Trip
import kotlinx.coroutines.flow.Flow

/**
 * Use case to get a trip by ID
 */
class GetTripByIdUseCase(private val repository: TripRepository) {
    operator fun invoke(tripId: String): Flow<Trip?> = repository.getTripById(tripId)
}
