package com.aurora.travlogue

import android.app.Application
import androidx.lifecycle.lifecycleScope
import com.aurora.travlogue.core.preferences.AppPreferences
import com.aurora.travlogue.core.sync.SyncScheduler
import com.aurora.travlogue.di.androidAppModule
import com.aurora.travlogue.di.platformModule
import com.aurora.travlogue.di.createSharedModule
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    private val syncScheduler: SyncScheduler by inject()
    private val appPreferences: AppPreferences by inject()

    // Application scope for coroutines
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize Koin for KMP shared module
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            // Pass API base URL from BuildConfig (debug = production, release = production)
            // The Android-specific Ktor logger is provided by platformModule
            modules(platformModule, createSharedModule(BuildConfig.API_BASE_URL), androidAppModule)
        }

        FirebaseApp.initializeApp(this)

        setFirebaseRemoteFlag()

        initializePlacesApi()

        // Initialize background sync
        initializeBackgroundSync()
    }

    /**
     * Schedule periodic background sync using user preferences
     * Default: 6 hours when network is available and battery is not low
     */
    private fun initializeBackgroundSync() {
        Timber.d("Initializing background sync")

        applicationScope.launch {
            // Read preferences
            val intervalHours = appPreferences.getSyncIntervalHours()
            val requireWifi = appPreferences.getRequireWifiForSync()
            val requireCharging = appPreferences.getRequireChargingForSync()

            Timber.d("Sync config: interval=${intervalHours}h, wifi=$requireWifi, charging=$requireCharging")

            // Schedule sync with user preferences
            syncScheduler.schedulePeriodicSync(
                intervalHours = intervalHours,
                requireWifi = requireWifi,
                requireCharging = requireCharging
            )

            Timber.i("Background sync initialized successfully")
        }
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