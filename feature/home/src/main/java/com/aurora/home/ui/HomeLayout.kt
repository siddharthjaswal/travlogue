package com.aurora.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.ui.components.LocationSearchLayout
import com.aurora.home.ui.components.SloganLayout

@Composable
internal fun HomeLayout(viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SloganLayout()
            LocationSearchLayout(viewModel)
        }
    }
}