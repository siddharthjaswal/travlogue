package com.sid.domain.repository.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.message.SENDER_AI
import com.aurora.data.data.entity.message.SENDER_USER
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content // Added import
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface GeminiRepository {
    /**
     * Generates content based on the given prompt using the Gemini API.
     *
     * @param prompt The text prompt to send to the model.
     * @param chatHistory A list of previous messages in the conversation.
     * @return The generated text response from the model, or null if an error occurred.
     */
    suspend fun generateContent(prompt: String, chatHistory: List<MessageEntity>): String?
}

class GeminiRepositoryImpl @Inject constructor() : GeminiRepository {

    val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel(
            modelName = "gemini-1.5-flash"
        )

    override suspend fun generateContent(
        prompt: String,
        chatHistory: List<MessageEntity>
    ): String? {
        return withContext(Dispatchers.IO) {
            try {
                Timber.d("Building chat history for Gemini. History size: ${chatHistory.size}")
                val history = chatHistory.mapNotNull { message ->
                    when (message.sender) {
                        SENDER_USER -> content(role = "user") { text(message.content) }
                        SENDER_AI -> content(role = "model") { text(message.content) }
                        else -> null
                    }
                }

                Timber.d("Starting chat with history. Mapped history size: ${history.size}")
                val chat = model.startChat(history = history)

                Timber.d("Sending prompt to Gemini: $prompt")
                val response = chat.sendMessage(prompt)
                val responseText = response.text
                Timber.d("Received response from Gemini: $responseText")
                responseText
            } catch (e: Exception) {
                Timber.e(e, "Error generating content with Gemini")
                null
            }
        }
    }
}
