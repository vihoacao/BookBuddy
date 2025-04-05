package com.vihoacao.bookbuddy.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "home") {
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            HomeScreen(navController = navController, bookBuddyViewModel = bookBuddyViewModel)
        }
        composable(route = "register") {
            RegisterScreen(navController)
        }
        // New route for BookDetailScreen, accepting a bookId as argument.
        composable(
            route = "bookDetailScreen/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            // Get the ViewModel instance, or you could pass it via a higher scope.
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            BookDetailScreen(
                bookId = bookId,
                navController = navController,
                bookBuddyViewModel = bookBuddyViewModel
            )
        }
    }
}
