package com.aurora.home.domain

import com.aurora.data.data.entity.message.MessageEntity

/**
 * Represents the different states for the Home screen.
 */
internal sealed interface HomeUiState {
    /**
     * Represents the loading state.
     */
    data object Loading : HomeUiState

    /**
     * Represents the state where there are no messages.
     * @param tripId The ID of the current chat session.
     */
    data class NoMessages(val tripId: Long) : HomeUiState

    /**
     * Represents the state where chat messages are available.
     * @param messages The list of messages.
     * @param tripId The ID of the current chat session.
     */
    data class ChatMessages(
        val messages: List<MessageEntity> = emptyList(),
        val tripId: Long
    ) : HomeUiState

    /**
     * Represents an error state.
     * @param message The error message.
     */
    data class Error(val message: String) : HomeUiState
}
