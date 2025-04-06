@file:OptIn(ExperimentalMaterial3Api::class)

package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteBookScreen(
    navController: NavController,
    bookBuddyViewModel: BookBuddyViewModel
) {
    val allBooks = bookBuddyViewModel.allBooks.observeAsState(emptyList()).value

    // A stateful SnackbarHost to show notifications
    val snackbarHostState = remember { SnackbarHostState() }
    // A coroutine scope for launching snack bar actions
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Delete Books") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) } // Attach our SnackbarHost here
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Select a book to delete",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Table header: Title | Category | (Delete button)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Title",
                    modifier = Modifier.weight(0.6f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = "Category",
                    modifier = Modifier.weight(0.2f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.weight(0.2f)) // For Delete button column
            }
            Divider()

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(allBooks) { book ->
                    DeleteBookRow(
                        book = book,
                        navController = navController,
                        bookBuddyViewModel = bookBuddyViewModel,
                        snackbarHostState = snackbarHostState,
                        scope = scope
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
fun DeleteBookRow(
    book: Book,
    navController: NavController,
    bookBuddyViewModel: BookBuddyViewModel,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title + cover column
        Row(
            modifier = Modifier.weight(0.6f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = book.bookImage,
                contentDescription = book.name,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = book.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
        }
        // Category column
        Text(
            text = book.category,
            modifier = Modifier.weight(0.2f),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
        // Delete button column
        Button(
            onClick = {
                bookBuddyViewModel.deleteBookById(book.id)
                scope.launch {
                    snackbarHostState.showSnackbar("Book successfully deleted")
                }
            },
            modifier = Modifier
                .weight(0.2f)
                .height(36.dp), // control button height
            shape = RoundedCornerShape(0.dp), // square corners
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp) // reduce internal padding
        ) {
            Text("Delete", color = Color.White, fontSize = 12.sp)
        }
    }
}
