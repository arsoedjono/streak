package com.example.streak.ui.screen.home

sealed class HomeEvent {
    object OnSaveStreak: HomeEvent()
    data class SetStreakCount(val streakCount: String): HomeEvent()
    data class SetStreakName(val streakName: String): HomeEvent()
}
