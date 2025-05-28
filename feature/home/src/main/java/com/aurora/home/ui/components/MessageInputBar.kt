package com.aurora.home.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.designsystem.theme.md_grey_300
import com.aurora.designsystem.theme.md_grey_700
import com.aurora.designsystem.theme.md_transparent


/**
 * A Composable that displays an [OutlinedTextField] at the bottom of the screen,
 * serving as the primary input bar for users to compose messages.
 * In the context of this travel planning app, users can type queries
 * like "Help me plan: Paris for a week?" to interact with the chat feature.
 *
 * @param modifier The modifier to be applied to this component.
 * @param onSendMessage Callback invoked when the user submits their message. (Consider adding this)
 */
@Composable
internal fun MessageInputBar(
    modifier: Modifier = Modifier, // Added default modifier
    onSendMessage: (String) -> Unit
) {
    var textInput by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val sendMessageAction = {
        if (textInput.isNotBlank()) {
            onSendMessage(textInput)
            textInput = ""
            // Optionally, you might want to hide the keyboard here as well
            // keyboardController?.hide()
        }
    }

    Box(
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .border(1.dp, md_grey_700, RoundedCornerShape(percent = 50))
    ) {
        MessageTextField(
            textInput = textInput,
            onTextInputChange = { textInput = it },
            onSendMessage = sendMessageAction,
            focusRequester = focusRequester,
            modifier = Modifier // Pass a new Modifier instance or adjust as needed
                .padding(vertical = 6.dp, horizontal = 12.dp)
                .fillMaxWidth()
        )
    }

    // Request focus and show keyboard when the composable enters the composition
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageTextField(
    textInput: String,
    onTextInputChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = textInput,
        onValueChange = onTextInputChange,
        placeholder = { MessagePlaceholderText() },
        modifier = modifier.focusRequester(focusRequester),
        shape = RoundedCornerShape(percent = 50),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = md_transparent,
            unfocusedBorderColor = md_transparent,
            cursorColor = md_grey_300 // Added for better visibility
        ),
        keyboardActions = KeyboardActions(onSend = { onSendMessage() }),
        trailingIcon = {
            SendButton(
                onClick = onSendMessage,
                enabled = textInput.isNotBlank()
            )
        },
        singleLine = true // Often desired for input bars
    )
}

@Composable
private fun MessagePlaceholderText() {
    val placeholderText = "Help me plan: e.g., 'Paris for a week ? "
    Text(placeholderText, color = md_grey_700)
}

@Composable
private fun SendButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Send,
            contentDescription = "Send message",
            tint = if (enabled) md_grey_300 else md_grey_300.copy(alpha = 0.4f) // Adjusted alpha for disabled state
        )
    }
}

@Preview
@Composable
private fun MessageInputBarPreview() {
    MessageInputBar(onSendMessage = {})
}

@Preview
@Composable
private fun MessageTextFieldPreview() {
    MessageTextField(
        textInput = "Preview text",
        onTextInputChange = {},
        onSendMessage = {},
        focusRequester = remember { FocusRequester() }
    )
}

@Preview
@Composable
private fun SendButtonPreview() {
    SendButton(onClick = {}, enabled = true)
}

@Preview
@Composable
private fun SendButtonDisabledPreview() {
    SendButton(onClick = {}, enabled = false)
}