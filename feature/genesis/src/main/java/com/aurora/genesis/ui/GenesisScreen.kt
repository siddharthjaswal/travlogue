package com.aurora.genesis.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aurora.designsystem.theme.AppTheme
import com.aurora.genesis.domain.GenesisViewModel
import com.aurora.genesis.domain.UiState
import com.aurora.genesis.domain.UiState.EmptyState


@Composable
fun GenesisScreen(viewModel: GenesisViewModel = hiltViewModel(), onBackPressed: () -> Unit) {

    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopBarLayout(state = state, onBackPressed = {
                onBackPressed()
            })
        },
        bottomBar = {
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (state) {
                EmptyState -> {
                    EmptyContent()
                }

                UiState.GetTimelinesState -> {
                    GetTimelineLayout()
                }

                UiState.ModificationState -> {

                }
            }
        }

    }
}

@Composable
private fun EmptyContent() {
    Text(text = "Empty")
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
internal fun Preview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //GenesisScreen {}
        }
    }

}