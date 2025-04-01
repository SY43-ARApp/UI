package com.example.appui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appui.ui.theme.AppUITheme
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TitleScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TitleScreen(modifier : Modifier = Modifier){
    var image = painterResource(R.drawable.background_image)
    Box()
    {
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
            Spacer(modifier = modifier.weight(2f))
            PlayText()
            Spacer(modifier = modifier.weight(1f))
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
    val color by animateColorAsState(if (visible) Color.White else Color.Transparent)

    LaunchedEffect(Unit) {//* TODO : ralentir le fade de l'animation
        while (true){
            delay(500)
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
        TitleScreen()
    }
}