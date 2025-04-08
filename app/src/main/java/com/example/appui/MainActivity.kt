package com.example.appui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appui.ui.theme.AppUITheme
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "title_screen") {
        composable("title_screen") { TitleScreen(onNavigate = { navController.navigate("next_screen") }) }
        composable("next_screen") { NextScreen() }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppUITheme {
                    AppNavigation()
            }
        }
    }
}

@Composable
fun TitleScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    var image = painterResource(R.drawable.background_image)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate() } // Détecte les clics sur l'écran
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Name()
            Spacer(modifier = Modifier.weight(2f))
            PlayText()
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun Name(modifier: Modifier = Modifier) {
    Column (modifier = modifier

        .padding(vertical =4.dp ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Game Title !",
            modifier = modifier
                .border(2.dp, Color.White)

                .padding(vertical = 10.dp, horizontal = 20.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

    }
}

@Composable
fun PlayText(modifier : Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }
    val color by animateColorAsState(
        targetValue = if (visible) Color.White else Color.Transparent,
        animationSpec = tween(
            durationMillis = 1000, // Durée de l'animation en millisecondes
            easing = LinearEasing
        ))

    LaunchedEffect(Unit) {
        while (true){
            delay(1000)// Durée entre chaque début d'animation
            visible = !visible
        }
    }

    Text(
        text = "Tap to Play",
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 20.dp),
        color = color,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppUITheme {
        AppNavigation()
    }
}