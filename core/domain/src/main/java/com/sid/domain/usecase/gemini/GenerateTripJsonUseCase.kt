package com.sid.domain.usecase.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.trip.TripEntity
import com.sid.domain.repository.gemini.GeminiRepository
import javax.inject.Inject

class GenerateTripJsonUseCase @Inject constructor(
    private val geminiRepository: GeminiRepository
) {
    suspend operator fun invoke(
        prompt: String,
        chatHistory: List<MessageEntity>
    ): TripEntity? {
        return geminiRepository.generateTripJsonContent(prompt, chatHistory)
    }
}
