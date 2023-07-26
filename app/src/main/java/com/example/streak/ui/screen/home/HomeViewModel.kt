package com.example.streak.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streak.data.entity.Streak
import com.example.streak.data.repository.StreakRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StreakRepository
): ViewModel() {
    var streak by mutableStateOf<Streak?>(null)
        private set
    var streakName by mutableStateOf("")
        private set
    var streakCount by mutableStateOf(0)
        private set

//    private val _uiEvent = Channel<UiEvent>()
//    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        repository.getCurrentStreak().value?.let {
            streakName = it.name
            streakCount = it.count
            this@HomeViewModel.streak = it
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnSaveStreak -> {
                if (streakName.isBlank()) return

                viewModelScope.launch {
//                    repository.upsertStreak(streak ?: Streak(name = streakName, count = streakCount))
                }
            }
            is HomeEvent.SetStreakCount -> streakCount = event.streakCount.toInt()
            is HomeEvent.SetStreakName -> streakName = event.streakName
        }
    }
}