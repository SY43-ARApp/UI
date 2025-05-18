package com.example.appui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appui.ui.theme.AppUITheme

@Composable
fun GameScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Le jeu")
        Button(onClick = {onNavigate("home")}) {
            Text(text = "back to home")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GamePreview() {
    AppUITheme {
        GameScreen(onNavigate = {})
    }
}