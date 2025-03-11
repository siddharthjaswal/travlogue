package com.aurora.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.pacificoFamily

@Preview
@Composable
fun TravlogueLayout() {
    Row(
        modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Travlogue",
            fontFamily = pacificoFamily,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun SloganLayout() {
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