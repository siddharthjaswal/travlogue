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
import org.koin.dsl.module

/**
 * Koin DI module for shared KMP code
 */
val sharedModule = module {
    // Database
    single { TravlogueDb(get()) }

    // Legacy API Client (to be phased out)
    single { createHttpClient(enableLogging = true) }
    single { TravlogueApiClient(get()) }

    // New Logbook API Client (integrated with backend)
    single {
        LogbookApiClient(
            tokenStorage = get(),
            enableLogging = true
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
    single { ActivitySyncRepository(get(), get(), get(), get()) } // Activity sync (now with ID mapping)
    single { BookingSyncRepository(get(), get(), get(), get()) } // Booking sync (now with ID mapping)

    // Domain Services
    singleOf(::GapDetectionService)
    singleOf(::BookingSyncService)
    single { SyncService(get(), get()) } // New sync service

    // Use Cases
    singleOf(::GetAllTripsUseCase)
    singleOf(::GetTripByIdUseCase)
    singleOf(::CreateTripUseCase)
    singleOf(::DeleteTripUseCase)
    singleOf(::DetectTripGapsUseCase)

    // ViewModels
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateTripViewModel)
    viewModelOf(::MockViewModel)

    // Factory ViewModel (requires tripId parameter)
    factory { (tripId: String) ->
        TripDetailViewModel(
            tripId = tripId,
            tripRepository = get(),
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
