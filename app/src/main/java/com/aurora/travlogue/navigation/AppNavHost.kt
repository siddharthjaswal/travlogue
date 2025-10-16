package com.aurora.travlogue.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aurora.travlogue.feature.home.presentation.HomeScreen
import com.aurora.travlogue.feature.home.presentation.HomeViewModel

/**
 * Main navigation host for the app
 * Uses type-safe navigation with Kotlin Serialization
 *
 * @param navController The navigation controller for managing navigation
 */
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home // Type-safe! No more strings
    ) {
        // Home screen - no arguments
        composable<Home> {
            HomeScreen(
                viewModel = hiltViewModel<HomeViewModel>(),
                onNavigateToPlan = { tripId ->
                    // Type-safe navigation to trip detail
                    navController.navigate(TripDetail(tripId = tripId))
                }
            )
        }

        // Future: Trip detail screen
        // composable<TripDetail> { backStackEntry ->
        //     val tripDetail = backStackEntry.toRoute<TripDetail>()
        //     TripDetailScreen(
        //         tripId = tripDetail.tripId,
        //         onNavigateBack = { navController.navigateUp() },
        //         onNavigateToPlan = { editMode ->
        //             navController.navigate(TripPlan(tripId = tripDetail.tripId, editMode = editMode))
        //         }
        //     )
        // }

        // Future: Trip plan screen
        // composable<TripPlan> { backStackEntry ->
        //     val tripPlan = backStackEntry.toRoute<TripPlan>()
        //     TripPlanScreen(
        //         tripId = tripPlan.tripId,
        //         editMode = tripPlan.editMode,
        //         onNavigateBack = { navController.navigateUp() }
        //     )
        // }
    }
}

/**
 * Navigate to a destination with single-top behavior
 * Prevents multiple copies of the same destination on the back stack
 *
 * @param T The destination type (must be a serializable data class or object)
 * @param destination The destination to navigate to
 */
inline fun <reified T : Any> NavHostController.navigateSingleTopTo(destination: T) =
    this.navigate(destination) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
