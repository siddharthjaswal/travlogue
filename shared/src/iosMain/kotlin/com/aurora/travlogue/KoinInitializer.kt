package com.aurora.travlogue

import com.aurora.travlogue.di.platformModule
import com.aurora.travlogue.di.sharedModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(platformModule + sharedModule)
    }
}
