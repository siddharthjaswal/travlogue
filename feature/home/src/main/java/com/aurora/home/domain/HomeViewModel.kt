package com.aurora.home.domain

// ... other imports ...
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.sid.domain.usecase.gemini.GenerateGeminiResponseUseCase
import com.sid.domain.usecase.message.GetMessagesForSessionUseCase
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
    private val getMessagesForSessionUseCase: GetMessagesForSessionUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val generateGeminiResponseUseCase: GenerateGeminiResponseUseCase,
    private val createTripUseCase: CreateTripUseCase,
    private val getLatestTripUseCase: GetLatestTripUseCase,
    private val getTripPlanningStageUseCase: GetTripPlanningStageUseCase,
    private val getTripByIdUseCase: GetTripByIdUseCase
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

            getMessagesForSessionUseCase(tripId).catch { e ->
                val errorMessage = "Failed to load messages: ${e.localizedMessage}"
                _homeUiState.value = HomeUiState.Error(errorMessage)
            }.collect { messages ->
                if (messages.isEmpty()) {
                    _homeUiState.value = HomeUiState.NoMessages(tripId = tripId)
                } else {
                    _homeUiState.value = HomeUiState.ChatMessages(
                        messages = messages,
                        tripId = tripId
                    )
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

    internal fun sendMessage(messageText: String) {
        val tripId =
            getCurrentTripId() ?: return

        val userMessageEntity = createSenderMessage(tripId, messageText.trim())

        viewModelScope.launch {
            val tripEntity = getTripByIdUseCase(tripId)
            if (tripEntity == null) {
                val errorMessage = "Failed to retrieve trip details for ID: $tripId"
                _homeUiState.value = HomeUiState.Error(errorMessage)
                Timber.e(errorMessage)
                return@launch
            }

            val planningStage = getTripPlanningStageUseCase(tripEntity)

            val additionalPrompt = when (planningStage) {
                TripPlanningStage.STAGE_AWAITING_DESTINATION -> PROMPT_AWAITING_DESTINATION
                TripPlanningStage.STAGE_AWAITING_START_DATE -> PROMPT_AWAITING_START_DATE
                TripPlanningStage.STAGE_AWAITING_END_DATE -> PROMPT_AWAITING_END_DATE
                TripPlanningStage.STAGE_PLANNING_COMPLETE -> PROMPT_PLANNING_COMPLETE
            }

            try {
                sendMessageUseCase(userMessageEntity)
            } catch (e: Exception) {
                val errorMessage = "Failed to send message: ${e.localizedMessage}"
                _homeUiState.value = HomeUiState.Error(errorMessage)
            }

            val geminiResponseText =
                generateGeminiResponseUseCase("${messageText.trim()} $additionalPrompt")
            if (geminiResponseText != null) {
                val aiMessageEntity = createAiMessage(tripId, geminiResponseText)
                sendMessageUseCase(aiMessageEntity)
            } else {
                val errorMessage = "Sorry, I couldn't get a response. Please try again."
                val errorMessageEntity = createSystemMessage(tripId, errorMessage)
                sendMessageUseCase(errorMessageEntity)
                Timber.w("Gemini response was null for prompt: $messageText")
            }
        }
    }

    /**
     * Fetches the latest trip, updates [latestTripId], and returns its ID or null.
     */
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
