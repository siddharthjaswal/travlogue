package com.sid.domain.repository.gemini

import android.graphics.Bitmap
import com.aurora.data.data.entity.day.DayEntity
import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.message.SENDER_AI
import com.aurora.data.data.entity.message.SENDER_USER
import com.aurora.data.data.entity.trip.TripEntity
import com.aurora.data.data.entity.trip.tripJsonSchema
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

private const val GEMINI_FLASH = "gemini-2.5-flash"

interface GeminiRepository {
    fun generateContent(prompt: String, chatHistory: List<MessageEntity>): Flow<String>

    suspend fun generateTripJsonContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): TripEntity?

    suspend fun generateDayJsonContent(
        prompt: String, chatHistory: List<MessageEntity>, tripId: Long
    ): DayEntity?

    suspend fun generateBannerImage(
        prompt: String
    ): String?
}

class GeminiRepositoryImpl @Inject constructor(
    private val imageStorageRepository: ImageStorageRepository
) : GeminiRepository {

    private fun mapChatHistoryToGeminiFormat(chatHistory: List<MessageEntity>): List<Content> {
        Timber.d(GeminiConstants.LOG_CHAT_HISTORY_BUILDING, chatHistory.size)
        return chatHistory.mapNotNull { message ->
            when (message.sender) {
                SENDER_USER -> content(role = "user") { text(message.content) }
                SENDER_AI -> content(role = "model") { text(message.content) }
                else -> null
            }
        }.also { mappedHistory ->
            Timber.d(GeminiConstants.LOG_CHAT_HISTORY_MAPPED_SIZE, mappedHistory.size)
        }
    }

    val model = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = GEMINI_FLASH,
        systemInstruction = content { text(GeminiConstants.SYS_INSTRUCTION_PHASE_ONE) })

    override fun generateContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): Flow<String> {
        val history = mapChatHistoryToGeminiFormat(chatHistory)
        Timber.d(GeminiConstants.LOG_CHAT_STARTING_WITH_HISTORY, history.size)
        val chat = model.startChat(history = history)
        Timber.d(GeminiConstants.LOG_CHAT_PROMPT_SENDING_STREAM, prompt)
        return chat.sendMessageStream(prompt).map { it.text ?: "" }
    }

    val tripModel = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
        modelName = GEMINI_FLASH,
        systemInstruction = content { text(GeminiConstants.SYS_INSTRUCTION_TRIP_JSON_NAME_FORMAT) },
        generationConfig = generationConfig {
            responseMimeType = "application/json"
            responseSchema = tripJsonSchema
        })

    override suspend fun generateTripJsonContent(
        prompt: String, chatHistory: List<MessageEntity>
    ): TripEntity? {
        val history = mapChatHistoryToGeminiFormat(chatHistory)
        Timber.d(
            GeminiConstants.LOG_CHAT_STARTING_WITH_HISTORY,
            history.size
        )
        val chat = tripModel.startChat(history = history)
        try {
            Timber.d(
                GeminiConstants.LOG_CHAT_PROMPT_SENDING_STREAM,
                prompt
            )
            val response = chat.sendMessage(prompt)
            val jsonString = response.text
            if (jsonString.isNullOrBlank()) {
                Timber.w(GeminiConstants.LOG_TRIP_JSON_RESPONSE_EMPTY)
                return null
            }
            Timber.d(GeminiConstants.LOG_TRIP_JSON_RESPONSE_RECEIVED, jsonString)
            Timber.d(GeminiConstants.LOG_TRIP_JSON_PARSING_ATTEMPT)
            return try {
                Gson().fromJson(jsonString, TripEntity::class.java).also {
                    Timber.d(GeminiConstants.LOG_TRIP_JSON_PARSE_SUCCESS, it.toString())
                }
            } catch (e: SerializationException) {
                Timber.e(e, GeminiConstants.LOG_TRIP_JSON_PARSE_ERROR)
                null
            } catch (e: Exception) {
                Timber.e(
                    e,
                    "An unexpected error occurred during Trip JSON parsing"
                )
                null
            }
        } catch (e: Exception) {
            Timber.e(
                e,
                "Error generating Trip JSON content from Gemini"
            )
            return null
        }
    }

//    val dayModel = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
//        modelName = GEMINI_FLASH,
//        systemInstruction = content { text(GeminiConstants.SYS_INSTRUCTION_DAY_JSON_NAME_FORMAT) },
//        generationConfig = generationConfig {
//            responseMimeType = "application/json"
//            responseSchema = dayJsonSchema
//        })

    override suspend fun generateDayJsonContent(
        prompt: String,
        chatHistory: List<MessageEntity>,
        tripId: Long
    ): DayEntity? {
        return null
    }


    @OptIn(PublicPreviewAPI::class)
    override suspend fun generateBannerImage(prompt: String): String? {
        Timber.d(GeminiConstants.LOG_BANNER_GENERATING_WITH_PROMPT, prompt)
        try {
            val systemContext =
                "Abstract artistic interpretation focused on main attractions, vibrant colors, modern style"
            Timber.d(
                GeminiConstants.LOG_IMAGEN_MODEL_INITIALIZING,
                GeminiConstants.IMAGEN_MODEL_NAME_BANNER
            )
            val imagenModel =
                Firebase.ai(backend = GenerativeBackend.googleAI())
                    .imagenModel(GeminiConstants.IMAGEN_MODEL_NAME_BANNER)
            val imageResponse = imagenModel.generateImages("$prompt [$systemContext]")
            if (imageResponse.images.isNotEmpty()) {
                val image = imageResponse.images.first()
                Timber.d(GeminiConstants.LOG_BANNER_GENERATION_SUCCESS_OBJECT, prompt)
                val bitmapImage: Bitmap = image.asBitmap()
                Timber.d(GeminiConstants.LOG_BANNER_BITMAP_CONVERTED, prompt)
                Timber.d(GeminiConstants.LOG_BANNER_IMAGE_SAVE_DELEGATING, prompt)
                return imageStorageRepository.saveImageToPictures(bitmapImage, "Banner_")
            } else {
                Timber.w(GeminiConstants.LOG_BANNER_RESPONSE_NO_IMAGES, prompt)
                return null
            }
        } catch (e: Exception) {
            Timber.e(e, GeminiConstants.LOG_BANNER_GENERATION_ERROR, prompt)
            return null
        }
    }
}
