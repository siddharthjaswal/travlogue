package com.sid.domain.repository.session

import com.aurora.data.data.dao.ChatSessionDao
import com.aurora.data.data.entity.ChatSessionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface ChatRepository {
    /**
     * Chat Session related operations
     */
    fun getAllSessions(): Flow<List<ChatSessionEntity>>
    fun getSessionById(sessionId: Long): Flow<ChatSessionEntity?>
    suspend fun createNewSession(): Long
    suspend fun getLastActiveSession(): ChatSessionEntity?
    suspend fun getOrCreateActiveSession(): Long
}

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatSessionDao: ChatSessionDao
) : ChatRepository {

    override fun getAllSessions(): Flow<List<ChatSessionEntity>> {
        return chatSessionDao.getAllSessions()
    }

    override fun getSessionById(sessionId: Long): Flow<ChatSessionEntity?> {
        return chatSessionDao.getSessionById(sessionId)
    }

    override suspend fun createNewSession(): Long {
        return withContext(Dispatchers.IO) {
            val newSession = ChatSessionEntity(
                tripId = null,
                startTime = System.currentTimeMillis()
            )
            chatSessionDao.insertSession(newSession)
        }
    }

    override suspend fun getLastActiveSession(): ChatSessionEntity? {
        return withContext(Dispatchers.IO) {
            chatSessionDao.getAbsoluteLatestSession() // Using the more specific DAO method
        }
    }

    override suspend fun getOrCreateActiveSession(): Long {
        return withContext(Dispatchers.IO) {

            val existingSession = chatSessionDao.getAbsoluteLatestSession()

            existingSession?.id ?: createNewSession()
        }
    }
}