package com.aurora.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onClickCreate: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            Text(
                text = "Hello Home Screen"
            )
            Button(
                onClick = onClickCreate
            ) {
                Text(
                    text = "Start Planning"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
internal fun Preview() {
    HomeScreen()
}