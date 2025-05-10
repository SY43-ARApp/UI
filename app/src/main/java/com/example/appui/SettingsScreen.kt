package com.example.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appui.ui.theme.AppUITheme


@Composable
fun Settings(onNavigate: (String) -> Unit) {
    // States for settings
    var masterVolume by remember { mutableStateOf(0.7f) }
    var musicVolume by remember { mutableStateOf(0.5f) }
    var soundEffectsEnabled by remember { mutableStateOf(true) }
    var muteAll by remember { mutableStateOf(false) }
    var showNotifications by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500)), // Orange background
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onNavigate("home") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "SETTINGS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Master Volume Slider
                    Text(
                        text = "Master Volume",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Slider(
                        value = masterVolume,
                        onValueChange = { masterVolume = it },
                        valueRange = 0f..1f,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Music Volume Slider
                    Text(
                        text = "Music Volume",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Slider(
                        value = musicVolume,
                        onValueChange = { musicVolume = it },
                        valueRange = 0f..1f,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sound Effects Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = soundEffectsEnabled,
                            onCheckedChange = { soundEffectsEnabled = it }
                        )
                        Text(
                            text = "Sound Effects",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    // Mute All Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = muteAll,
                            onCheckedChange = { muteAll = it }
                        )
                        Text(
                            text = "Mute All",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Show Notifications Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = showNotifications,
                            onCheckedChange = { showNotifications = it }
                        )
                        Text(
                            text = "Show Notifications",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Replay Tutorial
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Replay Tutorial",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { /* Handle replay tutorial */ }) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play Tutorial",
                                tint = Color(0xFFFFA500)
                            )
                        }
                    }


                    Spacer(modifier = Modifier.weight(1f)) // Push links to bottom

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    // Contact Support Link
                    Text(
                        text = "Contact Support",
                        fontSize = 16.sp,
                        color = Color(0xFFFFA500),
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { /* Handle contact support click */ }
                    )

                    // Terms of Use Link
                    Text(
                        text = "Terms of Use",
                        fontSize = 16.sp,
                        color = Color(0xFFFFA500),
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { /* Handle terms of use click */ }
                    )

                    // Privacy Policy Link
                    Text(
                        text = "Privacy Policy",
                        fontSize = 16.sp,
                        color = Color(0xFFFFA500),
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { /* Handle privacy policy click */ }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    AppUITheme {
        Settings(onNavigate = {})
    }
}