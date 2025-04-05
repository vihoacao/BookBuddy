package com.vihoacao.bookbuddy.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login") { LoginScreen(navController) }
        composable(route = "home") {
            // Create or retrieve a BookBuddyViewModel instance
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            HomeScreen(navController = navController, bookBuddyViewModel = bookBuddyViewModel)
        }
        composable(route = "register") { RegisterScreen(navController) }
    }
}
