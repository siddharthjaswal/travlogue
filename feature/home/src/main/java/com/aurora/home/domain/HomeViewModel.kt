package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.data.data.entity.day.createDayEntity
import com.aurora.data.data.entity.message.createAiMessage
import com.aurora.data.data.entity.message.createSenderMessage
import com.aurora.data.data.entity.message.createSystemMessage
import com.aurora.data.data.entity.message.getInitialMessage
import com.aurora.data.data.entity.trip.INIT_TRIP_NAME
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_DESTINATION
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_END_DATE
import com.aurora.data.data.entity.trip.PROMPT_AWAITING_START_DATE
import com.aurora.data.data.entity.trip.PROMPT_PLANNING_COMPLETE
import com.aurora.data.data.entity.trip.TripPlanningStage
import com.aurora.data.data.entity.trip.createNewTripEntity
import com.sid.domain.usecase.day.CreateDayUseCase
import com.sid.domain.usecase.day.GetDaysForTripUseCase
import com.sid.domain.usecase.gemini.GenerateBannerImageUseCase
import com.sid.domain.usecase.gemini.GenerateGeminiResponseUseCase
import com.sid.domain.usecase.gemini.GenerateTripJsonUseCase
import com.sid.domain.usecase.message.GetMessagesFlowForTripIdUseCase
import com.sid.domain.usecase.message.GetMessagesForTripIdUseCase
import com.sid.domain.usecase.message.SendMessageUseCase
import com.sid.domain.usecase.trip.CreateTripUseCase
import com.sid.domain.usecase.trip.GetLatestTripUseCase
import com.sid.domain.usecase.trip.GetTripByIdUseCase
import com.sid.domain.usecase.trip.GetTripPlanningStageUseCase
import com.sid.domain.usecase.trip.UpdateTripUseCase
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
    private val createTripUseCase: CreateTripUseCase,
    private val getLatestTripUseCase: GetLatestTripUseCase,
    private val getTripPlanningStageUseCase: GetTripPlanningStageUseCase,
    private val generateGeminiResponseUseCase: GenerateGeminiResponseUseCase,
    private val generateBannerImageUseCase: GenerateBannerImageUseCase,
    private val getTripByIdUseCase: GetTripByIdUseCase,
    private val generateTripJsonUseCase: GenerateTripJsonUseCase,
    private val updateTripUseCase: UpdateTripUseCase,
    private val getDaysForTripUseCase: GetDaysForTripUseCase,
    private val createDayUseCase: CreateDayUseCase
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
        tripId: Long, originalMessageText: String
    ): String? {
        val tripEntity = getTripByIdUseCase(tripId)
        if (tripEntity == null) {
            val errorMessage =
                "Failed to retrieve trip details for ID: $tripId. Cannot determine planning stage."
            Timber.e(errorMessage)
            val errorSystemMessage = createSystemMessage(
                tripId, "Error: Could not retrieve trip details to assist further."
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

        generateGeminiResponseUseCase(fullPrompt, chatHistory).catch { e ->
            Timber.e(e, "Error generating Gemini response stream")
            val errorMessage =
                "Sorry, I encountered an issue while generating a response. Please try again."
            val errorMessageEntity = createSystemMessage(tripId, errorMessage)
            sendMessageUseCase(errorMessageEntity)
        }.collect { response ->
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

    private suspend fun generateAndSaveBannerImage(tripId: Long, bannerPrompt: String) {
        Timber.tag("Gemini")
            .i("Attempting to generate banner image for trip ID: $tripId with prompt: \"$bannerPrompt\"")
        val imagePath = generateBannerImageUseCase(bannerPrompt)

        if (imagePath != null) {
            Timber.tag("Gemini")
                .d("Banner image generated successfully for trip ID: $tripId. Path: $imagePath")
            val currentTrip = getTripByIdUseCase(tripId)
            if (currentTrip != null) {
                val updatedTrip = currentTrip.copy(
                    bannerImagePath = imagePath,
                    lastUpdated = System.currentTimeMillis()
                )
                if (updateTripUseCase(updatedTrip)) {
                    Timber.tag("Gemini")
                        .i("Successfully updated trip ID: $tripId with banner image path: $imagePath")
                } else {
                    Timber.tag("Gemini")
                        .e("Failed to update trip ID: $tripId with banner image path.")
                }
            } else {
                Timber.tag("Gemini")
                    .e("Failed to retrieve trip ID: $tripId to update banner image path.")
            }
        } else {
            Timber.tag("Gemini")
                .w("generateBannerImageUseCase returned null for trip ID: $tripId. No banner image generated or saved.")
        }
    }

    private suspend fun generateTripDataFromPrompt(tripId: Long, fullPrompt: String) {
        val trip = getTripByIdUseCase(tripId)
        val chatHistory = getMessagesForTripIdUseCase(tripId)

        if (trip != null && trip.name == INIT_TRIP_NAME) {
            Timber.tag("Gemini")
                .i("Attempting to generate and update trip data from prompt for trip ID: $tripId")
            val generatedTripData = generateTripJsonUseCase(
                prompt = fullPrompt,
                chatHistory = chatHistory
            )

            if (generatedTripData != null) {
                val tripToUpdate = trip.copy(
                    name = generatedTripData.name,
                    days = generatedTripData.days,
                    startDate = generatedTripData.startDate,
                    endDate = generatedTripData.endDate,
                    lastUpdated = System.currentTimeMillis()
                )


                if (updateTripUseCase(tripToUpdate)) {
                    Timber.tag("Gemini")
                        .d("Successfully updated trip with data from Gemini: $tripToUpdate")
                    generateAndSaveBannerImage(tripId, tripToUpdate.name)
                } else {
                    Timber.tag("Gemini")
                        .e("Failed to update trip with data from Gemini for trip ID: $tripId")
                }

                tripToUpdate.days?.let { d ->
                    for (day in 1..d) {
                        createDayUseCase(createDayEntity(tripId, day))
                        Timber.tag("Gemini").e("Created Day for trip ID: $tripId, Day: $day")
                    }
                }

            } else {
                Timber.tag("Gemini")
                    .w("generateTripJsonUseCase returned null for trip ID: $tripId. No trip data to update.")
            }
        } else {
            if (trip == null) {
                Timber.tag("Gemini")
                    .w("Cannot generate trip data: Original trip not found for ID: $tripId")
            } else { // trip.name is not INIT_TRIP_NAME
                Timber.tag("Gemini")
                    .i("Trip name is '${trip.name}', not '$INIT_TRIP_NAME'. Skipping trip data generation from prompt for trip ID: $tripId.")
            }
        }
    }

    internal fun sendMessage(messageText: String) {
        val tripId = getCurrentTripId() ?: return

        viewModelScope.launch {
            if (!sendUserMessageInternal(tripId, messageText.trim())) {
                return@launch
            }

            val userOriginalPrompt = messageText.trim()

            val contextualPromptForAi =
                prepareFullPromptForGemini(tripId, userOriginalPrompt) ?: return@launch
            generateAndSaveAiResponse(tripId, contextualPromptForAi)

            // This will now also attempt to generate a banner if trip data is successfully extracted and updated
            generateTripDataFromPrompt(tripId, userOriginalPrompt)
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
