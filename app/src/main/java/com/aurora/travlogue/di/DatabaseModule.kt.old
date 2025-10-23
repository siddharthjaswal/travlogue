package com.aurora.travlogue.di

import android.content.Context
import androidx.room.Room
import com.aurora.travlogue.core.data.local.dao.*
import com.aurora.travlogue.core.data.local.database.TravlogueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTravlogueDatabase(
        @ApplicationContext context: Context
    ): TravlogueDatabase {
        return Room.databaseBuilder(
            context,
            TravlogueDatabase::class.java,
            "travlogue_database"
        )
            .addMigrations(
                TravlogueDatabase.MIGRATION_1_2,
                TravlogueDatabase.MIGRATION_2_3
            )
            .fallbackToDestructiveMigration() // For development - remove for production
            .build()
    }

    @Provides
    @Singleton
    fun provideTripDao(database: TravlogueDatabase): TripDao {
        return database.tripDao()
    }

    @Provides
    @Singleton
    fun provideLocationDao(database: TravlogueDatabase): LocationDao {
        return database.locationDao()
    }

    @Provides
    @Singleton
    fun provideActivityDao(database: TravlogueDatabase): ActivityDao {
        return database.activityDao()
    }

    @Provides
    @Singleton
    fun provideBookingDao(database: TravlogueDatabase): BookingDao {
        return database.bookingDao()
    }

    @Provides
    @Singleton
    fun provideGapDao(database: TravlogueDatabase): GapDao {
        return database.gapDao()
    }

    @Provides
    @Singleton
    fun provideTransitOptionDao(database: TravlogueDatabase): TransitOptionDao {
        return database.transitOptionDao()
    }
}
