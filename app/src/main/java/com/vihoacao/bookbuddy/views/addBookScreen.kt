package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewBookScreen(
    navController: NavController? = null,
    bookBuddyViewModel: BookBuddyViewModel? = null
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add a new book",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Category
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Author
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Author:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Image URL
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = {
                    // Validate input
                    if (name.isBlank() || category.isBlank() || author.isBlank() ||
                        description.isBlank() || imageUrl.isBlank()
                    ) {
                        errorMessage = "All fields are required."
                    } else {
                        // Insert the new book in a coroutine
                        coroutineScope.launch {
                            bookBuddyViewModel?.insertBook(
                                Book(
                                    name = name,
                                    category = category,
                                    author = author,
                                    description = description,
                                    bookImage = imageUrl
                                )
                            )
                            // Navigate back or to another screen
                            navController?.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57))
            ) {
                Text("Add", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewBookScreenPreview() {
    AddNewBookScreen()
}
