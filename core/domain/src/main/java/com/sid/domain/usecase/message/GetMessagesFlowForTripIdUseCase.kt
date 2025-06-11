package com.sid.domain.usecase.message

import com.sid.domain.repository.message.MessageRepository
import javax.inject.Inject

/**
 * Use case to explicitly load messages for a given session.
 */
class GetMessagesFlowForTripIdUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    operator fun invoke(tripId: Long) = messageRepository.getMessagesFlowForTripId(tripId)
}