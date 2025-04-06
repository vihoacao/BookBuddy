@file:OptIn(ExperimentalLayoutApi::class)

package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavController,
    bookBuddyViewModel: BookBuddyViewModel
) {
    // Observe categories from the database.
    val categories = bookBuddyViewModel.allCategories.observeAsState(emptyList()).value

    var searchQuery by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F1EC))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // "Search" button
            Button(
                onClick = {
                    // Clear previous error
                    errorMessage = ""
                    if (searchQuery.isNotBlank()) {
                        coroutineScope.launch {
                            try {
                                bookBuddyViewModel.findBookByName(searchQuery) { book ->
                                    if (book != null) {
                                        navController.navigate("bookDetailScreen/${book.id}")
                                    } else {
                                        errorMessage = "Book not found"
                                    }
                                }
                            } catch (e: Exception) {
                                errorMessage = "An error occurred: ${e.message}"
                            }
                        }
                    } else {
                        errorMessage = "Please enter a book name"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57))
            ) {
                Text("Search", color = Color.White)
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Categories title
            Text(
                text = "Categories",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Categories in a FlowRow
            FlowRow(
                mainAxisSpacing = 12.dp,
                crossAxisSpacing = 12.dp
            ) {
                categories.forEach { category ->
                    CategoryButton(category = category) {
                        // Example action when a category is clicked.
                        errorMessage = "You clicked on category: ${category.name}"
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryButton(category: com.vihoacao.bookbuddy.data.Category, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = category.name, color = Color.White)
    }
}
