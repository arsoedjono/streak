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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
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
import com.example.streak.ui.theme.GreenMalachite
import com.example.streak.ui.theme.OrangePeel
import com.example.streak.ui.theme.RedCoral
import com.example.streak.ui.theme.StreakTheme

@Composable
fun StreakScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
    ) {
        // if condition based on streak existence
        StreakCardNotEmpty()
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

        // only if pending
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
            onClick = { /*TODO*/ },
            enabled = true,
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
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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
        StreakCardEmpty()
    }
}

@Preview
@Composable
fun StreakCardNotEmptyPreview() {
    StreakTheme() {
        Column() {
            StreakCardNotEmpty()
        }
    }
}