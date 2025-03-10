package com.aurora.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.pacificoFamily

@Preview
@Composable
fun Travlogue() {
    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Travlogue",
            fontFamily = pacificoFamily,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}