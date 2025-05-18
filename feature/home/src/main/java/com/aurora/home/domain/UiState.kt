package com.aurora.home.domain

import com.aurora.data.data.entity.MessageEntity

internal sealed class UiState {
    data object Empty : UiState()
    data object Loading : UiState()
    data class HomeState(
        val messages: List<MessageEntity> = emptyList(),
        val currentSessionId: Long? = null
    ) : UiState()
}