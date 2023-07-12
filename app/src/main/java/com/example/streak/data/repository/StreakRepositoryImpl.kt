package com.example.streak.data.repository

import androidx.lifecycle.LiveData
import com.example.streak.data.AppDao
import com.example.streak.data.entity.Streak

class StreakRepositoryImpl(private val dao: AppDao): StreakRepository {
    override fun getCurrentStreak(): LiveData<Streak?> {
        return dao.getCurrentStreak()
    }

    override suspend fun insertStreak(streak: Streak) {
        dao.insertStreak(streak)
    }

    override suspend fun updateStreak(streak: Streak) {
        dao.updateStreak(streak)
    }

    override suspend fun deleteStreak(streak: Streak) {
        dao.deleteStreak(streak)
    }
}