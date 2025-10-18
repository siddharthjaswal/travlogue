package com.aurora.travlogue.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aurora.travlogue.core.data.local.dao.*
import com.aurora.travlogue.core.data.local.entities.*

@Database(
    entities = [
        Trip::class,
        Location::class,
        Activity::class,
        Booking::class,
        Gap::class,
        TransitOption::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TravlogueDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun locationDao(): LocationDao
    abstract fun activityDao(): ActivityDao
    abstract fun bookingDao(): BookingDao
    abstract fun gapDao(): GapDao
    abstract fun transitOptionDao(): TransitOptionDao

    companion object {
        /**
         * Migration from version 1 to 2: Add endTimezone field to Booking table
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add endTimezone column to bookings table
                database.execSQL(
                    "ALTER TABLE bookings ADD COLUMN endTimezone TEXT DEFAULT NULL"
                )
            }
        }
    }
}
