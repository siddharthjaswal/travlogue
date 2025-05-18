package com.sid.domain.repository.message

import com.aurora.data.data.dao.MessageDao
import com.aurora.data.data.entity.MessageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

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