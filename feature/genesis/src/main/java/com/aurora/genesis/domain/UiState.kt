package com.aurora.genesis.domain

import com.aurora.data.data.entity.trip.TripEntity

internal sealed class UiState {
    internal data object EmptyState : UiState()
    internal data class GetTimelinesState(val trip: TripEntity) : UiState()
}