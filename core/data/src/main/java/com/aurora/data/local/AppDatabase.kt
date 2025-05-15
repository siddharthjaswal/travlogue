package com.aurora.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [], version = 1, exportSchema = false)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "travlogue_db"
    }
}