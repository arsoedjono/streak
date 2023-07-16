package com.example.streak.ui.screen.streak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.streak.data.entity.Streak
import com.example.streak.ui.theme.BlueBright
import com.example.streak.ui.theme.GreenMalachite
import com.example.streak.ui.theme.OrangePeel
import com.example.streak.ui.theme.RedCoral
import com.example.streak.ui.theme.StreakTheme
import com.example.streak.util.UiEvent
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        viewModel.uiEvent.collect {
            when(it) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = it.message,
                        actionLabel = it.action
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
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(it)
        ) {
            if (viewModel.streak != null) {
                StreakCardNotEmpty(
                    streak = viewModel.streak!!,
                    onChecklistClicked = {
                        viewModel.onEvent(StreakEvent.OnIncrementStreakCount)
                    },
                    onEditClicked = {
                        showBottomSheet = true
                    },
                    onDeleteClicked = {
                        viewModel.onEvent(StreakEvent.OnDeleteStreak(viewModel.streak!!))
                    }
                )
            } else {
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

@Composable
fun StreakCardEmpty(
    onAddClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(30.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No active streak yet...",
            modifier = Modifier.padding(48.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center
        )
        FilledIconButton(
            onClick = onAddClicked,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = BlueBright)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add button",
                tint = Color.White,
                modifier = Modifier.size(45.dp)
            )
        }
    }
}

@Composable
fun StreakCardNotEmpty(
    streak: Streak,
    onChecklistClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    val isPendingPeriod = LocalDate.now() < LocalDate.parse(streak.nextPeriod, DateTimeFormatter.ofPattern("dd-MM-yyyy"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 0.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(48.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = streak.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = streak.count.toString(),
                style = MaterialTheme.typography.displayLarge. copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = "days",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (isPendingPeriod) {
            Text(
                text = "Next period: ${streak.nextPeriod}",
                modifier = Modifier
                    .weight(1f, false)
                    .padding(bottom = 15.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
    Spacer(modifier = Modifier.size(24.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 0.dp)
    ) {
        FilledIconButton(
            onClick = onChecklistClicked,
            enabled = !isPendingPeriod,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(50.dp),
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = GreenMalachite)
        ) {
            Icon(
                Icons.Default.Done,
                contentDescription = "Done button",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(40.dp)
        ) {
            FilledIconButton(
                onClick = onEditClicked,
                enabled = true,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(0.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 15.dp,
                    bottomEnd = 0.dp
                ),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = OrangePeel)
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit button",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
            FilledIconButton(
                onClick = onDeleteClicked,
                enabled = true,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(0.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 15.dp
                ),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = RedCoral)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete button",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun StreakForm(
    streak: Streak?,
    onNameChanged: (String) -> Unit,
    onCountChanged: (String) -> Unit,
    onSaveClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 45.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Streak",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = streak?.name ?: "",
            onValueChange = onNameChanged,
            label = { Text(text = "Name") }
        )
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = streak?.count?.toString() ?: "",
            onValueChange = onCountChanged,
            label = { Text(text = "Streak Count") }
        )
        Spacer(modifier = Modifier.size(24.dp))
        FilledTonalButton(
            onClick = onSaveClicked,
            modifier = Modifier
                .width(200.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = BlueBright,
                contentColor = Color.White
            )
        ) {
            Text(text = "Save")
        }
    }
}

@Preview
@Composable
fun StreakCardEmptyPreview() {
    StreakTheme {
        StreakCardEmpty(onAddClicked = {})
    }
}

@Preview
@Composable
fun StreakCardNotEmptyPreviewActivePeriod() {
    StreakTheme {
        Column {
            StreakCardNotEmpty(
                streak = Streak(
                    name = "My Streak",
                    count = 139,
                    nextPeriod = LocalDate
                        .now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ),
                onChecklistClicked = {},
                onEditClicked = {},
                onDeleteClicked = {}
            )
        }
    }
}

@Preview
@Composable
fun StreakCardNotEmptyPreviewPendingPeriod() {
    StreakTheme {
        Column {
            StreakCardNotEmpty(
                streak = Streak(
                    name = "My Streak",
                    count = 139,
                    nextPeriod = LocalDate
                        .now()
                        .plusDays(1)
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ),
                onChecklistClicked = {},
                onEditClicked = {},
                onDeleteClicked = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StreakFormPreviewEmpty() {
    StreakTheme {
        StreakForm(
            streak = null,
            onNameChanged = {},
            onCountChanged = {},
            onSaveClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StreakFormPreviewExist() {
    StreakTheme {
        StreakForm(
            streak = Streak(
                name = "My Streak",
                count = 139,
                nextPeriod = LocalDate
                    .now()
                    .plusDays(1)
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            ),
            onNameChanged = {},
            onCountChanged = {},
            onSaveClicked = {}
        )
    }
}