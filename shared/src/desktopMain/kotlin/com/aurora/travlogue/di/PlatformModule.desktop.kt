package com.aurora.travlogue.di

import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import org.koin.dsl.module

/**
 * Desktop-specific Koin module
 */
actual val platformModule = module {
    single { DatabaseDriverFactory() }
}
