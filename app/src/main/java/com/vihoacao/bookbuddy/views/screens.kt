@file:OptIn(ExperimentalMaterial3Api::class)

package com.vihoacao.bookbuddy.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import  androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vihoacao.bookbuddy.R
import com.vihoacao.bookbuddy.ui.theme.BookBuddyTheme

//define app navigation
@Composable
fun AppNavigation(){
    //retreat navController
    val navController = rememberNavController()
    //set navHost and the routes
    NavHost(navController, startDestination = "home") {
        composable(route = "home") { HomeScreen(navController)}
        composable(route = "second") { SecondScreen(navController)}
    }
}

@Composable
fun HomeScreen(navController: NavController,modifier: Modifier=Modifier) {
    val context = LocalContext.current
    Scaffold(topBar = { TopAppBar(title = { Text("Home Screen")})}) {
        padding ->
        Column(modifier.fillMaxSize().padding(padding).padding(16.dp)

            ) {
            Button(onClick = {
                Toast.makeText(context, "Click me!", Toast.LENGTH_SHORT).show()
                navController.navigate(route = "second")
            }) {
                Text(text = "Click me")
            }
            Image(painter = painterResource(R.drawable.dice_1),
                contentDescription = "Dice 1",
                modifier.size(100.dp)
               .clickable { navController.navigate(route = "second") }
            )
        }
    }
}

@Composable
fun SecondScreen(navController: NavController,modifier: Modifier = Modifier){
    Scaffold(topBar = { TopAppBar(title = { Text("Second Screen")})}) {
            padding ->
        Column(modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Button(onClick = {navController.navigate(route = "home")}) {
                Text(text = "Click me again!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun  ScreenPreview() {
    BookBuddyTheme {
        AppNavigation()
    }
}