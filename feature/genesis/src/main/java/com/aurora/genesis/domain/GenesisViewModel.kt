package com.aurora.genesis.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenesisViewModel @Inject constructor() : ViewModel() {

    internal var uiState = MutableStateFlow<UiState>(UiState.EmptyState)
        private set

    init {
        start()
    }

    internal fun start() {
        viewModelScope.launch {
            uiState.emit(UiState.GetTimelinesState)
        }
    }
}