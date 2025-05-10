package com.example.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.appui.ui.theme.AppUITheme


@Composable
fun Scoreboard(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center

    ) {
        Text(text = "Bienvenue sur la page du scoreboard !", color = Color.Black)
        Button( onClick = { onNavigate("home") } ) {
            Text(text = "back to home")
        }

    }

}
@Preview(showBackground = true)
@Composable
fun ScoreboardPreview() {
    AppUITheme {
        Scoreboard(onNavigate = {})
    }
}