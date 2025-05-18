package com.sid.domain.repository.gemini

interface GeminiRepository {
    /**
     * Generates content based on the given prompt using the Gemini API.
     *
     * @param prompt The text prompt to send to the model.
     * @return The generated text response from the model, or null if an error occurred.
     */
    suspend fun generateContent(prompt: String): String?
}