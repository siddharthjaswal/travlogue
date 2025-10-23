package com.aurora.travlogue.core.domain.usecase

import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.Gap
import com.aurora.travlogue.core.domain.service.GapDetectionService

/**
 * Use case to detect gaps in a trip's itinerary
 */
class DetectTripGapsUseCase(
    private val repository: TripRepository,
    private val gapDetectionService: GapDetectionService
) {
    suspend operator fun invoke(tripId: String): List<Gap> {
        val trip = repository.getTripByIdSync(tripId) ?: return emptyList()
        val locations = repository.getLocationsForTripSync(tripId)
        val bookings = repository.getBookingsForTripSync(tripId)

        return gapDetectionService.detectGaps(trip, locations, bookings)
    }
}
