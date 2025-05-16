package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val MESSAGES_TABLE_NAME = "messages"

@Entity(tableName = MESSAGES_TABLE_NAME)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    /**
     * Foreign key referencing the id of the ChatSession this message belongs to.
     */
    @ColumnInfo(name = "session_id") val sessionId: Long,
    @ColumnInfo(name = "sender") val sender: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "content") val content: String
)
