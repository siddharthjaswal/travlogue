package com.sid.domain.usecase.message

import com.aurora.data.data.entity.MessageEntity
import com.sid.domain.repository.message.MessageRepository
import javax.inject.Inject

/**
 * Use case to send a message.
 */
class SendMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(message: MessageEntity): Long {
        return messageRepository.saveMessage(message)
    }
}