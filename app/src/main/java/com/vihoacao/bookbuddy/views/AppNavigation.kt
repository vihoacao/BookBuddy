package com.vihoacao.bookbuddy.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login") { LoginScreen(navController) }
        //composable(route = "home") { HomeScreen(navController) }
        composable(route = "register") { RegisterScreen(navController) }
    }
}
