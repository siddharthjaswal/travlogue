package com.aurora.travlogue.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
    version = 1,
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
}
