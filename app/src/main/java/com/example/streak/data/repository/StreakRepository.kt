package com.example.streak.data.repository

import com.example.streak.data.entity.Streak

interface StreakRepository {
    fun getCurrentStreak(): Streak
    suspend fun upsertStreak(streak: Streak)
}