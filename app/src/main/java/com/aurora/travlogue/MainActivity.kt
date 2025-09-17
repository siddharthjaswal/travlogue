package com.aurora.travlogue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.aurora.travlogue.core.AppTheme
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

    val context = LocalActivity.current as ComponentActivity
    context.enableEdgeToEdge()


}
