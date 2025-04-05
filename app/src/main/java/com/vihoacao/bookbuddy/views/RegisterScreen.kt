package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vihoacao.bookbuddy.data.User
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun RegisterScreen(
    navController: NavController? = null,
    bookBuddyViewModel: BookBuddyViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("BookBuddy", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Start your journey with books", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password:") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = repassword,
                onValueChange = { repassword = it },
                label = { Text("Confirm your Password:") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(12.dp))
            }
            Button(
                onClick = {
                    // Validate input
                    if (name.isBlank() || email.isBlank() || password.isBlank() || repassword.isBlank()) {
                        errorMessage = "All fields are required."
                    } else if (password != repassword) {
                        errorMessage = "Passwords do not match."
                    } else {
                        // Insert the new user into the database
                        bookBuddyViewModel.insertUser(User(name = name, email = email, password = password))
                        // Clear error message
                        errorMessage = ""
                        // Navigate to home
                        navController?.navigate("home") {
                            // Optionally clear backstack
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57))
            ) {
                Text("Sign Up", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
