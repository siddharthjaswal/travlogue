package com.aurora.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar() {
    var textInput by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            placeholder = { Text("Help me plan: e.g., 'Paris for a week ? ", color = Color.Gray) },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(percent = 50)
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar()
}