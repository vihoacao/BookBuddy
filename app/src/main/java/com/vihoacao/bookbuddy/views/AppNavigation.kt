package com.vihoacao.bookbuddy.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

// Helper function to obtain the current route
@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // You can now use currentRoute(navController) anywhere in this file.

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
        // Route for SearchScreen accepting no arguments
        composable(route = "searchScreen") {
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            SearchScreen(navController, bookBuddyViewModel)
        }
        // Route for BookDetailScreen accepting a bookId argument.
        composable(
            route = "bookDetailScreen/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            BookDetailScreen(
                bookId = bookId,
                navController = navController,
                bookBuddyViewModel = bookBuddyViewModel
            )
        }
        // Route for MyBookScreen
        composable(route = "myBookScreen") {
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            MyBookScreen(
                navController = navController,
                bookBuddyViewModel = bookBuddyViewModel
            )
        }
        // Route for AddNewBookScreen
        composable(route = "addBookScreen") {
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            AddNewBookScreen(navController, bookBuddyViewModel)
        }
        // New route for DeleteBookScreen
        composable(route = "deleteBookScreen") {
            val bookBuddyViewModel: BookBuddyViewModel = viewModel()
            DeleteBookScreen(
                navController = navController,
                bookBuddyViewModel = bookBuddyViewModel
            )
        }
    }
}
