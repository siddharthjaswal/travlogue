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
import com.aurora.home.domain.HomeUiState
import com.aurora.home.ui.components.NoMessage
import com.aurora.home.ui.components.MessageList
import com.aurora.home.ui.components.MessageInputBar
import com.aurora.home.ui.components.HomeTopBar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCreate: () -> Unit = {}
) {

    val state = viewModel.homeUiState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        },
        bottomBar = {
            MessageInputBar(
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
                HomeUiState.Loading -> {

                }
                is HomeUiState.ChatMessages -> {
                    MessageList(state.messages)
                }

                is HomeUiState.NoMessages -> NoMessage()
                is HomeUiState.Error -> {

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