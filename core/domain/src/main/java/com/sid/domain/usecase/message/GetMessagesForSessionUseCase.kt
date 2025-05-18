package com.sid.domain.usecase.message

import com.sid.domain.repository.message.MessageRepository
import javax.inject.Inject

/**
 * Use case to explicitly load messages for a given session.
 */
class GetMessagesForSessionUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    operator fun invoke(sessionId: Long) = messageRepository.getMessagesForSession(sessionId)
}