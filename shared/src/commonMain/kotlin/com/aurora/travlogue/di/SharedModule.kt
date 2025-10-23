package com.aurora.travlogue.di

import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.TravlogueDb
import com.aurora.travlogue.core.data.remote.TravlogueApiClient
import com.aurora.travlogue.core.data.remote.createHttpClient
import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.service.BookingSyncService
import com.aurora.travlogue.core.domain.service.GapDetectionService
import com.aurora.travlogue.core.domain.usecase.*
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripViewModel
import com.aurora.travlogue.feature.home.presentation.HomeViewModel
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

    // API Client
    single { createHttpClient(enableLogging = true) }
    single { TravlogueApiClient(get()) }

    // Repositories
    single { TripRepository(get()) }

    // Domain Services
    singleOf(::GapDetectionService)
    singleOf(::BookingSyncService)

    // Use Cases
    singleOf(::GetAllTripsUseCase)
    singleOf(::GetTripByIdUseCase)
    singleOf(::CreateTripUseCase)
    singleOf(::DeleteTripUseCase)
    singleOf(::DetectTripGapsUseCase)

    // ViewModels
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateTripViewModel)
}

/**
 * Platform-specific module (expect/actual pattern)
 * Each platform provides its own DatabaseDriverFactory
 */
expect val platformModule: Module
