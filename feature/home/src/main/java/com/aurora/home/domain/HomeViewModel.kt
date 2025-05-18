package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.data.data.entity.MessageEntity
import com.sid.domain.usecase.message.GetMessagesForSessionUseCase
import com.sid.domain.usecase.message.SendMessageUseCase
import com.sid.domain.usecase.session.GetOrCreateActiveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOrCreateActiveSessionUseCase: GetOrCreateActiveSessionUseCase,
    private val getMessagesForSessionUseCase: GetMessagesForSessionUseCase,
    private val sendMessageUseCase: SendMessageUseCase
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
                // Not in a state where we have a session ID (e.g., Loading, Error)
                // Or, if this happens, it indicates a logic flaw or unexpected state transition.
                _homeUiState.value =
                    HomeUiState.Error("Cannot send message: No active session loaded.")
                // Optionally, try to re-initialize session or guide user
                // loadInitialSessionAndMessages(null) // Attempt to re-establish a session
                return
            }
        }

        if (sessionId == null) return

        viewModelScope.launch {
            val messageEntity = MessageEntity(
                sessionId = sessionId,
                sender = "user",
                timestamp = System.currentTimeMillis(),
                content = messageText.trim()
            )
            try {
                sendMessageUseCase(messageEntity)
                // The Flow collected in loadInitialSessionAndMessages should automatically update the messages list.
                // No explicit UI state update here is needed if using Flows correctly from DAO -> Repo -> UseCase -> ViewModel.
            } catch (e: Exception) {
                // Handle potential errors from sendMessageUseCase (e.g., database write failure)
                // You might want to provide feedback to the user, e.g., by temporarily adding
                // the message to the UI with a "failed to send" status and a retry option.
                // For now, just updating to a general error state.
                _homeUiState.value =
                    HomeUiState.Error("Failed to send message: ${e.localizedMessage}")
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