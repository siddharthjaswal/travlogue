package com.aurora.home.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
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
        val placesClient = Places.createClient(context)

        viewModelScope.launch(Dispatchers.IO) {
            val autocompletePlacesRequest: FindAutocompletePredictionsRequest =
                FindAutocompletePredictionsRequest.builder()
                    .setQuery("København H")
                    .setRegionCode("ES")
                    .build()

            placesClient.findAutocompletePredictions(autocompletePlacesRequest)
                .addOnSuccessListener { response ->
                    val placeSuggestions = response.autocompletePredictions
                    Timber.d("list of suggestions: $placeSuggestions")
                }.addOnFailureListener { exception ->
                    Timber.d("Place not found: ${exception.toString()}")
                }
        }

    }
}