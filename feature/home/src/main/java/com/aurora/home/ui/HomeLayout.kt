package com.aurora.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.ui.components.BottomBar

@Composable
internal fun HomeLayout(viewModel: HomeViewModel) {
    Box(
        modifier = Modifier.fillMaxSize() // Box takes up the maximum size available
    ) {
        // Content that should occupy the remaining space goes here
        // This Column will be positioned at the top by default in the Box
        Column(
            modifier = Modifier
                .fillMaxSize() // Fill the size of the Box
                .padding(horizontal = 16.dp), // Add some padding to the content
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between items
        ) {
            // Your main screen content here
            // This content will be above the BottomBar
        }

        // BottomBar fixed at the bottom
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
private fun HomeLayoutPreview() {
    val viewModel: HomeViewModel = viewModel()
    HomeLayout(viewModel = viewModel)
}