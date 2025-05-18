package com.example.appui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ShouldPauseCallback
import androidx.compose.ui.platform.LocalContext


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "title_screen") {
        composable("title_screen") { TitleScreenWithParallax(onNavigate = { route -> navController.navigate(route) }) }
        composable("home") { Home(onNavigate = { route -> navController.navigate(route) }) }
        composable("leaderboard") { Leaderboard(onNavigate = { route -> navController.navigate(route) }) }
        composable("profile") { Profile(onNavigate = {route -> navController.navigate(route)}) }
        composable("game") { GameScreen(onNavigate = { route -> navController.navigate(route) }) }
        composable("shop") { Shop(onNavigate = { route -> navController.navigate(route) }) }
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
fun TitleScreen(modifier: Modifier = Modifier, onNavigate: (String) -> Unit) {
    val image = painterResource(R.drawable.background_image)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {  onNavigate("home")  } // Détecte les clics sur l'écran
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
fun TitleScreenWithParallax(modifier: Modifier = Modifier, onNavigate: (String) -> Unit) {
    val context = LocalContext.current

    // États pour la position de l'image de fond
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }

    // Configuration du capteur
    DisposableEffect(key1 = context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
               // Calculer le déplacement horizontal en fonction de l'inclinaison du téléphone sur l'axe X
               // La valeur est multipliée par 1.5 pour amplifier l'effet et limitée entre -30 et 30 pour éviter un déplacement excessif
               offsetX.value = (-event.values[0] * 3.5f).coerceIn(-300f, 300f)

               // Calculer le déplacement vertical en fonction de l'inclinaison du téléphone sur l'axe Y
               // La valeur est multipliée par 1.5 pour amplifier l'effet et limitée entre -30 et 30 pour éviter un déplacement excessif
               offsetY.value = (event.values[1] * 3.5f).coerceIn(-300f, 300f)
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }

        // Enregistrer le listener
        sensorManager.registerListener(
            sensorListener,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_GAME
        )

        // Nettoyer quand le composable est détruit
        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate("home") }
    ) {
        // Image d'arrière-plan avec effet parallaxe
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = offsetX.value.dp, y = offsetY.value.dp)
        )

        // Contenu au premier plan (reste statique)
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
    val image = painterResource(R.drawable.titre)
    Column (modifier = modifier

        .padding(vertical =4.dp ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(350.dp)
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