package com.example.streak.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.streak.data.entity.Streak

@Dao
interface AppDao {
    @Query("SELECT * FROM streak LIMIT 1")
    fun getCurrentStreak(): Streak

    @Upsert
    suspend fun upsertStreak(streak: Streak)
}