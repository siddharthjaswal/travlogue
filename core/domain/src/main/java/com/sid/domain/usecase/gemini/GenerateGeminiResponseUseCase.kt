package com.sid.domain.usecase.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.sid.domain.repository.gemini.GeminiRepository
import javax.inject.Inject

class GenerateGeminiResponseUseCase @Inject constructor(
    private val geminiRepository: GeminiRepository
) {
    suspend operator fun invoke(prompt: String, chatHistory: List<MessageEntity>): String? {
        return geminiRepository.generateContent(prompt, chatHistory)
    }
}