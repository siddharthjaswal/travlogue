package com.aurora.travlogue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.aurora.designsystem.theme.AppTheme
import com.aurora.travlogue.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

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
fun MainApp() {
    AppTheme {
        val navController = rememberNavController()
            AppNavHost(
                navController = navController
            )
    }
}