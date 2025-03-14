package com.aurora.travlogue

import android.app.Application
import com.aurora.data.constants.GOOGLE_PLACES_KEY
import com.google.android.libraries.places.api.Places
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        FirebaseApp.initializeApp(this)

        setFirebaseRemoteFlag()

        initializePlacesApi()

    }

    private fun initializePlacesApi() {
        val apiKey = FirebaseRemoteConfig.getInstance().getString(GOOGLE_PLACES_KEY)
        if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
            Timber.e("Places API Error: No api key")
            return
        }

        // Initialize the SDK
        Places.initializeWithNewPlacesApiEnabled(this, apiKey)
    }

    private fun setFirebaseRemoteFlag() {

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 36/*3600*/
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
    }
}