package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.data.data.entity.MessageEntity
import com.sid.domain.usecase.gemini.GenerateGeminiResponseUseCase
import com.sid.domain.usecase.message.GetMessagesForSessionUseCase
import com.sid.domain.usecase.message.SendMessageUseCase
import com.sid.domain.usecase.session.GetOrCreateActiveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val SENDER_AI = "ai"
const val SENDER_USER = "user"


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOrCreateActiveSessionUseCase: GetOrCreateActiveSessionUseCase,
    private val getMessagesForSessionUseCase: GetMessagesForSessionUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val generateGeminiResponseUseCase: GenerateGeminiResponseUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    internal val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        loadInitialSessionAndMessages()
    }

    private fun loadInitialSessionAndMessages() {
        viewModelScope.launch {
            _homeUiState.value = HomeUiState.Loading
            val sessionId = getOrCreateActiveSessionUseCase()
            getMessagesForSessionUseCase(sessionId)
                .catch { e ->
                    _homeUiState.value =
                        HomeUiState.Error("Failed to load messages: ${e.localizedMessage}")
                }
                .collect { messages ->
                    if (messages.isEmpty()) {
                        _homeUiState.value = HomeUiState.NoMessages(currentSessionId = sessionId)
                    } else {
                        _homeUiState.value = HomeUiState.ChatMessages(
                            messages = messages,
                            currentSessionId = sessionId
                        )
                    }
                }

        }
    }

    internal fun sendMessage(messageText: String) {
        val currentLoadedState = homeUiState.value

        val sessionId: Long? = when (currentLoadedState) {
            is HomeUiState.ChatMessages -> currentLoadedState.currentSessionId
            is HomeUiState.NoMessages -> currentLoadedState.currentSessionId
            else -> {
                _homeUiState.value =
                    HomeUiState.Error("Cannot send message: No active session loaded.")
                return
            }
        }

        if (sessionId == null) return

        val userMessageEntity = MessageEntity(
            sessionId = sessionId,
            sender = SENDER_USER,
            timestamp = System.currentTimeMillis(),
            content = messageText.trim()
        )

        viewModelScope.launch {
            try {
                sendMessageUseCase(userMessageEntity)
            } catch (e: Exception) {
                _homeUiState.value =
                    HomeUiState.Error("Failed to send message: ${e.localizedMessage}")
            }

            val geminiResponseText = generateGeminiResponseUseCase(messageText.trim())
            if (geminiResponseText != null) {
                val aiMessageEntity = MessageEntity(
                    sessionId = sessionId,
                    sender = SENDER_AI,
                    timestamp = System.currentTimeMillis(),
                    content = geminiResponseText
                )
                sendMessageUseCase(aiMessageEntity)
            } else {
                val errorMessageEntity = MessageEntity(
                    sessionId = sessionId,
                    sender = SENDER_AI,
                    timestamp = System.currentTimeMillis(),
                    content = "Sorry, I couldn't get a response. Please try again."
                )
                sendMessageUseCase(errorMessageEntity)
                Timber.w("Gemini response was null for prompt: $messageText")
            }


        }
    }

    /**
     * Call this if the user explicitly wants to start a new chat session,
     * potentially associated with a specific trip in the future.
     * For now, assuming it just reloads/re-establishes the general (null tripId) session
     * or creates a new one if needed.
     */
    internal fun startNewChatSession() {
        // This will re-run the logic to get or create a session based on the tripId.
        // If tripId is null, it will fetch/create the general session.
        // If a new tripId is provided, it would create/fetch for that specific trip.
        loadInitialSessionAndMessages()
    }
}