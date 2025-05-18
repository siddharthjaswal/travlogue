package com.sid.domain.repository.session

import com.aurora.data.data.entity.ChatSessionEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    /**
     * Chat Session related operations
     */
    fun getAllSessions(): Flow<List<ChatSessionEntity>>
    fun getSessionById(sessionId: Long): Flow<ChatSessionEntity?>
    suspend fun createNewSession(): Long // Returns new session ID
    suspend fun getLastActiveSession(): ChatSessionEntity?
    suspend fun getOrCreateActiveSession(): Long // Key method

    // Potentially other methods like:
    // suspend fun updateSession(session: ChatSessionEntity)
    // suspend fun deleteSession(sessionId: Long)
}