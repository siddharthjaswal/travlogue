package com.aurora.data.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_sessions")
data class ChatSessionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    /**
     * Foreign key referencing the id of the Trip this chat session belongs to.
     */
    @ColumnInfo(name = "trip_id") val tripId: Long?,
    @ColumnInfo(name = "start_time") val startTime: Long
)
