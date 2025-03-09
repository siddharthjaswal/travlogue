package com.aurora.genesis.domain

internal sealed class UiState {
    data object EmptyState : UiState()
    data object GetTimelinesState : UiState()
    data object ModificationState : UiState()
}