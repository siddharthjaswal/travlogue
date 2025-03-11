package com.aurora.home.domain

internal sealed class UiState {
    data object EmptyState : UiState()
    data object HomeState : UiState()
}