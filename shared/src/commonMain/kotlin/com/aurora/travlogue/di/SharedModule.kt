package com.aurora.travlogue.di

import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.TravlogueDb
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.TravlogueApiClient
import com.aurora.travlogue.core.data.remote.createHttpClient
import com.aurora.travlogue.core.data.repository.ActivitySyncRepository
import com.aurora.travlogue.core.data.repository.BookingSyncRepository
import com.aurora.travlogue.core.data.repository.IdMappingRepository
import com.aurora.travlogue.core.data.repository.TripDaySyncRepository
import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.data.repository.TripSyncRepository
import com.aurora.travlogue.core.domain.service.BookingSyncService
import com.aurora.travlogue.core.domain.service.GapDetectionService
import com.aurora.travlogue.core.domain.service.SyncService
import com.aurora.travlogue.core.domain.usecase.*
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripViewModel
import com.aurora.travlogue.feature.home.presentation.HomeViewModel
import com.aurora.travlogue.feature.mock.presentation.MockViewModel
import com.aurora.travlogue.feature.tripdetail.presentation.TripDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Creates shared KMP DI module with configurable API base URL
 *
 * @param apiBaseUrl Base URL for the Logbook API (e.g., "http://10.0.2.2:8000/api/v1" for local dev)
 */
fun createSharedModule(
    apiBaseUrl: String = "https://api.travlogue.in/api/v1"
) = module {
    // Database
    single { TravlogueDb(get()) }

    // Legacy API Client (to be phased out)
    single { createHttpClient(enableLogging = true) }
    single { TravlogueApiClient(get()) }

    // New Logbook API Client (integrated with backend)
    single {
        LogbookApiClient(
            tokenStorage = get(),
            baseUrl = apiBaseUrl,
            enableLogging = true,
            logger = getOrNull(named("ktorLogger")) // Platform-specific logger (Android uses AndroidKtorLogger)
        )
    }

    // Auth Manager
    single {
        AuthManager(
            googleAuthProvider = get(),
            tokenStorage = get(),
            apiClient = get()
        )
    }

    // Repositories
    single { TripRepository(get()) } // Local repository (legacy, for direct local access)
    single { IdMappingRepository(get()) } // ID mapping repository (UUID ↔ Int tracking)

    // Sync Repositories (NEW - preferred for all operations)
    single { TripSyncRepository(get(), get(), get(), get()) } // Trip sync (local + remote + ID mapping)
    single { TripDaySyncRepository(get(), get(), get(), get()) } // TripDay sync (bridges trips with days)
    single { ActivitySyncRepository(get(), get(), get(), get()) } // Activity sync (now with ID mapping)
    single { BookingSyncRepository(get(), get(), get(), get()) } // Booking sync (now with ID mapping)

    // Domain Services
    singleOf(::GapDetectionService)
    singleOf(::BookingSyncService)
    single { SyncService(get(), get(), get(), get(), get()) } // Full multi-entity sync: Trip/TripDay/Activity/Booking

    // Use Cases
    singleOf(::GetAllTripsUseCase)
    singleOf(::GetTripByIdUseCase)
    singleOf(::CreateTripUseCase)
    singleOf(::DeleteTripUseCase)
    singleOf(::DetectTripGapsUseCase)

    // ViewModels
    single { HomeViewModel(get(), get()) } // Updated to use TripSyncRepository + SyncService
    single { CreateTripViewModel(get()) } // Updated to use TripSyncRepository
    viewModelOf(::MockViewModel)

    // Factory ViewModel (requires tripId parameter)
    factory { (tripId: String) ->
        TripDetailViewModel(
            tripId = tripId,
            tripRepository = get(),
            activitySyncRepository = get(),
            bookingSyncRepository = get(),
            gapDetectionService = get(),
            bookingSyncService = get()
        )
    }
}

/**
 * Platform-specific module (expect/actual pattern)
 * Each platform provides its own DatabaseDriverFactory
 */
expect val platformModule: Module
