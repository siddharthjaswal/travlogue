package com.aurora.genesis.domain

sealed interface UiState {
    data object EmptyState : UiState
    data object GetTimelinesState : UiState
    data object ModificationState : UiState
}