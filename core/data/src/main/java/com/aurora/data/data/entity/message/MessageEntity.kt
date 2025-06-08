package com.aurora.data.data.entity.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val MESSAGES_TABLE_NAME = "messages"
const val SENDER_AI = "tron"
const val SENDER_USER = "user"
const val SENDER_SYSTEM = "clu"

@Entity(tableName = MESSAGES_TABLE_NAME)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    /**
     * Foreign key referencing the id of the TripEntity that this message belongs to.
     */
    @ColumnInfo(name = "trip_id") val tripId: Long,
    @ColumnInfo(name = "sender") val sender: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "content") val content: String
)
