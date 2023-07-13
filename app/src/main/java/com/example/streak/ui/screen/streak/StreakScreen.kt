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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import com.example.streak.ui.theme.BlueBright
import com.example.streak.ui.theme.GreenMalachite
import com.example.streak.ui.theme.OrangePeel
import com.example.streak.ui.theme.RedCoral
import com.example.streak.ui.theme.StreakTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreakScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(it)
        ) {
            // TODO: if condition based on streak existence
            StreakCardNotEmpty(
                onEditClicked = { showBottomSheet = true }
            )
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                StreakForm(onSaveClicked = {
                    // TODO: create new or update existing streak
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) showBottomSheet = false
                    }
                })
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

// TODO: change texts to real data
@Composable
fun StreakCardNotEmpty(
    onEditClicked: () -> Unit
) {
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
                text = "The active streak name here",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "13",
                style = MaterialTheme.typography.displayLarge. copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = "days",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // TODO: only if already done for this period and waiting for the next period
        Text(
            text = "Next period: DD-MM-YYYY",
            modifier = Modifier
                .weight(1f, false)
                .padding(bottom = 15.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
    Spacer(modifier = Modifier.size(24.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 0.dp)
    ) {
        FilledIconButton(
            onClick = { /* TODO: update to increment streak count by 1 */ },
            enabled = true, // TODO: disabled if next period is after current time
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
                onClick = { /* TODO: delete streak */ },
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
        // TODO: value based on data and create onValueChange event state
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Title") }
        )
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
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
fun StreakScreenPreview() {
    StreakTheme() {
        StreakScreen()
    }
}

@Preview
@Composable
fun StreakCardEmptyPreview() {
    StreakTheme() {
        StreakCardEmpty(onAddClicked = {})
    }
}

@Preview
@Composable
fun StreakCardNotEmptyPreview() {
    StreakTheme() {
        Column() {
            StreakCardNotEmpty(onEditClicked = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StreakFormPreview() {
    StreakTheme {
        StreakForm(onSaveClicked = {})
    }
}