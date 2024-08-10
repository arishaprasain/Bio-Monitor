package com.example.firebaseauth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.ChooseScreen
import com.example.firebaseauth.StartScreen
import com.example.firebaseauth.login_screen.SignInScreen
import com.example.firebaseauth.sign_up.SignUpScreen

@Composable
fun navigationgraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Routes.START,
        builder = {
            composable(Routes.SIGN_UP) {
                SignUpScreen()
            }

            composable(Routes.SIGN_IN) {
                SignInScreen()
            }

            composable(Routes.START) {
                StartScreen(navController)
            }

            composable(Routes.CHOOSE) {
                ChooseScreen(navController)
            }

        })

}


