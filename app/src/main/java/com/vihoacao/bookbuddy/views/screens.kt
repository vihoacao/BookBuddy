//@file:OptIn(ExperimentalMaterial3Api::class)
//
package com.vihoacao.bookbuddy.views
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import com.vihoacao.bookbuddy.ui.theme.BookBuddyTheme
//
//@Composable
//fun LoginScreen(onLoginClick: (String, String) -> Unit) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "BookBuddy",
//            fontSize = 32.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xFF4A3B36)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = "Start your journey with books")
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Email Field
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email:", fontStyle = androidx.compose.ui.text.font.FontStyle.Italic) },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Password Field
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password:", fontStyle = androidx.compose.ui.text.font.FontStyle.Italic) },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Login Button
//        Button(
//            onClick = { onLoginClick(email, password) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D6553))
//        ) {
//            Text(text = "Login", color = Color.White)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    BookBuddyTheme {
//        LoginScreen { _, _ -> }
//    }
//}
