package com.example.streak.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streak.data.entity.Streak

@Database(
    entities = [Streak::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}