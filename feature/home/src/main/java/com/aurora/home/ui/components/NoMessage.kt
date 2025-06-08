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
import com.aurora.data.data.entity.message.getInitialMessage


/**
 * A composable function that displays a message, typically used when there is
 * no data or content to show in a particular section of the UI.
 *
 * It centers a text message within the available space.
 *
 * @param modifier Optional [Modifier] to be applied to the layout.
 * @param message The text message to be displayed. Defaults to a fun, inviting message.
 *                It's recommended to use string resources for localization.
 */
@Composable
internal fun NoMessage(
    modifier: Modifier = Modifier,
    message: String = getInitialMessage()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
    MaterialTheme {
        NoMessage()
    }
}
