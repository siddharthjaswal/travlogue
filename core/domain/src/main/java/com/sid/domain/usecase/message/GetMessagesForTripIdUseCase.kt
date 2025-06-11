package com.sid.domain.usecase.message

import com.aurora.data.data.entity.message.MessageEntity
import com.sid.domain.repository.message.MessageRepository
import javax.inject.Inject

class GetMessagesForTripIdUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(tripId: Long): List<MessageEntity> {
        return messageRepository.getMessagesForTripId(tripId)
    }
}
