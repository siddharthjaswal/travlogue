package com.aurora.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aurora.designsystem.theme.AppTheme
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.domain.UiState
import com.aurora.home.ui.components.EmptyLayout
import com.aurora.home.ui.components.HomeLayout
import com.aurora.home.ui.components.MessageBar
import com.aurora.home.ui.components.TopBarLayout

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCreate: () -> Unit = {}
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopBarLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        },
        bottomBar = {
            MessageBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
            ) { messageText ->
                viewModel.sendMessage(messageText)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            when (state) {
                UiState.Empty -> EmptyLayout()
                UiState.Loading -> {

                }
                is UiState.HomeState -> {
                    HomeLayout()
                }
            }
        }
    }

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
            HomeScreen()
        }
    }
}