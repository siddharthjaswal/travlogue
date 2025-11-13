package com.aurora.travlogue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.auth.AuthState
import com.aurora.travlogue.core.design.AppTheme
import com.aurora.travlogue.navigation.AppNavHost
import com.aurora.travlogue.navigation.Home
import com.aurora.travlogue.navigation.SignIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MainApp(
    authManager: AuthManager = koinInject()
) {
    val authState by authManager.authState.collectAsState()

    // Initialize auth manager on app start
    LaunchedEffect(Unit) {
        launch {
            authManager.initialize()
        }
    }

    AppTheme {
        val navController = rememberNavController()

        // Determine start destination based on auth state
        val startDestination = when (authState) {
            is AuthState.Authenticated -> Home
            is AuthState.Unauthenticated -> SignIn
            else -> SignIn // Loading or Error defaults to SignIn
        }

        AppNavHost(
            navController = navController,
            startDestination = startDestination
        )
    }

    val context = LocalActivity.current as ComponentActivity
    context.enableEdgeToEdge()
}
