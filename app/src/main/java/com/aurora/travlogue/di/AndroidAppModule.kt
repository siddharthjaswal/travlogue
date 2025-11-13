package com.aurora.travlogue.di

import com.aurora.travlogue.core.sync.NetworkConnectivityMonitor
import com.aurora.travlogue.core.sync.SyncScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Android-specific dependencies for the app module
 * Includes background sync, network monitoring, etc.
 */
val androidAppModule = module {
    // Network Connectivity Monitor
    single { NetworkConnectivityMonitor(androidContext()) }

    // Sync Scheduler for WorkManager
    single { SyncScheduler(androidContext()) }
}
