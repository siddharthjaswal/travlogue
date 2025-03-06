package com.aurora.genesis.domain

sealed interface UiState {
    data object Empty : UiState
    data object Create : UiState
    data object Modify : UiState
}