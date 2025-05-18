package com.sid.domain.repository.message

import com.aurora.data.data.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessagesForSession(sessionId: Long): Flow<List<MessageEntity>>
    suspend fun saveMessage(message: MessageEntity): Long
}