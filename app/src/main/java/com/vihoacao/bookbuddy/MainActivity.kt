package com.vihoacao.bookbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vihoacao.bookbuddy.ui.theme.BookBuddyTheme
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RectangleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vihoacao.bookbuddy.data.User
import com.vihoacao.bookbuddy.viewmodel.BookBuddyViewModel
import com.vihoacao.bookbuddy.views.AppNavigation

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            BookBuddyTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainLayout(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MainLayout(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier.fillMaxSize()
//    ) {
//        // Top Divider with Spacing
//        Column(modifier = Modifier.fillMaxWidth()) {
//            Spacer(modifier = Modifier.height(65.dp))
//            HorizontalDivider(
//                modifier = Modifier.fillMaxWidth(),
//                thickness = 3.dp,
//                color = Color(0xFF6D4C41) // Brown color
//            )
//        }
//
//        // Centered Text Content
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "BookBuddy",
//                fontSize = 32.sp,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Text(
//                text = "Start your journey with books",
//                fontSize = 16.sp,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//
//        // Bottom Divider with Spacing
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//        ) {
//            HorizontalDivider(
//                modifier = Modifier.fillMaxWidth(),
//                thickness = 3.dp,
//                color = Color(0xFF6D4C41) // Brown color
//            )
//            Spacer(modifier = Modifier.height(65.dp))
//        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMainLayout() {
//    BookBuddyTheme {
//        MainLayout()
//    }
//}


class MainActivity : ComponentActivity() {
    private val bookBuddyViewModel: BookBuddyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookBuddyTheme {
                AppNavigation()
                //code for other tasks here...
                //for example: insert a User record into User_Table
                //bookBuddyViewModel.insert(User(username = "Tony", score = 100, duration = 50, date = 10000L))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    BookBuddyTheme {
        AppNavigation()
    }
}
