package com.aurora.genesis.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.aurora.genesis.domain.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarLayout(onBackPressed: () -> Unit, state: UiState) {
    TopAppBar(
        title = {
            when (state) {
                UiState.EmptyState -> {}
                UiState.GetTimelinesState -> {
                    Text(text = "New Plan", color = MaterialTheme.colorScheme.primary)
                }

                UiState.ModificationState -> {
                    Text(text = "Edit Plan", color = MaterialTheme.colorScheme.primary)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {

        }
    )
}