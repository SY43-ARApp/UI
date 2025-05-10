package com.example.appui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appui.ui.theme.AppUITheme

@Composable
fun Profile(onNavigate: (String) -> Unit) {
    var image = painterResource(R.drawable.user)
    Column (
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color.LightGray),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.5f))

        Row {
            Spacer(modifier = Modifier.weight(0.5f))

            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(0.2f)
                    .background(androidx.compose.ui.graphics.Color.LightGray)
                    .size(100.dp, 100.dp) // Set the size of the image

            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text="Pseudo")
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {onNavigate("home")}) {
            Text(text = "back to home")
        }
        Spacer(modifier = Modifier.weight(0.5f))

    }




}
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    AppUITheme {
        Profile(onNavigate = {})
    }
}