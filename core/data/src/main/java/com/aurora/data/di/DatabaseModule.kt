package com.aurora.data.di

import android.content.Context
import androidx.room.Room
import com.aurora.data.data.dao.ActivityDao
import com.aurora.data.data.dao.DayDao
import com.aurora.data.data.dao.MessageDao
import com.aurora.data.data.dao.StayDao
import com.aurora.data.data.dao.TripDao
import com.aurora.data.local.AppDatabase
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(appDatabase: AppDatabase): MessageDao {
        return appDatabase.messageDao()
    }

    @Provides
    @Singleton
    fun provideTripDao(appDatabase: AppDatabase): TripDao {
        return appDatabase.tripDao()
    }

    @Provides
    @Singleton
    fun provideStayDao(appDatabase: AppDatabase): StayDao {
        return appDatabase.stayDao()
    }

    @Provides
    @Singleton
    fun provideActivityDao(appDatabase: AppDatabase): ActivityDao {
        return appDatabase.activityDao()
    }

    @Provides
    @Singleton
    fun provideDayDao(appDatabase: AppDatabase): DayDao {
        return appDatabase.dayDao()
    }

}