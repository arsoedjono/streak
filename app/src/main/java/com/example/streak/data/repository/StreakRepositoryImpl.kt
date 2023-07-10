package com.example.streak.data.repository

import com.example.streak.data.AppDao
import com.example.streak.data.entity.Streak

class StreakRepositoryImpl(private val dao: AppDao): StreakRepository {
    override fun getCurrentStreak(): Streak {
        return dao.getCurrentStreak()
    }

    override suspend fun upsertStreak(streak: Streak) {
        dao.upsertStreak(streak)
    }
}