package com.aurora.travlogue.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aurora.genesis.domain.GenesisViewModel
import com.aurora.genesis.ui.GenesisScreen
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.ui.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
    ) {
        composable(route = Home.route) {
            HomeScreen(
                viewModel = hiltViewModel<HomeViewModel>(),
                onNavigateToPlan = {
                    navController.navigateSingleTopTo(Genesis.route)
                }
            )
        }
        composable(route = Genesis.route) {
            GenesisScreen(viewModel = hiltViewModel<GenesisViewModel>(), onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }