@file:OptIn(ExperimentalMaterial3Api::class)

package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun BookDetailScreen(
    bookId: Int,
    navController: NavController,
    bookBuddyViewModel: BookBuddyViewModel
) {
    var book by remember { mutableStateOf<Book?>(null) }

    // Fetch the book when this screen is first composed
    LaunchedEffect(bookId) {
        bookBuddyViewModel.getBookById(bookId) { fetchedBook ->
            book = fetchedBook
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (book == null) {
            // Show a loading indicator while the book data is being fetched
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            BookDetailContent(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                book = book!!
            )
        }
    }
}

@Composable
private fun BookDetailContent(modifier: Modifier = Modifier, book: Book) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Book cover image
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            AsyncImage(
                model = book.bookImage,
                contentDescription = book.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
                    .clip(MaterialTheme.shapes.medium),
                placeholder = AsyncImagePainter.State.Empty.painter,
                error = AsyncImagePainter.State.Empty.painter
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title & author
        Text(
            text = book.name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "by ${book.author}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Category + format (hardcoded)
        Text(
            text = "${book.category} • Hardcover",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // About section
        Text(
            text = "About",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // My reviews (placeholder)
        Text(
            text = "My reviews:",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "I have not yet written a review for this book. " +
                    "You can add logic here to display user reviews or your own notes.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Center the two buttons, space by 16dp, set custom color
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Mark as read logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57))
            ) {
                Text("Mark as Read", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Write/Edit review logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F5C57))
            ) {
                Text("Write/Edit review", color = Color.White)
            }
        }
    }
}
