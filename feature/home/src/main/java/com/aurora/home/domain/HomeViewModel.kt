package com.aurora.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    internal var uiState = MutableStateFlow<UiState>(UiState.EmptyState)
        private set

    init {
        start()
    }

    private fun start() {
        viewModelScope.launch {
            uiState.emit(UiState.HomeState)
        }
    }
}