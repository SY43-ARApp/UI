package com.example.appui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appui.ui.theme.AppUITheme

val games = listOf("games 1", "games 2", "games 3", "games 4", "games 5", "games 6", "games 7", "games 8", "games 9", "games 10")

@Composable
fun HistoryScreen(
    onNavigate: (String) -> Unit
) {
    Column {
        for (game in games) {
                Text(
                    text = game,
                    modifier = Modifier
                        .padding(16.dp)
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreene() {
    AppUITheme {
        HistoryScreen(onNavigate = {})
    }
}
