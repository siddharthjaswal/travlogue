package com.aurora.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box // Added
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator // Added
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Added
import androidx.compose.ui.Alignment // Added
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aurora.designsystem.theme.AppTheme
import com.aurora.home.domain.HomeUiState
import com.aurora.home.domain.HomeViewModel
import com.aurora.home.ui.components.HomeTopBar
import com.aurora.home.ui.components.MessageInputBar
import com.aurora.home.ui.components.MessageList
import com.aurora.home.ui.components.NoMessage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToPlan: (Long) -> Unit
) {

    val state by viewModel.homeUiState.collectAsStateWithLifecycle() // Changed to by delegate
    val isSending by viewModel.isSendingMessage.collectAsStateWithLifecycle() // Added

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            HomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onNavigateToPlan = {
                    viewModel.latestTripId?.let {
                        onNavigateToPlan(it)
                    }
                }
            )
        },
        bottomBar = {
            MessageInputBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding(),
                // Optionally, disable input bar while sending
                // enabled = !isSending
            ) { messageText ->
                viewModel.sendMessage(messageText)
            }
        }
    ) { paddingValues ->
        Box( // Wrap content in a Box to overlay the loader
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply scaffold padding here
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize() // Column takes full size of the Box content area
            ) {
                when (val currentUiState = state) { // Give state a local name for smart casting
                    HomeUiState.Loading -> {
                        // You might want a different loading indicator for initial load vs. message sending
                        // For now, this will be covered by the global 'isSending' loader if initial load is quick
                    }

                    is HomeUiState.ChatMessages -> {
                        MessageList(currentUiState.messages)
                    }

                    is HomeUiState.NoMessages -> NoMessage()
                    is HomeUiState.Error -> {
                        // Display error message
                    }
                }
            }

            if (isSending) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center) // Center the loader
                )
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
            // Preview might need a mock ViewModel or to pass a static 'isSending' value
            // For simplicity, just showing the HomeScreen without specific state for loader here.
            HomeScreen() {}
        }
    }
}
