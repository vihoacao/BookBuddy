package com.vihoacao.bookbuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vihoacao.bookbuddy.data.BookBuddyDB
import com.vihoacao.bookbuddy.data.BookBuddyDao
import com.vihoacao.bookbuddy.data.User
import kotlinx.coroutines.launch

class BookBuddyViewModel(application: Application): AndroidViewModel(application) {
    private val bookBuddyDao: BookBuddyDao = BookBuddyDB.getDatabase(application).bookBuddyDao()
    //retrieve all User records
    val allUsers: List<User> = bookBuddyDao.getAllUsers()
    //variables from user input should be defined here
    //...

    fun insert(user: User) = viewModelScope.launch {
        bookBuddyDao.insert(user)
    }

}