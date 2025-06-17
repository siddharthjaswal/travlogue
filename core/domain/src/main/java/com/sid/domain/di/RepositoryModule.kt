package com.sid.domain.di

import com.sid.domain.repository.day.DayRepository
import com.sid.domain.repository.day.DayRepositoryImpl
import com.sid.domain.repository.gemini.GeminiRepository
import com.sid.domain.repository.gemini.GeminiRepositoryImpl
import com.sid.domain.repository.message.MessageRepository
import com.sid.domain.repository.message.MessageRepositoryImpl
import com.sid.domain.repository.storage.ImageStorageRepository
import com.sid.domain.repository.storage.ImageStorageRepositoryImpl
import com.sid.domain.repository.trip.TripRepository
import com.sid.domain.repository.trip.TripRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMessageRepository(
        messageRepositoryImpl: MessageRepositoryImpl
    ): MessageRepository

    @Binds
    @Singleton
    abstract fun bindGeminiRepository(
        geminiRepositoryImpl: GeminiRepositoryImpl
    ): GeminiRepository

    @Binds
    @Singleton
    abstract fun bindTripRepository(
        tripRepositoryImpl: TripRepositoryImpl
    ): TripRepository

    @Binds
    @Singleton
    abstract fun bindImageStorageRepository(
        imageStorageRepositoryImpl: ImageStorageRepositoryImpl
    ): ImageStorageRepository

    @Binds
    @Singleton
    abstract fun bindDayRepository(
        dayRepositoryImpl: DayRepositoryImpl
    ): DayRepository
}