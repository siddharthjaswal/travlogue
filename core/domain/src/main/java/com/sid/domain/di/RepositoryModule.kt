package com.sid.domain.di

import com.sid.domain.repository.gemini.GeminiRepository
import com.sid.domain.repository.gemini.GeminiRepositoryImpl
import com.sid.domain.repository.message.MessageRepository
import com.sid.domain.repository.message.MessageRepositoryImpl
import com.sid.domain.repository.session.ChatRepository
import com.sid.domain.repository.session.ChatRepositoryImpl
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
    abstract fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

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
}