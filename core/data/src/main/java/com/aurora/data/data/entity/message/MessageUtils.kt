package com.aurora.data.data.entity.message

private const val initPromptMessage =
    "Ready to plan your next adventure?\nAsk me anything, like \"Best beaches in Thailand\" or \"Weekend trip to Rome\""

fun getInitialMessage(): String{
    return initPromptMessage
}

fun createSenderMessage(tripId: Long, messageText: String): MessageEntity {
    return MessageEntity(
        tripId = tripId,
        sender = SENDER_USER,
        timestamp = System.currentTimeMillis(),
        content = messageText.trim()
    )
}

fun createAiMessage(tripId: Long, messageText: String): MessageEntity {
    return MessageEntity(
        tripId = tripId,
        sender = SENDER_AI,
        timestamp = System.currentTimeMillis(),
        content = messageText.trim()
    )
}

fun createSystemMessage(tripId: Long, messageText: String): MessageEntity {
    return MessageEntity(
        tripId = tripId,
        sender = SENDER_SYSTEM,
        timestamp = System.currentTimeMillis(),
        content = messageText.trim()
    )
}