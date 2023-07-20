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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.streak.R
import com.example.streak.util.converter.humanizedRepeatUnit
import com.example.streak.data.entity.Streak
import com.example.streak.ui.theme.GreenMalachite
import com.example.streak.ui.theme.OrangePeel
import com.example.streak.ui.theme.RedCoral
import com.example.streak.ui.theme.StreakTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun StreakCardNotEmpty(
    streak: Streak,
    onChecklistClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    val isPendingPeriod = LocalDate.now() < LocalDate.parse(
        streak.nextPeriod,
        DateTimeFormatter.ofPattern(stringResource(id = R.string.formatDate))
    )

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
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = streak.count.toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = streak.humanizedRepeatUnit(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (isPendingPeriod) {
            Text(
                text = stringResource(id = R.string.streakNextPeriod, streak.nextPeriod),
                modifier = Modifier
                    .weight(1f, false)
                    .padding(bottom = 15.dp),
                fontSize = 13.sp
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
                contentDescription = stringResource(id = R.string.buttonContentDescriptionDone),
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
                    contentDescription = stringResource(id = R.string.buttonContentDescriptionEdit),
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
                    contentDescription = stringResource(id = R.string.buttonContentDescriptionDelete),
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
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
                        .format(DateTimeFormatter.ofPattern(stringResource(id = R.string.formatDate)))
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
                        .format(DateTimeFormatter.ofPattern(stringResource(id = R.string.formatDate)))
                ),
                onChecklistClicked = {},
                onEditClicked = {},
                onDeleteClicked = {}
            )
        }
    }
}