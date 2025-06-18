package com.aurora.genesis.domain

import com.aurora.data.data.entity.day.DayEntity
import com.aurora.data.data.entity.trip.TripEntity
import kotlinx.coroutines.flow.Flow

internal sealed class UiState {
    internal data object EmptyState : UiState()
    internal data class GetTimelinesState(
        val trip: TripEntity,
        val daysFlow: Flow<List<DayEntity>>
    ) : UiState()
}