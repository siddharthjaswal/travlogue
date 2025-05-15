package com.aurora.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aurora.data.data.entity.ChatSession
import com.aurora.data.data.entity.Message

@Database(entities = [Message::class, ChatSession::class], version = 1, exportSchema = false)
//@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "travlogue_db"
    }
}