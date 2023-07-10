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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    streakTitle: String = "",
    streakCount: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var streakTitleState by remember { mutableStateOf(streakTitle) }
        var streakCountState by remember { mutableStateOf(streakCount.toString()) }

        Text(
            text = "What's Your Streak?",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = streakTitleState,
            onValueChange = { streakTitleState = it },
            label = { Text("Your Streak") }
        )
        OutlinedTextField(
            value = if (streakCount == 0) "" else streakCountState,
            onValueChange = { streakCountState = it },
            label = { Text("Initial Streak Count (default: 0)") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = { /*TODO save to db*/ }
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
            text = streakCount.toString(),
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