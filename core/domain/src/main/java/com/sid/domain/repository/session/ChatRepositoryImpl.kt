package com.sid.domain.repository.session

import com.aurora.data.data.dao.ChatSessionDao
import com.aurora.data.data.entity.ChatSessionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

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

    override suspend fun createNewSession(tripId: Long?): Long {
        return withContext(Dispatchers.IO) {
            val newSession = ChatSessionEntity(
                tripId = tripId,
                startTime = System.currentTimeMillis()
            )
            chatSessionDao.insertSession(newSession)
        }
    }

    override suspend fun getLastActiveSession(): ChatSessionEntity? {
        // This logic assumes "last active" means the one with the most recent startTime.
        // You might have other criteria (e.g., a flag in the entity, user preference).
        return withContext(Dispatchers.IO) {
            // chatSessionDao.getAllSessions() returns Flow<List<...>>
            // We need to get the first emission of the list and then take the first item.
            chatSessionDao.getAllSessions().firstOrNull()?.firstOrNull()
        }
    }

    override suspend fun getOrCreateActiveSession(tripId: Long?): Long {
        return withContext(Dispatchers.IO) {
            val lastSession = getLastActiveSession() // Use the method defined above
            // You might want more sophisticated logic here:
            // - If tripId is provided, try to find a session for that trip.
            // - If no session for that trip, create one.
            // - If no tripId, use the absolute last session or create a new general one.

            // Simplified: Use last session if it exists, otherwise create a new one.
            // If tripId is relevant for distinguishing sessions, adjust this.
            if (lastSession != null) {
                // If tripId is provided and lastSession's tripId doesn't match,
                // you might decide to create a new session anyway.
                // For now, let's assume we reuse the very last session or create a new one.
                if (tripId != null && lastSession.tripId != tripId) {
                    createNewSession(tripId)
                } else {
                    lastSession.id
                }
            } else {
                createNewSession(tripId)
            }
        }
    }
}