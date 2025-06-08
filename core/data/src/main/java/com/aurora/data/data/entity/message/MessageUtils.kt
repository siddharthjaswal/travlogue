package com.aurora.data.data.entity.message

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