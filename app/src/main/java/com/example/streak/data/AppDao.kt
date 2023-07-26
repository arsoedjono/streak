package com.example.streak.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.streak.data.entity.Streak

@Dao
interface AppDao {
    @Query("SELECT * FROM streak LIMIT 1")
    fun getCurrentStreak(): LiveData<Streak?>

    @Insert
    suspend fun insertStreak(streak: Streak)

    @Update
    suspend fun updateStreak(streak: Streak)

    @Delete
    suspend fun deleteStreak(streak: Streak)
}