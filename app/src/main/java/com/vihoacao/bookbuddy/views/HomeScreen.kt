package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun HomeScreen(
    navController: NavController? = null,
    bookBuddyViewModel: BookBuddyViewModel
) {
    // Observe all books from the ViewModel.
    val books = bookBuddyViewModel.allBooks.observeAsState(initial = emptyList()).value

    // Filter books by category.
    val dramaBooks = books.filter { it.category.equals("Drama", ignoreCase = true) }
    val fictionBooks = books.filter { it.category.equals("Fiction", ignoreCase = true) }

    // Make the entire screen scrollable
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
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
        Spacer(modifier = Modifier.height(16.dp))

        // Currently Reading section (all books)
        SectionTitle("Currently Reading")
        BookRow(books = books, navController = navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Drama section
        SectionTitle("Drama")
        BookRow(books = dramaBooks, navController = navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Fiction section
        SectionTitle("Fiction")
        BookRow(books = fictionBooks, navController = navController)
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

@Composable
fun BookRow(books: List<Book>, navController: NavController? = null) {
    LazyRow {
        items(books) { book ->
            BookItem(book = book, navController = navController)
        }
    }
}

@Composable
fun BookItem(book: Book, navController: NavController? = null) {
    Column(
        modifier = Modifier
            .width(120.dp) // Keep original width
            .padding(8.dp)
            .clickable { navController?.navigate("bookDetailScreen/${book.id}") },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Remove card shadow by setting 0 dp elevation and transparent container
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            AsyncImage(
                model = book.bookImage,
                contentDescription = book.name,
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        // Slightly reduce line spacing by specifying lineHeight close to fontSize
        Text(
            text = book.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            style = TextStyle(lineHeight = 16.sp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "by ${book.author}",
            fontSize = 12.sp,
            style = TextStyle(lineHeight = 14.sp),
            color = Color.Gray
        )
    }
}
