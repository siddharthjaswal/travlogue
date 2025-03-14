package com.aurora.home.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.widgets.places.getSearchSuggestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
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

    internal fun getSearchNearByPlacesByString(inputString: String, context: Context) {
        Timber.w("New Search for $inputString --------------------------------")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val suggestions = getSearchSuggestions(inputString, context)
                for (suggestion in suggestions) {
                    val fullText = suggestion.getFullText(null).toString()
                    val distanceMeters = suggestion.distanceMeters
                    Timber.d("Place: $fullText, Distance: $distanceMeters meters")
                }
                Timber.w("End Search--------------------------------------------------")
            } catch (e: Exception) {
                Timber.d("Place not found: ${e.toString()}")
            }
        }
    }
}