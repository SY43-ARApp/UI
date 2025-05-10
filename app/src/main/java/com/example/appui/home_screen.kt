package com.example.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun Home_screen(onNavigate:  (String)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text(text = "Bienvenue sur la home page !", color = Color.Black)
        Button(onClick = {onNavigate("game")}) {
            Text(text = "Jouer")
        }

        Row {
            Button( onClick = { onNavigate("scoreboard") } ) {
                Text(text = "Scoreboard")
            }
            Button(onClick = { onNavigate("Profile") }) {
                Text(text = "Mon profile")
            }
            Button(onClick = {onNavigate("games_history")}) {
                Text(text="Historique des jeux")
            }
            Button(onClick = {onNavigate("settings")}) {
                Text(text="Paramètres")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AppUITheme {
        Home_screen(onNavigate = {})
    }
}