package com.aurora.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.dotoFamily
import com.aurora.designsystem.theme.pacificoFamily

@Preview
@Composable
fun TravLogue() {
    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Trav",
            fontFamily = pacificoFamily,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.size(3.dp))
        Text(
            text = "Logue",
            fontFamily = dotoFamily,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}