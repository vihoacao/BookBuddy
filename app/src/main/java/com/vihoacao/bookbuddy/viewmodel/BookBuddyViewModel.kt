package com.vihoacao.bookbuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vihoacao.bookbuddy.data.BookBuddyDB
import com.vihoacao.bookbuddy.data.BookBuddyDao
import com.vihoacao.bookbuddy.data.User
import kotlinx.coroutines.launch

class BookBuddyViewModel(application: Application) : AndroidViewModel(application) {
    private val bookBuddyDao: BookBuddyDao = BookBuddyDB.getDatabase(application).bookBuddyDao()

    // Retrieve all User records as LiveData for UI observation
    val allUsers: LiveData<List<User>> = bookBuddyDao.getAllUsersLiveData()

    // Insert a new user
    fun insert(user: User) = viewModelScope.launch {
        bookBuddyDao.insertUser(user)
    }

    // Update an existing user
    fun updateUser(user: User) = viewModelScope.launch {
        bookBuddyDao.updateUser(user)
    }

    // Get a user by ID
    fun getUserById(id: Int, callback: (User?) -> Unit) = viewModelScope.launch {
        val user = bookBuddyDao.getUserById(id)
        callback(user)
    }

    // Delete all users
    fun deleteAllUsers() = viewModelScope.launch {
        bookBuddyDao.deleteAllUsers()
    }
}
