package com.aurora.travlogue.desktop

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aurora.travlogue.desktop.ui.screens.CreateTripScreen
import com.aurora.travlogue.desktop.ui.screens.HomeScreen
import com.aurora.travlogue.desktop.ui.screens.MockScreen
import com.aurora.travlogue.desktop.ui.screens.TripDetailScreen

/**
 * Sealed class representing all possible navigation destinations
 */
sealed class Screen {
    data object Home : Screen()
    data object CreateTrip : Screen()
    data object Mock : Screen()
    data class TripDetail(val tripId: String) : Screen()
}

/**
 * Main navigation composable for the desktop app
 */
@Composable
fun TravlogueApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> {
            HomeScreen(
                onNavigateToCreateTrip = { currentScreen = Screen.CreateTrip },
                onNavigateToMock = { currentScreen = Screen.Mock },
                onNavigateToTripDetail = { tripId -> currentScreen = Screen.TripDetail(tripId) }
            )
        }
        is Screen.CreateTrip -> {
            CreateTripScreen(
                onNavigateBack = { currentScreen = Screen.Home }
            )
        }
        is Screen.Mock -> {
            MockScreen(
                onNavigateBack = { currentScreen = Screen.Home },
                onNavigateToTripDetail = { tripId -> currentScreen = Screen.TripDetail(tripId) }
            )
        }
        is Screen.TripDetail -> {
            TripDetailScreen(
                tripId = screen.tripId,
                onNavigateBack = { currentScreen = Screen.Home }
            )
        }
    }
}
