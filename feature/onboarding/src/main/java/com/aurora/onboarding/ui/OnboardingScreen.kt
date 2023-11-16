package com.aurora.onboarding.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun OnboardingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Text(
            text = "Hello Onboarding Screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun Preview() {
    OnboardingScreen()
}