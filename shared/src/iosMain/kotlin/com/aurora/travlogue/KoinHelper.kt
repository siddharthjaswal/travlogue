package com.aurora.travlogue

import com.aurora.travlogue.di.platformModule
import com.aurora.travlogue.di.sharedModule
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripViewModel
import com.aurora.travlogue.feature.home.presentation.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

/**
 * Helper class to access Koin dependencies from Swift/iOS
 */
class KoinHelper : KoinComponent {

    companion object {
        val shared = KoinHelper()

        fun initKoin() {
            startKoin {
                modules(platformModule + sharedModule)
            }
        }
    }

    fun getHomeViewModel(): HomeViewModel = get()

    fun getCreateTripViewModel(): CreateTripViewModel = get()
}
