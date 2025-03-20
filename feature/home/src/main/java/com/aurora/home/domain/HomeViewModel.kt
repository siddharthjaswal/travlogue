package com.aurora.home.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.widgets.places.getSearchSuggestions
import com.google.android.libraries.places.api.model.AutocompletePrediction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    internal var uiState = MutableStateFlow<UiState>(UiState.EmptyState)
        private set

    private val _suggestions = MutableStateFlow<List<AutocompletePrediction>>(emptyList())
    val suggestions: StateFlow<List<AutocompletePrediction>> get() = _suggestions.asStateFlow()

    init {
        start()
    }

    private fun start() {
        viewModelScope.launch {
            uiState.emit(UiState.HomeState)
        }
    }

    internal fun getSearchNearByPlacesByString(inputString: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val suggestions = getSearchSuggestions(inputString, context)
                _suggestions.value = suggestions
            } catch (e: Exception) {
                //Timber.d("Place not found: ${e.toString()}")
            }
        }
    }
}