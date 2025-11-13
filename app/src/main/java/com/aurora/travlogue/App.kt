package com.aurora.travlogue

import android.app.Application
import com.aurora.travlogue.core.sync.SyncScheduler
import com.aurora.travlogue.di.androidAppModule
import com.aurora.travlogue.di.platformModule
import com.aurora.travlogue.di.sharedModule
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    private val syncScheduler: SyncScheduler by inject()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize Koin for KMP shared module
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(platformModule, sharedModule, androidAppModule)
        }

        FirebaseApp.initializeApp(this)

        setFirebaseRemoteFlag()

        initializePlacesApi()

        // Initialize background sync
        initializeBackgroundSync()
    }

    /**
     * Schedule periodic background sync
     * Runs every 6 hours when network is available and battery is not low
     */
    private fun initializeBackgroundSync() {
        Timber.d("Initializing background sync")
        syncScheduler.schedulePeriodicSync(
            intervalHours = 6,
            requireWifi = false,    // Sync on any network
            requireCharging = false  // Sync even when not charging
        )
        Timber.i("Background sync initialized successfully")
    }

    private fun initializePlacesApi() {
//        val apiKey = FirebaseRemoteConfig.getInstance().getString(GOOGLE_PLACES_KEY)
//        if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
//            Timber.e("Places API Error: No api key")
//            return
//        }

        // Initialize the SDK
//        Places.initializeWithNewPlacesApiEnabled(this, apiKey)
    }

    private fun setFirebaseRemoteFlag() {

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 36
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
    }
}