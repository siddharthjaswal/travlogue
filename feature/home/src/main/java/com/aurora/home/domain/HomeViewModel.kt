package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.data.data.entity.message.createAiMessage
import com.aurora.data.data.entity.message.createSenderMessage
import com.aurora.data.data.entity.message.createSystemMessage
import com.aurora.data.data.entity.message.getInitialMessage
import com.aurora.data.data.entity.trip.INIT_TRIP_NAME
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_DESTINATION // Added
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_END_DATE // Added
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_START_DATE // Added
import com.aurora.data.data.entity.trip.PROMPT_PLANNING_COMPLETE // Added
import com.aurora.data.data.entity.trip.TripPlanningStage // Added
import com.aurora.data.data.entity.trip.createNewTripEntity
import com.sid.domain.usecase.gemini.GenerateGeminiResponseUseCase
import com.sid.domain.usecase.gemini.GenerateTripJsonUseCase
import com.sid.domain.usecase.message.GetMessagesFlowForTripIdUseCase
import com.sid.domain.usecase.message.GetMessagesForTripIdUseCase
import com.sid.domain.usecase.message.SendMessageUseCase
import com.sid.domain.usecase.trip.CreateTripUseCase
import com.sid.domain.usecase.trip.GetLatestTripUseCase
import com.sid.domain.usecase.trip.GetTripByIdUseCase
import com.sid.domain.usecase.trip.GetTripPlanningStageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMessagesFlowForTripIdUseCase: GetMessagesFlowForTripIdUseCase,
    private val getMessagesForTripIdUseCase: GetMessagesForTripIdUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val generateGeminiResponseUseCase: GenerateGeminiResponseUseCase,
    private val createTripUseCase: CreateTripUseCase,
    private val getLatestTripUseCase: GetLatestTripUseCase,
    private val getTripPlanningStageUseCase: GetTripPlanningStageUseCase,
    private val getTripByIdUseCase: GetTripByIdUseCase,
    private val generateTripJsonUseCase: GenerateTripJsonUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    internal val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    internal var latestTripId: Long? = null

    init {
        loadInitialSessionAndMessages()
    }

    private fun loadInitialSessionAndMessages() {
        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading
            val tripId = fetchLatestTrip() ?: createTripAndReturnId()
            getMessagesFlowForTripIdUseCase(tripId).catch { e ->
                val errorMessage = "Failed to load messages: ${e.localizedMessage}"
                _homeUiState.value = HomeUiState.Error(errorMessage)
            }.collect { messages ->
                if (messages.isEmpty()) {
                    _homeUiState.value = HomeUiState.NoMessages(tripId = tripId)
                } else {
                    _homeUiState.value =
                        HomeUiState.ChatMessages(messages = messages, tripId = tripId)
                }
            }
        }
    }

    private suspend fun createTripAndReturnId(): Long {
        val trip = createNewTripEntity(INIT_TRIP_NAME)
        val tripId = createTripUseCase(trip)
        val initMessage = createSystemMessage(tripId, getInitialMessage())
        sendMessageUseCase(initMessage)
        return tripId
    }

    private fun getCurrentTripId(): Long? {
        return when (val currentState = _homeUiState.value) {
            is HomeUiState.ChatMessages -> currentState.tripId
            is HomeUiState.NoMessages -> currentState.tripId
            else -> {
                val errorMessage = "Cannot perform action: No active session loaded."
                _homeUiState.value = HomeUiState.Error(errorMessage)
                Timber.e(errorMessage)
                null
            }
        }
    }

    private suspend fun sendUserMessageInternal(tripId: Long, messageText: String): Boolean {
        val userMessageEntity = createSenderMessage(tripId, messageText)
        return try {
            sendMessageUseCase(userMessageEntity)
            true
        } catch (e: Exception) {
            val errorMessage = "Failed to send user message: ${e.localizedMessage}"
            _homeUiState.value = HomeUiState.Error(errorMessage)
            Timber.e(e, errorMessage)
            false
        }
    }

    private suspend fun prepareFullPromptForGemini(
        tripId: Long,
        originalMessageText: String
    ): String? {
        val tripEntity = getTripByIdUseCase(tripId)
        if (tripEntity == null) {
            val errorMessage =
                "Failed to retrieve trip details for ID: $tripId. Cannot determine planning stage."
            Timber.e(errorMessage)
            val errorSystemMessage = createSystemMessage(
                tripId,
                "Error: Could not retrieve trip details to assist further."
            )
            sendMessageUseCase(errorSystemMessage)
            _homeUiState.value = HomeUiState.Error(errorMessage)
            return null
        }

        val planningStage = getTripPlanningStageUseCase(tripEntity)
        val contextHint = when (planningStage) {
            TripPlanningStage.STAGE_AWAITING_DESTINATION -> PROMPT_AWAITING_DESTINATION
            TripPlanningStage.STAGE_AWAITING_START_DATE -> PROMPT_AWAITING_START_DATE
            TripPlanningStage.STAGE_AWAITING_END_DATE -> PROMPT_AWAITING_END_DATE
            TripPlanningStage.STAGE_PLANNING_COMPLETE -> PROMPT_PLANNING_COMPLETE
        }

        return "${originalMessageText.trim()}\n\n[System Note: Current planning focus is on: $contextHint]"
    }

    private suspend fun generateAndSaveAiResponse(tripId: Long, fullPrompt: String) {
        val chatHistory = getMessagesForTripIdUseCase(tripId)
        val geminiResponseBuilder = StringBuilder()

        generateGeminiResponseUseCase(fullPrompt, chatHistory)
            .catch { e ->
                Timber.e(e, "Error generating Gemini response stream")
                val errorMessage =
                    "Sorry, I encountered an issue while generating a response. Please try again."
                val errorMessageEntity = createSystemMessage(tripId, errorMessage)
                sendMessageUseCase(errorMessageEntity)
            }
            .collect { response ->
                geminiResponseBuilder.append(response)
                Timber.tag("Gemini").d("Received part of response from Gemini: $response")
            }

        if (geminiResponseBuilder.isNotBlank()) {
            val finalAiText = geminiResponseBuilder.toString().trim()
            Timber.tag("Gemini").d("Final assembled response from Gemini: $finalAiText")
            val aiMessageEntity = createAiMessage(tripId, finalAiText)
            try {
                sendMessageUseCase(aiMessageEntity)
            } catch (e: Exception) {
                Timber.e(e, "Failed to send AI message to database")
                val systemErrorMessage =
                    createSystemMessage(tripId, "Error: Could not save the AI's response.")
                sendMessageUseCase(systemErrorMessage)
                _homeUiState.value =
                    HomeUiState.Error("Failed to save AI response: ${e.localizedMessage}")
            }
        } else {
            Timber.w("Gemini response was empty or only whitespace for prompt: $fullPrompt")
            val emptyResponseMessage = createSystemMessage(
                tripId,
                "I received an empty response. Could you try rephrasing or ask something else?"
            )
            sendMessageUseCase(emptyResponseMessage)
        }
    }


    internal fun sendMessage(messageText: String) {
        val tripId = getCurrentTripId() ?: return

        viewModelScope.launch {
            if (!sendUserMessageInternal(tripId, messageText.trim())) {
                return@launch
            }

            val fullPrompt = messageText.trim()

            generateAndSaveAiResponse(tripId, fullPrompt)
        }
    }

    private suspend fun fetchLatestTrip(): Long? {
        return try {
            val latestTripEntity = getLatestTripUseCase().firstOrNull()
            if (latestTripEntity != null) {
                this.latestTripId = latestTripEntity.id
                Timber.d("Latest trip found: ${latestTripEntity.name}, ID: ${latestTripEntity.id}")
                latestTripEntity.id
            } else {
                this.latestTripId = null
                Timber.d("No latest trip found.")
                null
            }
        } catch (e: Exception) {
            Timber.e(e, "Error fetching latest trip")
            this.latestTripId = null
            null
        }
    }
}
