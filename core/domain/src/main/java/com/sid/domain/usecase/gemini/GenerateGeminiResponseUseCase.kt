package com.sid.domain.usecase.gemini

import com.sid.domain.repository.gemini.GeminiRepository
import javax.inject.Inject

class GenerateGeminiResponseUseCase @Inject constructor(
    private val geminiRepository: GeminiRepository
) {
    suspend operator fun invoke(prompt: String): String? {
        return geminiRepository.generateContent(prompt)
    }
}