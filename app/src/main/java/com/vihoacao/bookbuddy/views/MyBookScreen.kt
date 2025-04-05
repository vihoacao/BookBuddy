@file:OptIn(ExperimentalMaterial3Api::class)

package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MyBookScreen(
    navController: NavController? = null,
    bookBuddyViewModel: BookBuddyViewModel
) {
    // Observe all books
    val allBooks = bookBuddyViewModel.allBooks.observeAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My books") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Header row: My books count with Edit and Add buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My books: ${allBooks.size}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                // Row for action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Edit",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.clickable {
                            // Handle Edit action here
                        }
                    )
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.clickable {
                            // Navigate to your AddBookScreen (or handle add action)
                            navController?.navigate("addBookScreen")
                        }
                    )
                }
            }

            // Table header: Title | Categories | Date
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Title",
                    modifier = Modifier.weight(0.5f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = "Categories",
                    modifier = Modifier.weight(0.2f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = "Date",
                    modifier = Modifier.weight(0.3f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Divider()

            // Table content: list of books
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(allBooks) { book ->
                    BookTableRow(book = book, navController = navController)
                    Divider()
                }
            }

            // Pagination row (placeholder)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "1 2 3 4 …", color = Color.Gray)
            }
        }
    }
}

@Composable
fun BookTableRow(book: Book, navController: NavController?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title column: thumbnail + title text
        Row(
            modifier = Modifier
                .weight(0.5f)
                .clickable { navController?.navigate("bookDetailScreen/${book.id}") },
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

        // Categories column
        Text(
            text = book.category,
            modifier = Modifier.weight(0.2f),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )

        // Date column
        Text(
            text = formatDate(book.dateAdded),
            modifier = Modifier.weight(0.3f),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
    }
}

/**
 * Helper function to format a timestamp (Long) into a readable date string.
 */
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
