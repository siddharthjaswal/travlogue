package com.sid.domain.repository.message

import com.aurora.data.data.dao.MessageDao
import com.aurora.data.data.entity.MessageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

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

@Singleton
class MessageRepositoryImpl @Inject constructor(
    private val messageDao: MessageDao,
) : MessageRepository {
    override fun getMessagesForSession(sessionId: Long): Flow<List<MessageEntity>> {
        return messageDao.getMessagesForSession(sessionId)
    }

    override suspend fun saveMessage(message: MessageEntity): Long {
        return withContext(Dispatchers.IO) {
            messageDao.insertMessage(message)
        }
    }
}
