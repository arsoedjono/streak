package com.example.streak.data.repository

import androidx.lifecycle.LiveData
import com.example.streak.data.entity.Streak

interface StreakRepository {
    fun getCurrentStreak(): LiveData<Streak?>
    suspend fun upsertStreak(streak: Streak)
}