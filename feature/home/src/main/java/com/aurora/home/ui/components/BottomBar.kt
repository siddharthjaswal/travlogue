package com.aurora.home.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.designsystem.theme.md_grey_700
import com.aurora.designsystem.theme.md_transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(modifier: Modifier) {
    var textInput by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .border(1.dp, md_grey_700, RoundedCornerShape(percent = 50))
    ) {
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            placeholder = { PlaceHolderText() },
            modifier = modifier
                .padding(vertical = 6.dp, horizontal = 12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(percent = 50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = md_transparent,
                unfocusedBorderColor = md_transparent
            )
        )
    }
}

@Composable
private fun PlaceHolderText() {
    val placeHolderText = "Help me plan: e.g., 'Paris for a week ? "
    Text(placeHolderText, color = md_grey_700)
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(modifier = Modifier)
}