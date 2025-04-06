package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    bookBuddyViewModel: BookBuddyViewModel
) {
    // Observe all books from the ViewModel.
    val books = bookBuddyViewModel.allBooks.observeAsState(initial = emptyList()).value

    // Filter books by category: "Drama", "Fiction", and "Classic"
    val dramaBooks = books.filter { it.category.equals("Drama", ignoreCase = true) }
    val fictionBooks = books.filter { it.category.equals("Fiction", ignoreCase = true) }
    val classicBooks = books.filter { it.category.equals("Classic", ignoreCase = true) }

    // Wrap content in a Scaffold to include a bottom bar.
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        // Make the entire content scrollable.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            // Welcome text
            Text(
                text = "Welcome,",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Let's continue your journey",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Drama section
            SectionTitle("Drama")
            BookRow(books = dramaBooks, navController = navController, imageSize = 140.dp)
            Spacer(modifier = Modifier.height(24.dp))

            // Fiction section
            SectionTitle("Fiction")
            BookRow(books = fictionBooks, navController = navController, imageSize = 140.dp)
            Spacer(modifier = Modifier.height(24.dp))

            // Classic section
            SectionTitle("Classic")
            BookRow(books = classicBooks, navController = navController, imageSize = 140.dp)
        }
    }
}

/**
 * A reusable composable for section titles.
 */
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
}

/**
 * A reusable horizontal row of books that accepts an imageSize of type Dp.
 */
@Composable
fun BookRow(
    books: List<Book>,
    navController: NavController,
    imageSize: Dp
) {
    LazyRow {
        items(books) { book ->
            BookItem(book = book, navController = navController, imageSize = imageSize)
        }
    }
}

/**
 * A single book item that displays the book's image, title, and author.
 */
@Composable
fun BookItem(
    book: Book,
    navController: NavController,
    imageSize: Dp
) {
    Column(
        modifier = Modifier
            .width(imageSize)
            .padding(8.dp)
            .clickable { navController.navigate("bookDetailScreen/${book.id}") },
        horizontalAlignment = Alignment.Start
    ) {
        // Book image without card shadow.
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            AsyncImage(
                model = book.bookImage,
                contentDescription = book.name,
                modifier = Modifier.size(imageSize)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        // Book title
        Text(
            text = book.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            style = TextStyle(lineHeight = 16.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(2.dp))
        // Book author
        Text(
            text = "by ${book.author}",
            fontSize = 12.sp,
            style = TextStyle(lineHeight = 14.sp),
            color = Color.Gray
        )
    }
}
