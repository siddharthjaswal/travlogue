package com.aurora.travlogue.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aurora.travlogue.feature.createtrip.presentation.CreateTripScreen
import com.aurora.travlogue.feature.home.presentation.HomeScreen
import com.aurora.travlogue.feature.mock.presentation.MockScreen
import com.aurora.travlogue.feature.signin.presentation.SignInScreen
import com.aurora.travlogue.feature.tripdetail.presentation.TripDetailScreen

/**
 * Main navigation host for the app
 * Uses type-safe navigation with Kotlin Serialization
 *
 * @param navController The navigation controller for managing navigation
 * @param startDestination The initial destination (defaults to Home, can be SignIn for unauthenticated users)
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestination = Home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination // Auth-aware start destination
    ) {
        // Home screen - no arguments
        composable<Home> {
            HomeScreen(
                onNavigateToCreateTrip = {
                    navController.navigate(CreateTrip)
                },
                onNavigateToPlan = { tripId ->
                    // Type-safe navigation to trip detail
                    navController.navigate(TripDetail(tripId = tripId))
                },
                onNavigateToMock = {
                    navController.navigate(Mock)
                }
            )
        }

        // Create Trip screen - full screen form
        composable<CreateTrip> {
            CreateTripScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Trip detail screen
        composable<TripDetail> { backStackEntry ->
            val tripDetail = backStackEntry.toRoute<TripDetail>()
            TripDetailScreen(
                tripId = tripDetail.tripId,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Mock data generator screen - testing tool
        composable<Mock> {
            MockScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToTrip = { tripId ->
                    navController.navigate(TripDetail(tripId = tripId))
                }
            )
        }

        // Sign in screen - Google OAuth authentication
        composable<SignIn> {
            SignInScreen(
                onSignInSuccess = {
                    // Navigate to home and clear back stack
                    navController.navigate(Home) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // Future: Trip detail screen (old comment - now implemented above)
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
