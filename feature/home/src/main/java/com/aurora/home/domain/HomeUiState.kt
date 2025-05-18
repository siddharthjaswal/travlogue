package com.aurora.home.domain

import com.aurora.data.data.entity.MessageEntity

internal sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class NoMessages(val currentSessionId: Long) : HomeUiState

    data class ChatMessages(
        val messages: List<MessageEntity> = emptyList(),
        val currentSessionId: Long? = null
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}