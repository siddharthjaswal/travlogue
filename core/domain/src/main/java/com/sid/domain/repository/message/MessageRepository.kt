package com.sid.domain.repository.message

import com.aurora.data.data.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing messages.
 * Provides methods to retrieve and save messages related to chat sessions.
 */
interface MessageRepository {
    /**
     * Retrieves a flow of messages for a given chat session.
     *
     * @param sessionId The unique identifier of the chat session.
     * @return A [Flow] emitting a list of [MessageEntity] objects for the session.
     */
    fun getMessagesForSession(sessionId: Long): Flow<List<MessageEntity>>

    /**
     * Saves a message to the repository.
     *
     * @param message The [MessageEntity] to be saved.
     * @return The ID of the newly saved message.
     */
    suspend fun saveMessage(message: MessageEntity): Long
}
