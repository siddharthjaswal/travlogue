package com.aurora.genesis.domain

internal sealed class UiState {
    internal data object EmptyState : UiState()
    internal data object CreateYourTripState : UiState()
}