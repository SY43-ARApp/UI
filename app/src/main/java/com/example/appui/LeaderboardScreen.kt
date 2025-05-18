package com.example.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appui.ui.theme.AppUITheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableIntStateOf
import kotlin.toString


@Composable
fun Leaderboard(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500)),
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
                    text = "LEADERBOARD",
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
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                var selectedTabIndex by remember { mutableIntStateOf(0) }
                val tabs = listOf("Global", "Personal")
                
                Column {
                    TabRow(
                        selectedTabIndex = selectedTabIndex
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                text = { Text(title) },
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index }
                            )
                        }
                    }
                    
                    when (selectedTabIndex) {
                        0 -> GlobalLeaderboard()
                        1 -> PersonalLeaderboard()
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardContent(items: List<LeaderboardEntry>, title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            itemsIndexed(items) { index, entry ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color(0xFFEEEEEE)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "#${index + 1}",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA500),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = entry.name,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = entry.score.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA500)
                        )

                        Text(
                            text = entry.date,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreboardPreview() {
    AppUITheme {
        Leaderboard(onNavigate = {})
    }
}


@Composable
fun GlobalLeaderboard() {
    // Create mock data directly as objects instead of parsing JSON
    val mockData = listOf(
        LeaderboardEntry("AlexGamer", 1250, "2023-12-15"),
        LeaderboardEntry("Ninja42", 980, "2023-12-18"),
        LeaderboardEntry("ProPlayer", 875, "2023-12-14"),
        LeaderboardEntry("GameMaster", 820, "2023-12-20"),
        LeaderboardEntry("StarPlayer", 750, "2023-12-16"),
        LeaderboardEntry("SpeedRunner", 690, "2023-12-19"),
        LeaderboardEntry("CasualGamer", 580, "2023-12-17"),
        LeaderboardEntry("Newbie123", 450, "2023-12-21"),
        LeaderboardEntry("YourUsername", 720, "2023-12-13"),
        LeaderboardEntry("FriendlyPlayer", 600, "2023-12-12"),
        LeaderboardEntry("ChillGamer", 500, "2023-12-11"),
        LeaderboardEntry("EpicGamer", 400, "2023-12-09"),
        LeaderboardEntry("ProGamer", 300, "2023-12-07"),
        LeaderboardEntry("NoobMaster", 200, "2023-12-06")
    )

    LeaderboardContent(mockData, "Global Leaderboard")
}

@Composable
fun PersonalLeaderboard() {
    // Create personal history entries
    val mockData = listOf(
        LeaderboardEntry("You", 720, "2023-12-13"),
        LeaderboardEntry("You", 685, "2023-12-10"),
        LeaderboardEntry("You", 590, "2023-12-08"),
        LeaderboardEntry("You", 510, "2023-12-05"),
        LeaderboardEntry("You", 420, "2023-12-01")
    )

    LeaderboardContent(mockData, "Your Scores")
}

data class LeaderboardEntry(
    val name: String,
    val score: Int,
    val date: String
)
