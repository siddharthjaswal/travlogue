package com.aurora.genesis.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.aurora.data.data.entity.trip.TripEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarLayout(onBackPressed: () -> Unit, trip: TripEntity) {
    TopAppBar(
        title = {
            Text(text = trip.name, color = MaterialTheme.colorScheme.primary)
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {

        }
    )
}