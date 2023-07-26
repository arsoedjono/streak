package com.example.streak.ui.screen.streak

import com.example.streak.data.entity.Streak

sealed class StreakEvent {
    object OnSaveStreak: StreakEvent()
    object OnIncrementStreakCount: StreakEvent()
    data class OnDeleteStreak(val streak: Streak): StreakEvent()
    object OnUndoDeleteStreak: StreakEvent()
    data class SetStreakName(val streakName: String): StreakEvent()
    data class SetStreakCount(val streakCount: String): StreakEvent()
}
