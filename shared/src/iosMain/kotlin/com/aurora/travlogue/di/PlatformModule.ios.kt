package com.aurora.travlogue.di

import com.aurora.travlogue.core.auth.GoogleAuthProvider
import com.aurora.travlogue.core.auth.IOSGoogleAuthProvider
import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.IOSTokenStorage
import com.aurora.travlogue.core.data.local.TokenStorage
import org.koin.dsl.module

/**
 * iOS-specific Koin module
 */
actual val platformModule = module {
    single { DatabaseDriverFactory() }

    // Token Storage
    single<TokenStorage> { IOSTokenStorage() }

    // Google Auth Provider
    single<GoogleAuthProvider> { IOSGoogleAuthProvider() }
}
