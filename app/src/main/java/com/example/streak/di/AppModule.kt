package com.example.streak.di

import android.app.Application
import androidx.room.Room
import com.example.streak.data.AppDatabase
import com.example.streak.data.repository.StreakRepository
import com.example.streak.data.repository.StreakRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "streak.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStreakRepository(db: AppDatabase): StreakRepository {
        return StreakRepositoryImpl(db.appDao())
    }
}