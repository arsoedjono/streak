package com.example.streak.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.streak.data.dao.StreakDao
import com.example.streak.data.model.Streak

@Database(entities = [Streak::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun streakDao(): StreakDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun db(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "streak-app"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}