package com.example.streak.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.streak.data.model.Streak

@Dao
interface StreakDao {
    @Query("SELECT * FROM streak LIMIT 1")
    fun getCurrent(): Streak

    @Insert
    fun insert(vararg streaks: Streak)
}