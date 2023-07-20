package com.example.streak.ui.screen.streak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.streak.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreakScreen(
    viewModel: StreakViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(StreakEvent.OnUndoDeleteStreak)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .padding(it)
        ) {
            viewModel.streak?.let { streak ->
                StreakCardNotEmpty(
                    streak = streak,
                    onChecklistClicked = {
                        viewModel.onEvent(StreakEvent.OnIncrementStreakCount)
                    },
                    onEditClicked = {
                        showBottomSheet = true
                    },
                    onDeleteClicked = {
                        viewModel.onEvent(StreakEvent.OnDeleteStreak(streak))
                    }
                )
            } ?: run {
                StreakCardEmpty(
                    onAddClicked = {
                        showBottomSheet = true
                    }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                StreakForm(
                    streak = viewModel.streak,
                    onNameChanged = { name ->
                        viewModel.onEvent(StreakEvent.SetStreakName(name))
                    },
                    onCountChanged = { count ->
                        viewModel.onEvent(StreakEvent.SetStreakCount(count))
                    },
                    onSaveClicked = {
                        viewModel.onEvent(StreakEvent.OnSaveStreak)

                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    }
                )
            }
        }
    }
}