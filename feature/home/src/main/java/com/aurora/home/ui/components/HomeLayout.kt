package com.aurora.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aurora.home.domain.HomeViewModel

@Composable
internal fun HomeLayout(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

        }

        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 56.dp)
                .imePadding()
        )
    }
}

@Preview
@Composable
private fun HomeLayoutPreview() {
    val viewModel: HomeViewModel = viewModel()
    HomeLayout(viewModel = viewModel)
}