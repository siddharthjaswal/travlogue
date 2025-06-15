package com.sid.domain.repository.gemini

import android.graphics.Bitmap
import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.message.SENDER_AI
import com.aurora.data.data.entity.message.SENDER_USER
import com.aurora.data.data.entity.trip.TripEntity
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.PublicPreviewAPI
import com.google.firebase.ai.type.SerializationException
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import com.google.gson.Gson
import com.sid.domain.repository.storage.ImageStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

private const val LOG_BUILDING_HISTORY_GEMINI = "Building chat history for Gemini. History size: %d"
private const val LOG_MAPPED_HISTORY_SIZE = "Mapped history size: %d"
private const val LOG_STARTING_CHAT_WITH_HISTORY =
    "Starting chat with history. Mapped history size: %d"
private const val LOG_SENDING_PROMPT_GEMINI_STREAM = "Sending prompt to Gemini via stream: %s"
private const val SYS_INSTRUCTION_TRAVEL_ASSISTANT =
    "You are a travel assistant. Break the questions in parts, ask one at time."
private const val SYS_INSTRUCTION_ADD_EMOJI_TO_NAME =
    "Add Destination Related Cute Emoji to the name field. Keep it maximum to 3 words and a emoji. Come up with cute names"
private const val LOG_RECEIVED_TRIP_JSON_RESPONSE = "Received Trip JSON response from Gemini: %s"
private const val LOG_PARSING_TRIP_JSON = "Attempting to parse Trip JSON"
private const val LOG_PARSED_TRIP_JSON_SUCCESS = "Successfully parsed Trip JSON to TripEntity: %s"
private const val LOG_PARSING_TRIP_JSON_ERROR = "Error parsing Trip JSON"
private const val LOG_EMPTY_TRIP_JSON_RESPONSE =
    "Received empty or null Trip JSON response from Gemini"

private const val IMAGEN_MODEL_NAME = "imagen-3.0-generate-002"
private const val LOG_INIT_IMAGEN_MODEL = "Initializing Imagen model: %s"
private const val LOG_GENERATING_BANNER_PROMPT = "Generating banner image with prompt: %s"
private const val LOG_BANNER_GENERATION_SUCCESS_OBJECT =
    "Successfully generated banner image object for prompt: %s"
private const val LOG_BANNER_NO_IMAGES_IN_RESPONSE = "No images returned from Imagen for prompt: %s"
private const val LOG_BANNER_GENERATION_ERROR = "Error generating banner image for prompt %s"
private const val LOG_BANNER_CONVERTED_TO_BITMAP = "Banner image converted to bitmap for prompt: %s"
private const val LOG_DELEGATING_IMAGE_SAVE = "Delegating image save for prompt: %s"

interface GeminiRepository {
    fun generateContent(prompt: String, chatHistory: List<MessageEntity>): Flow<String>

    suspend fun generateTripJsonContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): TripEntity?

    suspend fun generateBannerImage(
        prompt: String
    ): String?
}

class GeminiRepositoryImpl @Inject constructor(
    private val imageStorageRepository: ImageStorageRepository
) : GeminiRepository {

    private fun mapChatHistoryToGeminiFormat(chatHistory: List<MessageEntity>): List<Content> {
        Timber.d(LOG_BUILDING_HISTORY_GEMINI, chatHistory.size)
        return chatHistory.mapNotNull { message ->
            when (message.sender) {
                SENDER_USER -> content(role = "user") { text(message.content) }
                SENDER_AI -> content(role = "model") { text(message.content) }
                else -> null
            }
        }.also { mappedHistory ->
            Timber.d(LOG_MAPPED_HISTORY_SIZE, mappedHistory.size)
        }
    }

    val model = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = "gemini-1.5-flash",
        systemInstruction = content { text(SYS_INSTRUCTION_TRAVEL_ASSISTANT) })

    override fun generateContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): Flow<String> {
        val history = mapChatHistoryToGeminiFormat(chatHistory)
        Timber.d(LOG_STARTING_CHAT_WITH_HISTORY, history.size)
        val chat = model.startChat(history = history)
        Timber.d(LOG_SENDING_PROMPT_GEMINI_STREAM, prompt)
        return chat.sendMessageStream(prompt).map { it.text ?: "" }
    }

    val tripModel = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = "gemini-1.5-flash",
        systemInstruction = content { text(SYS_INSTRUCTION_ADD_EMOJI_TO_NAME) },
        generationConfig = generationConfig {
            responseMimeType = "application/json"
            responseSchema = TripEntity.tripJsonSchema
        })

    override suspend fun generateTripJsonContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): TripEntity? {
        val history = mapChatHistoryToGeminiFormat(chatHistory)
        Timber.d(LOG_STARTING_CHAT_WITH_HISTORY, history.size)
        val chat = tripModel.startChat(history = history)
        try {
            Timber.d(LOG_SENDING_PROMPT_GEMINI_STREAM, prompt)
            val response = chat.sendMessage(prompt)
            val jsonString = response.text
            if (jsonString.isNullOrBlank()) {
                Timber.w(LOG_EMPTY_TRIP_JSON_RESPONSE)
                return null
            }
            Timber.d(LOG_RECEIVED_TRIP_JSON_RESPONSE, jsonString)
            Timber.d(LOG_PARSING_TRIP_JSON)
            return try {
                Gson().fromJson(jsonString, TripEntity::class.java).also {
                    Timber.d(LOG_PARSED_TRIP_JSON_SUCCESS, it.toString())
                }
            } catch (e: SerializationException) {
                Timber.e(e, LOG_PARSING_TRIP_JSON_ERROR)
                null
            } catch (e: Exception) {
                Timber.e(e, "An unexpected error occurred during Trip JSON parsing")
                null
            }
        } catch (e: Exception) {
            Timber.e(e, "Error generating Trip JSON content from Gemini")
            return null
        }
    }

    @OptIn(PublicPreviewAPI::class)
    override suspend fun generateBannerImage(prompt: String): String? {
        Timber.d(LOG_GENERATING_BANNER_PROMPT, prompt)
        try {
            Timber.d(LOG_INIT_IMAGEN_MODEL, IMAGEN_MODEL_NAME)
            val imagenModel =
                Firebase.ai(backend = GenerativeBackend.googleAI()).imagenModel(IMAGEN_MODEL_NAME)
            val imageResponse = imagenModel.generateImages(prompt)
            if (imageResponse.images.isNotEmpty()) {
                val image = imageResponse.images.first()
                Timber.d(LOG_BANNER_GENERATION_SUCCESS_OBJECT, prompt)
                val bitmapImage: Bitmap = image.asBitmap()
                Timber.d(LOG_BANNER_CONVERTED_TO_BITMAP, prompt)
                Timber.d(LOG_DELEGATING_IMAGE_SAVE, prompt)
                return imageStorageRepository.saveImageToPictures(bitmapImage, "Banner_")
            } else {
                Timber.w(LOG_BANNER_NO_IMAGES_IN_RESPONSE, prompt)
                return null
            }
        } catch (e: Exception) {
            Timber.e(e, LOG_BANNER_GENERATION_ERROR, prompt)
            return null
        }
    }
}
