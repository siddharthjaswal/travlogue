package com.sid.domain.usecase.gemini

import com.sid.domain.repository.gemini.GeminiRepository
import javax.inject.Inject

/**
 * Use case for generating a banner image based on a prompt.
 * It leverages the GeminiRepository to generate an image via an AI model
 * and then saves it, returning the file path.
 */
class GenerateBannerImageUseCase @Inject constructor(
    private val geminiRepository: GeminiRepository
) {
    /**
     * Generates a banner image using the provided prompt and saves it to storage.
     *
     * @param prompt The text prompt to guide the image generation.
     * @return The absolute file path of the saved image if successful, or null otherwise.
     */
    suspend operator fun invoke(prompt: String): String? {
        return geminiRepository.generateBannerImage(prompt)
    }
}
