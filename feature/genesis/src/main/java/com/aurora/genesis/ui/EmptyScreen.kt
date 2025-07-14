package com.aurora.genesis.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun EmptyContent(
    onCreatePlanClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp), // Horizontal padding for the content
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // This weighted column takes up all available vertical space,
        // pushing the button below it to the bottom of the screen.
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your next adventure awaits.",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Let's start planning.",
                style = MaterialTheme.typography.titleLarge,
                // Use a slightly muted color for secondary text from your theme
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // This button will be positioned at the bottom
        Button(
            onClick = onCreatePlanClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp), // Spacing from the screen's bottom edge
            shape = RoundedCornerShape(50), // Creates the pill shape
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF82D186), // Specific green from the screenshot
                contentColor = Color.Black // Text color on the button
            )
        ) {
            Text(
                text = "Create a New Plan",
                modifier = Modifier.padding(vertical = 12.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
internal fun EmptyContentPreview() {
    EmptyContent(
        onCreatePlanClicked = {}
    )
}