package com.aurora.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aurora.data.data.entity.ActivityEntity
import com.aurora.data.data.entity.ChatSessionEntity
import com.aurora.data.data.entity.MessageEntity

@Database(
    entities = [MessageEntity::class, ChatSessionEntity::class, ActivityEntity::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "travlogue_db"
    }
}