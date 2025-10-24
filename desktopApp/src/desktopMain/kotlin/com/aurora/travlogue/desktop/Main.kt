package com.aurora.travlogue.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {
    // Initialize Koin
    startKoin {
        modules(sharedModule)
    }

    val windowState = rememberWindowState(
        size = DpSize(1200.dp, 800.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Travlogue - Travel Planning Made Easy",
        state = windowState
    ) {
        TravlogueApp()
    }
}
