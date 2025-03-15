package com.aurora.home.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.AppTheme
import com.aurora.designsystem.theme.md_transparent
import com.aurora.home.domain.HomeViewModel

@Composable
internal fun LocationSearchLayout(viewModel: HomeViewModel) {

    var locationSearchValue by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val suggestions by viewModel.suggestions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = locationSearchValue,
                onValueChange = {
                    locationSearchValue = it
                    isDropdownExpanded = it.isNotEmpty()
                    viewModel.getSearchNearByPlacesByString(it, context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                placeholder = {
                    Text(
                        text = "Where to?",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Places"
                        )
                    }
                },
                shape = RoundedCornerShape(56.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.5f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.4f),
                    focusedIndicatorColor = md_transparent,
                    unfocusedIndicatorColor = md_transparent
                ),
                keyboardActions = KeyboardActions(onSearch = { }),
                singleLine = true
            )

            if (isDropdownExpanded && suggestions.isNotEmpty()) {
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    suggestions.forEach { suggestion ->
                        val fullText = suggestion.getFullText(null).toString()
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = fullText,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            onClick = {
                                // Update the TextField value with the selected suggestion
                                locationSearchValue = fullText
                                isDropdownExpanded = false // Close the dropdown
                                // Do not call getSearchNearByPlacesByString again
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
internal fun Preview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            //LocationSearchLayout()
        }
    }
}