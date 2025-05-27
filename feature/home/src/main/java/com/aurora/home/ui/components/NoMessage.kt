package com.aurora.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a message, typically used when there is
 * no data or content to show in a particular section of the UI.
 *
 * It centers a text message within the available space.
 *
 * @param modifier Optional [Modifier] to be applied to the layout.
 * @param message The text message to be displayed. Defaults to a generic "No messages available."
 *                It's recommended to use string resources for localization.
 */
@Composable
internal fun NoMessage(
    modifier: Modifier = Modifier,
    message: String = "No messages available." // Consider using stringResource(R.string.no_messages_available)
) {
    Box(
        modifier = modifier
            .fillMaxSize() // Occupy all available space
            .padding(16.dp), // Add some padding around the content
        contentAlignment = Alignment.Center // Center the content within the Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // You can add an icon here if desired, e.g., an empty state icon
            // Icon(imageVector = Icons.Default.Inbox, contentDescription = null)
            // Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium, // Use a predefined text style
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Use a color that contrasts well
            )
        }
    }
}

@Preview(showBackground = true, name = "NoMessage Preview")
@Composable
private fun NoMessagePreview() {
    MaterialTheme { // Wrap with MaterialTheme for previews to see theming
        NoMessage()
    }
}

@Preview(showBackground = true, name = "NoMessage Custom Text Preview")
@Composable
private fun NoMessageCustomTextPreview() {
    MaterialTheme {
        NoMessage(message = "It looks like your trip session is empty!")
    }
}