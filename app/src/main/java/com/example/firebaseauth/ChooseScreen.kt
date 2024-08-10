package com.example.firebaseauth
import com.example.firebaseauth.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.navigation.Routes

@Composable
fun ChooseScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        //contentAlignment = Layout.Alignment.ALIGN_CENTER
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Tech support for your health stats!",
                color = Color(0xFF000080),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 80.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(229.dp)
                    .width(307.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp)
            )

            Button(
                onClick = {
                    navController.navigate(Routes.SIGN_UP)
                },
                // colors = ButtonDefaults.buttonColors(containerColor = Color.Green),

                modifier = Modifier

                    .width(326.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Button(
                onClick = {
                    navController.navigate(Routes.SIGN_IN)
                },
                //colors = ButtonDefaults.buttonColors(containerColor = Color.Green),

                modifier = Modifier

                    .width(326.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Login as guest",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White // Set text color to White for better visibility on gray background
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Button(
                onClick = {
                    navController.navigate(Routes.SIGN_IN)
                },
                //colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    //.height(64.dp)
                    .width(326.dp)
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White // Set text color to White for better visibility on gray background
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChooseScreenPreview() {
    // Create a dummy NavController for the preview
    val navController = rememberNavController()
    ChooseScreen(navController)
}