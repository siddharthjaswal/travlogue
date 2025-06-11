package com.sid.domain.usecase.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.google.firebase.ai.type.GenerateContentResponse
import com.sid.domain.repository.gemini.GeminiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateGeminiResponseUseCase @Inject constructor(
    private val geminiRepository: GeminiRepository
) {
    operator fun invoke(prompt: String, chatHistory: List<MessageEntity>): Flow<String> {
        return geminiRepository.generateContent(prompt, chatHistory)
    }
}