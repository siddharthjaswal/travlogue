package com.aurora.widgets.places

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

suspend fun getSearchSuggestions(
    inputString: String,
    context: Context
): List<AutocompletePrediction> {
    val placesClient = Places.createClient(context)

    // Ensure the input string is valid
    if (inputString.length <= 2) {
        return emptyList()
    }

    // Build the autocomplete request
    val autocompletePlacesRequest = FindAutocompletePredictionsRequest.builder()
        .setQuery(inputString)
        .setRegionCode("ES")
        .build()

    // Use suspendCancellableCoroutine to bridge the callback-based API with coroutines
    return suspendCancellableCoroutine { continuation ->
        placesClient.findAutocompletePredictions(autocompletePlacesRequest)
            .addOnSuccessListener { response ->
                val suggestions = response.autocompletePredictions
                continuation.resumeWith(runCatching {
                    suggestions
                })
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception) // Resume with an error
            }
    }
}