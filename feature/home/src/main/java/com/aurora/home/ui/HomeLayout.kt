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
import com.aurora.home.ui.components.TopBarLayout

@Composable
internal fun HomeLayout(viewModel: HomeViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        TopBarLayout(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

        }

        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 56.dp)
        )
    }
}

@Preview
@Composable
private fun HomeLayoutPreview() {
    val viewModel: HomeViewModel = viewModel()
    HomeLayout(viewModel = viewModel)
}