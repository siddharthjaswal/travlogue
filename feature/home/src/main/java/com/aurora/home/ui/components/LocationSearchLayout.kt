package com.aurora.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.md_grey_200
import com.aurora.designsystem.theme.md_grey_700
import com.aurora.designsystem.theme.md_grey_900
import com.aurora.designsystem.theme.md_transparent

@Preview
@Composable
internal fun LocationSearchLayout() {

    var locationSearchValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Search TextField
        TextField(
            value = locationSearchValue,
            onValueChange = { locationSearchValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            placeholder = {
                Text(
                    text = "Where to?",
                    fontSize = 16.sp,
                    color = md_grey_200
                )
            },
            shape = RoundedCornerShape(56.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = md_grey_900.copy(alpha = 0.7f),
                unfocusedContainerColor = md_grey_700.copy(alpha = 0.4f),
                focusedIndicatorColor = md_transparent,
                unfocusedIndicatorColor = md_transparent
            ),
            //keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { }),
            singleLine = true
        )
    }
}