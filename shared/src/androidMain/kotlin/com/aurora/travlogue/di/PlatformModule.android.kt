package com.aurora.travlogue.di

import com.aurora.travlogue.shared.BuildConfig
import com.aurora.travlogue.core.auth.AndroidGoogleAuthProvider
import com.aurora.travlogue.core.auth.GoogleAuthProvider
import com.aurora.travlogue.core.data.local.AndroidTokenStorage
import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.TokenStorage
import com.aurora.travlogue.core.data.remote.AndroidKtorLogger
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Android-specific Koin module
 */
actual val platformModule = module {
    single { DatabaseDriverFactory(get()) }

    // Token Storage
    single<TokenStorage> { AndroidTokenStorage(get()) }

    // Ktor Logger (Android-specific for HTTP logging)
    single(named("ktorLogger")) { AndroidKtorLogger() as io.ktor.client.plugins.logging.Logger }

    // Google Auth Provider (concrete type)
    single {
        AndroidGoogleAuthProvider(
            context = get(),
            serverClientId = BuildConfig.GOOGLE_OAUTH_CLIENT_ID,
            apiClient = get() // LogbookApiClient for backend auth
        )
    }

    // Google Auth Provider (interface binding)
    single<GoogleAuthProvider> { get<AndroidGoogleAuthProvider>() }
}
