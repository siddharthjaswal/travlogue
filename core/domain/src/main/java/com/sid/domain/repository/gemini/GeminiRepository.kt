package com.sid.domain.repository.gemini

import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface GeminiRepository {
    /**
     * Generates content based on the given prompt using the Gemini API.
     *
     * @param prompt The text prompt to send to the model.
     * @return The generated text response from the model, or null if an error occurred.
     */
    suspend fun generateContent(prompt: String): String?
}

class GeminiRepositoryImpl @Inject constructor() : GeminiRepository {

    private val generativeModel =
        Firebase.vertexAI.generativeModel("gemini-2.0-flash") // "gemini-1.5-flash-latest" "gemini-pro"

    override suspend fun generateContent(prompt: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                Timber.d("Sending prompt to Gemini: $prompt")
                val response = generativeModel.generateContent(prompt)
                Timber.d("Received response from Gemini: ${response.text}")
                response.text
            } catch (e: Exception) {
                Timber.e(e, "Error generating content with Gemini")
                null // Return null or throw a custom domain exception
            }
        }
    }
}