package com.aurora.travlogue.di

import com.aurora.travlogue.core.auth.AndroidGoogleAuthProvider
import com.aurora.travlogue.core.auth.GoogleAuthProvider
import com.aurora.travlogue.core.data.local.AndroidTokenStorage
import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.TokenStorage
import org.koin.dsl.module

/**
 * Android-specific Koin module
 */
actual val platformModule = module {
    single { DatabaseDriverFactory(get()) }

    // Token Storage
    single<TokenStorage> { AndroidTokenStorage(get()) }

    // Google Auth Provider
    single<GoogleAuthProvider> {
        AndroidGoogleAuthProvider(
            context = get(),
            serverClientId = "YOUR_GOOGLE_CLIENT_ID" // TODO: Move to BuildConfig or settings
        )
    }
}
