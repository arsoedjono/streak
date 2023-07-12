package com.example.streak.ui.screen.streak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.streak.ui.theme.BlueBright

@Composable
fun StreakScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
    ) {
        // if condition based on streak existence
    }
}

@Composable
fun StreakCardEmpty() {
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
            onClick = { /*TODO*/ },
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
fun StreakCardNotEmpty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(30.dp)
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
            Text(
                text = "13",
                modifier = Modifier.padding(top = 40.dp),
                style = MaterialTheme.typography.displayLarge. copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = "days",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // only if pending
        Text(
            text = "Next period: DD-MM-YYYY",
            modifier = Modifier
                .weight(1f, false)
                .padding(bottom = 15.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun StreakScreenPreview() {
    StreakScreen()
}

@Preview
@Composable
fun StreakCardEmptyPreview() {
    StreakCardEmpty()
}

@Preview
@Composable
fun StreakCardNotEmptyPreview() {
    StreakCardNotEmpty()
}