package com.aurora.home.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.AppTheme
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier, // Provide a default modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { TopBarTitle() },
        actions = {
            // Spacer to balance the navigation icon and center the title
            // If you truly need an invisible icon for spacing, consider using a Spacer
            // with the same width as the navigation icon.
            // Using a transparent icon might still have some accessibility implications.
            BalancingSpacer()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent // Ensures the TopAppBar background is transparent
        )
    )
}

/**
 * Composable function for the title displayed in the TopAppBar.
 */
@Composable
private fun TopBarTitle() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Centers the title content
    ) {
        // Using a Column to allow for potential sub-titles or other elements later
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Travlogue", // Consider using stringResource for localization
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        }
    }
}

/**
 * Composable function for the navigation menu button.
 * @param onClick Lambda to be invoked when the button is clicked.
 */
@Composable
private fun NavigationMenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Menu,
            // Use stringResource for better accessibility and localization
            contentDescription = "Menu" // stringResource(R.string.menu_content_description)
        )
    }
}


/**
 * Composable function to provide a spacer in the actions slot of the TopAppBar.
 * This helps in centering the title when a navigation icon is present.
 * The width should ideally match the width of the navigation icon.
 */
@Composable
private fun BalancingSpacer() {
    // A common size for IconButton is 48.dp. Adjust if your navigation icon is different.
    Spacer(modifier = Modifier.width(48.dp))
    // If you strictly need an invisible icon (though Spacer is often preferred):
    /*
    IconButton(onClick = {}, enabled = false) { // Disable to prevent interaction
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = null, // No content description for a purely decorative/spacing element
            tint = Color.Transparent
        )
    }
    */
}

@Preview(showBackground = true) // Added showBackground for better preview visibility
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
internal fun TopBarLayoutPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeTopBar()
        }
    }
}

