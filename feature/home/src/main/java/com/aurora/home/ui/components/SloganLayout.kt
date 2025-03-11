package com.aurora.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
internal fun SloganLayout() {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp, bottom = 0.dp),
            textAlign = TextAlign.Center,
            text = "Unlock Your",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 56.dp),
            textAlign = TextAlign.Center,
            text = "Next Adventure",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}