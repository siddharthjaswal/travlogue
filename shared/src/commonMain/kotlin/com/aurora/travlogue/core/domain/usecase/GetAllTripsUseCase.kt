package com.aurora.travlogue.core.domain.usecase

import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.Trip
import kotlinx.coroutines.flow.Flow

/**
 * Use case to get all trips
 */
class GetAllTripsUseCase(private val repository: TripRepository) {
    operator fun invoke(): Flow<List<Trip>> = repository.allTrips
}
