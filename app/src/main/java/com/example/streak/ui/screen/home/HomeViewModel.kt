package com.example.streak.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.streak.data.repository.StreakRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StreakRepository
): ViewModel() {
    // TODO: finish this
    val currentStreak = repository.getCurrentStreak()
}