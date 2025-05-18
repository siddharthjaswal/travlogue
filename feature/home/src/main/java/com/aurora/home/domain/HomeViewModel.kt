package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.domain.usecase.message.GetMessagesForSessionUseCase
import com.sid.domain.usecase.message.SendMessageUseCase
import com.sid.domain.usecase.session.GetOrCreateActiveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOrCreateActiveSessionUseCase: GetOrCreateActiveSessionUseCase,
    private val getMessagesForSessionUseCase: GetMessagesForSessionUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {
    internal var uiState = MutableStateFlow<UiState>(UiState.Empty)
        private set

    private var currentActiveSessionId: Long? = null

    init {
        loadInitialSessionAndMessages()
    }

    private fun loadInitialSessionAndMessages(tripId: Long? = null){
        viewModelScope.launch {
            uiState.value = UiState.Loading
        }
    }

    internal fun sendMessage(messageText: String) {

    }
}