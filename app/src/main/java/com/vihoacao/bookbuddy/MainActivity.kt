package com.vihoacao.bookbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.vihoacao.bookbuddy.ui.theme.BookBuddyTheme
import com.vihoacao.bookbuddy.views.AppNavigation
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel

class MainActivity : ComponentActivity() {
    private val bookBuddyViewModel: BookBuddyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BookBuddyApp()
        }
    }
}

@Composable
fun BookBuddyApp() {
    BookBuddyTheme {
        AppNavigation()
    }
}
