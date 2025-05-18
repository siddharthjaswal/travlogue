package com.sid.domain.usecase.session

import com.sid.domain.repository.session.ChatRepository
import javax.inject.Inject

/**
 * Use case to get the ID of the current active chat session.
 * If no active session exists, it creates a new one.
 * Optionally, a tripId can be provided to associate the session.
 */
class GetOrCreateActiveSessionUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): Long {
        return chatRepository.getOrCreateActiveSession()
    }
}