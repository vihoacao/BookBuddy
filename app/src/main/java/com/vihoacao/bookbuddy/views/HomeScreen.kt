package com.vihoacao.bookbuddy.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vihoacao.bookbuddy.data.Book
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

@Composable
fun HomeScreen(navController: NavController? = null, bookBuddyViewModel: BookBuddyViewModel) {
    // Observe the list of books from the ViewModel.
    val books = bookBuddyViewModel.allBooks.observeAsState(initial = emptyList()).value

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome,", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Let's continue your journey", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Currently Reading", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        LazyRow {
            items(books) { book ->
                BookItem(book)
            }
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Column(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = book.bookImage,
            contentDescription = book.name,
            modifier = Modifier.size(120.dp)
        )
        Text(book.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
