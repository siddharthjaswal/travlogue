package com.aurora.travlog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aurora.home.ui.HomeScreen
import com.aurora.onboarding.ui.OnboardingScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
    ) {
        composable(route = Home.route) {
            HomeScreen()
        }
        composable(route = Onboarding.route) {
            OnboardingScreen()
        }
    }
}