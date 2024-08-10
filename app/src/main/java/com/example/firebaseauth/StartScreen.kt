package com.example.firebaseauth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.example.firebaseauth.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.navigation.Routes

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "BioMonitor",
            color = Color(0xFF440785),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.download),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier

                .height(243.dp)
                .width(288.dp)
        )

        Text(
            text = "Monitoring Your Health with Precision",
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center ,
            modifier = Modifier
                .offset(y = -50.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate(Routes.CHOOSE)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF440785)),
            modifier = Modifier
                .offset(y = -50.dp) // Adjust the vertical position here
                .height(72.dp)
                .width(192.dp)
        ) {
            Text(
                text = "Get Started",
                color = Color.White
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun StartScreenPreview(){
    val navController = rememberNavController()
    StartScreen(navController = navController)
}