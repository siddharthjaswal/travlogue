package com.aurora.genesis.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aurora.designsystem.theme.AppTheme
import com.aurora.genesis.domain.GenesisViewModel
import com.aurora.genesis.domain.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenesisScreen(viewModel: GenesisViewModel = hiltViewModel(), onBackPressed: () -> Unit) {

    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (state is UiState.GetTimelinesState) {
                TopBarLayout(
                    trip = state.trip,
                    onBackPressed = onBackPressed,
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar = {
            // Your bottom bar content if needed
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (state) {
                UiState.EmptyState -> {
                    EmptyContent()
                }

                is UiState.GetTimelinesState -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            GetTimelineLayout(state.trip)
                        }
                    }
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