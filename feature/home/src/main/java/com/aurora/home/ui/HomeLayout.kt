package com.aurora.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.ui.components.BottomBar

@Composable
internal fun HomeLayout(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
        }
        BottomBar()
    }
}

@Preview
@Composable
private fun HomeLayoutPreview() {
    val viewModel: HomeViewModel = viewModel()
    HomeLayout(viewModel = viewModel)
}