package com.aurora.travlogue.di

import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.service.BookingSyncService
import com.aurora.travlogue.core.domain.service.GapDetectionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import javax.inject.Singleton

/**
 * Hilt module that bridges KMP shared module dependencies (provided by Koin)
 * to Hilt-injected Android ViewModels during the gradual migration.
 */
@Module
@InstallIn(SingletonComponent::class)
object SharedModule : KoinComponent {

    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository {
        val repository: TripRepository by inject()
        return repository
    }

    @Provides
    @Singleton
    fun provideGapDetectionService(): GapDetectionService {
        val service: GapDetectionService by inject()
        return service
    }

    @Provides
    @Singleton
    fun provideBookingSyncService(): BookingSyncService {
        val service: BookingSyncService by inject()
        return service
    }
}
