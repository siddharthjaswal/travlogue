package com.aurora.travlogue.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * App-wide preferences using DataStore
 * Manages user settings for sync, notifications, etc.
 */
class AppPreferences(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    /**
     * Sync interval in hours
     * Default: 6 hours
     */
    val syncIntervalHours: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[SYNC_INTERVAL_HOURS] ?: DEFAULT_SYNC_INTERVAL_HOURS
    }

    /**
     * Require WiFi for sync
     * Default: false (sync on any network)
     */
    val requireWifiForSync: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[REQUIRE_WIFI_FOR_SYNC] ?: false
    }

    /**
     * Require charging for sync
     * Default: false (sync even when not charging)
     */
    val requireChargingForSync: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[REQUIRE_CHARGING_FOR_SYNC] ?: false
    }

    /**
     * Update sync interval
     */
    suspend fun setSyncInterval(hours: Long) {
        context.dataStore.edit { preferences ->
            preferences[SYNC_INTERVAL_HOURS] = hours
        }
    }

    /**
     * Update WiFi requirement for sync
     */
    suspend fun setRequireWifiForSync(require: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[REQUIRE_WIFI_FOR_SYNC] = require
        }
    }

    /**
     * Update charging requirement for sync
     */
    suspend fun setRequireChargingForSync(require: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[REQUIRE_CHARGING_FOR_SYNC] = require
        }
    }

    /**
     * Get sync interval synchronously (for initial setup)
     */
    suspend fun getSyncIntervalHours(): Long {
        return context.dataStore.data.map { preferences ->
            preferences[SYNC_INTERVAL_HOURS] ?: DEFAULT_SYNC_INTERVAL_HOURS
        }.first()
    }

    suspend fun getRequireWifiForSync(): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[REQUIRE_WIFI_FOR_SYNC] ?: false
        }.first()
    }

    suspend fun getRequireChargingForSync(): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[REQUIRE_CHARGING_FOR_SYNC] ?: false
        }.first()
    }

    companion object {
        private val SYNC_INTERVAL_HOURS = longPreferencesKey("sync_interval_hours")
        private val REQUIRE_WIFI_FOR_SYNC = booleanPreferencesKey("require_wifi_for_sync")
        private val REQUIRE_CHARGING_FOR_SYNC = booleanPreferencesKey("require_charging_for_sync")

        const val DEFAULT_SYNC_INTERVAL_HOURS = 6L

        /**
         * Available sync interval options (in hours)
         */
        val SYNC_INTERVAL_OPTIONS = listOf(
            1L to "Every hour",
            3L to "Every 3 hours",
            6L to "Every 6 hours (recommended)",
            12L to "Every 12 hours",
            24L to "Once daily"
        )
    }
}

/**
 * Extension to get first value from Flow
 */
private suspend fun <T> Flow<T>.first(): T {
    var result: T? = null
    collect {
        result = it
        return@collect
    }
    return result!!
}
