package com.aurora.genesis.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.domain.usecase.trip.GetLatestTripUseCase
import com.sid.domain.usecase.trip.GetTripByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenesisViewModel @Inject constructor(
    private val getLatestTripUseCase: GetLatestTripUseCase,
    private val getTripByIdUseCase: GetTripByIdUseCase,
) : ViewModel() {

    internal var uiState = MutableStateFlow<UiState>(UiState.EmptyState)
        private set

    init {
        start()
    }

    internal fun start() {
        viewModelScope.launch {
            val trip = getLatestTripUseCase().firstOrNull()

            if (trip != null) {
                uiState.emit(UiState.GetTimelinesState(trip))
            } else {
                uiState.emit(UiState.EmptyState)
            }
        }
    }
}