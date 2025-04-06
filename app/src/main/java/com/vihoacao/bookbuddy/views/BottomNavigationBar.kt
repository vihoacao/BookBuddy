package com.vihoacao.bookbuddy.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Use the currentRoute helper defined in AppNavigation.kt
    val currentRoute = currentRoute(navController)
    NavigationBar(
        containerColor = Color(0xFFF5F1EC),
        tonalElevation = 0.dp
    ) {
        // Home item
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6F5C57),
                unselectedIconColor = Color(0xFF6F5C57),
                selectedTextColor = Color(0xFF6F5C57),
                unselectedTextColor = Color(0xFF6F5C57),
                indicatorColor = Color(0xFFF5F1EC)
            )
        )
        // Search item
        NavigationBarItem(
            selected = currentRoute == "searchScreen",
            onClick = { navController.navigate("searchScreen") },
            icon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            label = { Text("Search") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6F5C57),
                unselectedIconColor = Color(0xFF6F5C57),
                selectedTextColor = Color(0xFF6F5C57),
                unselectedTextColor = Color(0xFF6F5C57),
                indicatorColor = Color(0xFFF5F1EC)
            )
        )
        // My Books item
        NavigationBarItem(
            selected = currentRoute == "myBookScreen",
            onClick = { navController.navigate("myBookScreen") },
            icon = {
                Icon(imageVector = Icons.Default.MenuBook, contentDescription = "My Books")
            },
            label = { Text("My Books") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF6F5C57),
                unselectedIconColor = Color(0xFF6F5C57),
                selectedTextColor = Color(0xFF6F5C57),
                unselectedTextColor = Color(0xFF6F5C57),
                indicatorColor = Color(0xFFF5F1EC)
            )
        )
    }
}
