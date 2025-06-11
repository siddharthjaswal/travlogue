package com.sid.domain.repository.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
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
    suspend fun generateContent(prompt: String, chatHistory: List<MessageEntity>): String?
}

class GeminiRepositoryImpl @Inject constructor() : GeminiRepository {


    val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel(
            modelName = "gemini-2.0-flash"
        )

    override suspend fun generateContent(
        prompt: String,
        chatHistory: List<MessageEntity>
    ): String? {
        return withContext(Dispatchers.IO) {
            try {
                Timber.d("Sending prompt to Gemini: $prompt")
                val response = model.generateContent(prompt)
                Timber.d("Received response from Gemini: ${response.text}")
                response.text
            } catch (e: Exception) {
                Timber.e(e, "Error generating content with Gemini")
                null // Return null or throw a custom domain exception
            }
        }
    }
}