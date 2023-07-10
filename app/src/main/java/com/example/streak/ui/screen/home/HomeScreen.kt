package com.example.streak.ui.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What's Your Streak?",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = viewModel.streakName,
            onValueChange = { viewModel.onEvent(HomeEvent.SetStreakName(it)) },
            label = { Text("Your Streak") }
        )
        OutlinedTextField(
            value = viewModel.streakCount.toString(),
            onValueChange = { viewModel.onEvent(HomeEvent.SetStreakCount(it)) },
            label = { Text("Initial Streak Count (default: 0)") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = { viewModel.onEvent(HomeEvent.OnSaveStreak) }
        ) {
            Text("Save")
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Divider(modifier = Modifier.padding(horizontal = 50.dp))
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Current Streak",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = viewModel.streakCount.toString(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(name = "Light theme", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}