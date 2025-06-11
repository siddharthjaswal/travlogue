package com.sid.domain.repository.gemini

import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.message.SENDER_AI
import com.aurora.data.data.entity.message.SENDER_USER
import com.aurora.data.data.entity.trip.TripEntity
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

interface GeminiRepository {
    fun generateContent(prompt: String, chatHistory: List<MessageEntity>): Flow<String>

    fun generateTripJsonContent(prompt: String, chatHistory: List<MessageEntity>): Flow<String>
}

class GeminiRepositoryImpl @Inject constructor() : GeminiRepository {
    private fun mapChatHistoryToGeminiFormat(chatHistory: List<MessageEntity>): List<Content> {
        Timber.d("Building chat history for Gemini. History size: ${chatHistory.size}")
        return chatHistory.mapNotNull { message ->
            when (message.sender) {
                SENDER_USER -> content(role = "user") { text(message.content) }
                SENDER_AI -> content(role = "model") { text(message.content) }
                else -> null
            }
        }.also { mappedHistory ->
            Timber.d("Mapped history size: ${mappedHistory.size}")
        }
    }

    val model = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = "gemini-1.5-flash",
        systemInstruction = content { text("You are a travel assistant") }
    )

    override fun generateContent(
        prompt: String,
        chatHistory: List<MessageEntity>
    ): Flow<String> {
        Timber.d("Building chat history for Gemini. History size: ${chatHistory.size}")
        val history = mapChatHistoryToGeminiFormat(chatHistory)

        Timber.d("Starting chat with history. Mapped history size: ${history.size}")
        val chat = model.startChat(history = history)

        Timber.d("Sending prompt to Gemini via stream: $prompt")
        return chat.sendMessageStream(prompt).map { it.text ?: "" }
    }

    val tripModel = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = "gemini-1.5-flash",
        systemInstruction = content { text("You are a travel assistant") },
        generationConfig = generationConfig {
            responseMimeType = "application/json"
            responseSchema = TripEntity.tripJsonSchema
        }
    )

    override fun generateTripJsonContent(
        prompt: String,
        chatHistory: List<MessageEntity>
    ): Flow<String> {

        Timber.d("Building chat history for Gemini. History size: ${chatHistory.size}")
        val history = mapChatHistoryToGeminiFormat(chatHistory)

        Timber.d("Starting chat with history. Mapped history size: ${history.size}")
        val chat = tripModel.startChat(history = history)

        Timber.d("Sending prompt to Gemini via stream: $prompt")
        return chat.sendMessageStream(prompt).map { it.text ?: "" }
    }
}
