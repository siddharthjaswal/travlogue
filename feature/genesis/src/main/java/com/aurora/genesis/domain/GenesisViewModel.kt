package com.aurora.genesis.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenesisViewModel @Inject constructor() : ViewModel() {

    internal var uiState = MutableStateFlow<UiState>(UiState.EmptyState)
        private set

    internal fun start() {
        Timber.w("Start")
        viewModelScope.launch {
            uiState.emit(UiState.GetTimelinesState)
        }
    }

    internal fun nextStep(){
        Timber.w("nextStep")
        viewModelScope.launch {
            uiState.emit(UiState.EmptyState)
            Timber.w("4")
        }
    }
}