package com.aurora.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onClickCreate: () -> Unit = {}
) {
    Scaffold(
        topBar = {
        },
        bottomBar = {
        },
        floatingActionButton = {
            Fab(onClickCreate)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Home"
            )
        }
    }
}

@Composable
internal fun Fab(onClickCreate: () -> Unit = {}) {
    FloatingActionButton(onClick = { onClickCreate() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Preview(showBackground = true)
@Composable
internal fun Preview() {
    HomeScreen()
}