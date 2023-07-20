package com.example.streak.ui.screen.streak

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.streak.R
import com.example.streak.data.entity.Streak
import com.example.streak.ui.theme.BlueBright
import com.example.streak.ui.theme.StreakTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
            text = stringResource(id = R.string.streakFormTitle),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = streak?.name ?: "",
            onValueChange = onNameChanged,
            label = { Text(text = stringResource(id = R.string.streakFormLabelName)) }
        )
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = streak?.count?.toString() ?: "",
            onValueChange = onCountChanged,
            label = { Text(text = stringResource(id = R.string.streakFormLabelCount)) }
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