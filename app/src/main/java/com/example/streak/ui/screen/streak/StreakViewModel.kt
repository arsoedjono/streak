package com.example.streak.ui.screen.streak

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streak.data.entity.Streak
import com.example.streak.data.repository.StreakRepository
import com.example.streak.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class StreakViewModel @Inject constructor(
    private val repository: StreakRepository
): ViewModel() {
    init {
        repository.getCurrentStreak().value?.let {
            this@StreakViewModel.streak = it
        }
    }

    var streak by mutableStateOf<Streak?>(null)
        private set
    var streakName by mutableStateOf("")
        private set
    var streakCount by mutableStateOf(0)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var deletedStreak: Streak? = null

    fun onEvent(event: StreakEvent) {
        when(event) {
            is StreakEvent.OnSaveStreak -> {
                viewModelScope.launch {
                    if (streakName.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(message = "Name can't be empty")
                        )
                        return@launch
                    }
                    if (streak != null) {
                        repository.updateStreak(streak!!.copy(
                            name = streakName,
                            count = streakCount
                        ))
                    } else {
                        val newStreak = Streak(
                            name = streakName,
                            count = streakCount,
                            nextPeriod = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        )
                        repository.insertStreak(newStreak)
                    }
                }
            }
            is StreakEvent.OnIncrementStreakCount -> {
                viewModelScope.launch {
                    repository.updateStreak(streak!!.copy(count = streakCount + 1))
                }
            }
            is StreakEvent.OnDeleteStreak -> {
                viewModelScope.launch {
                    deletedStreak = event.streak
                    repository.deleteStreak(event.streak)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "Streak successfully deleted!",
                            action = "Undo",
                            duration = SnackbarDuration.Indefinite
                        )
                    )
                }
            }
            is StreakEvent.OnUndoDeleteStreak -> {
                deletedStreak?.let {
                    viewModelScope.launch {
                        repository.insertStreak(it)
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Undo delete successfully"
                            )
                        )
                    }
                }
            }
            is StreakEvent.SetStreakName -> streakName = event.streakName
            is StreakEvent.SetStreakCount -> streakCount = event.streakCount.toInt()
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}