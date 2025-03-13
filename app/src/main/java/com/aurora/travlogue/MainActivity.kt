package com.aurora.travlogue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.aurora.designsystem.theme.AppTheme
import com.aurora.travlogue.navigation.AppNavHost
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
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

    //Enable edge to edge
    val context = LocalActivity.current as ComponentActivity
    context.enableEdgeToEdge()

    //Fetch remote config
    setFirebaseRemoteFlag()
}

private fun setFirebaseRemoteFlag() {

    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }
    remoteConfig.setConfigSettingsAsync(configSettings)

    remoteConfig.fetchAndActivate()
}