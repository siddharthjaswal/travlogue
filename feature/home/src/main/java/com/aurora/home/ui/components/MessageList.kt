package com.aurora.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.data.data.entity.MessageEntity
import com.aurora.designsystem.theme.AppTheme

const val SENDER_USER = "user"
const val SENDER_AI = "ai"

@Composable
internal fun MessageList(messages: List<MessageEntity>) {
    val listState = rememberLazyListState()

    // Automatically scroll to the bottom when new messages are added
    // and the user hasn't manually scrolled up.
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            // Check if the last visible item is close to the actual last item.
            // This prevents auto-scrolling if the user has scrolled up to read older messages.
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            if (lastVisibleItemIndex == null || lastVisibleItemIndex >= totalItemsCount - 2) {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between message bubbles
    ) {
        items(
            messages,
            key = { message -> message.id }) { message -> // Use a stable key if possible
            MessageBubble(message = message)
        }
    }
}


@Composable
private fun MessageBubble(message: MessageEntity) {
    val isUserMessage = message.sender.equals(SENDER_USER, ignoreCase = true)
    val alignment = if (isUserMessage) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleColor = if (isUserMessage) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (isUserMessage) MaterialTheme.colorScheme.onPrimaryContainer
    else MaterialTheme.colorScheme.onSecondaryContainer
    val bubbleShape = RoundedCornerShape(
        topStart = if (isUserMessage) 16.dp else 0.dp,
        topEnd = if (isUserMessage) 0.dp else 16.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    )
    val icon =
        if (isUserMessage) Icons.Filled.AccountCircle else Icons.Filled.AutoAwesome // Or any other AI icon
    val iconTint =
        if (isUserMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

    Row( // Use Row to place icon and bubble side-by-side
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom, // Align items to the bottom
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        if (!isUserMessage) {
            Icon(
                imageVector = icon,
                contentDescription = "AI Icon",
                modifier = Modifier
                    .size(32.dp) // Adjust size as needed
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant) // Optional background for icon
                    .padding(4.dp),
                tint = iconTint
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            modifier = Modifier
                // .align(alignment) // This is now handled by the Row's horizontalArrangement
                .weight(
                    1f,
                    fill = false
                ) // Allow bubble to take necessary space but not push icon away for long messages
                .clip(bubbleShape)
                .background(bubbleColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = message.content,
                color = textColor,
            )
        }

        if (isUserMessage) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = icon,
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(32.dp) // Adjust size as needed
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant) // Optional background for icon
                    .padding(4.dp),
                tint = iconTint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageListPreview() {
    val sampleMessages = listOf(
        MessageEntity(
            id = 1,
            sessionId = 1,
            sender = SENDER_AI,
            timestamp = System.currentTimeMillis() - 20000,
            content = "Hello! How can I help you plan your trip today?"
        ),
        MessageEntity(
            id = 2,
            sessionId = 1,
            sender = SENDER_USER,
            timestamp = System.currentTimeMillis() - 10000,
            content = "Hi! I want to go to Paris for a week."
        ),
        MessageEntity(
            id = 3,
            sessionId = 1,
            sender = SENDER_AI,
            timestamp = System.currentTimeMillis() - 5000,
            content = "Paris for a week, great choice! When are you planning to go?"
        ),
        MessageEntity(
            id = 4,
            sessionId = 1,
            sender = SENDER_USER,
            timestamp = System.currentTimeMillis(),
            content = "Around next spring. Maybe April?"
        )
    )
    AppTheme { // Use your app's theme for accurate preview
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageList(messages = sampleMessages)
        }
    }
}

@Preview(showBackground = true, name = "Message List Empty Preview")
@Composable
private fun MessageListEmptyPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageList(messages = emptyList())
        }
    }
}

@Preview(showBackground = true, name = "Single User Message Preview")
@Composable
private fun SingleUserMessagePreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageList(
                messages = listOf(
                    MessageEntity(
                        id = 1,
                        sessionId = 1,
                        sender = SENDER_USER,
                        timestamp = System.currentTimeMillis(),
                        content = "This is a user message."
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Single AI Message Preview")
@Composable
private fun SingleAiMessagePreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageList(
                messages = listOf(
                    MessageEntity(
                        id = 1,
                        sessionId = 1,
                        sender = SENDER_AI,
                        timestamp = System.currentTimeMillis(),
                        content = "This is an AI message."
                    )
                )
            )
        }
    }
}