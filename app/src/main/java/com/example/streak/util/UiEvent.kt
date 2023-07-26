package com.example.streak.util

import androidx.compose.material3.SnackbarDuration

sealed class UiEvent {
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ): UiEvent()
}
