package com.aurora.onboarding.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun OnboardingScreen() {
    Text(
        text = "Hello Onboarding Screen"
    )
}

@Preview(showBackground = true)
@Composable
internal fun Preview() {
    OnboardingScreen()
}