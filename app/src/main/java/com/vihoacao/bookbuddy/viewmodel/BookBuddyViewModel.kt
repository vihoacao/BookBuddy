package com.vihoacao.bookbuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vihoacao.bookbuddy.data.*
import kotlinx.coroutines.launch

class BookBuddyViewModel(application: Application) : AndroidViewModel(application) {
    private val bookBuddyDao: BookBuddyDao = BookBuddyDB.getDatabase(application).bookBuddyDao()

    // ---------- USER METHODS ----------
    val allUsers: LiveData<List<User>> = bookBuddyDao.getAllUsersLiveData()

    fun insertUser(user: User) = viewModelScope.launch {
        bookBuddyDao.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        bookBuddyDao.updateUser(user)
    }

    fun getUserById(id: Int, callback: (User?) -> Unit) = viewModelScope.launch {
        val user = bookBuddyDao.getUserById(id)
        callback(user)
    }

    fun deleteAllUsers() = viewModelScope.launch {
        bookBuddyDao.deleteAllUsers()
    }

    // New: Login function that verifies user credentials.
    fun loginUser(email: String, password: String, callback: (User?) -> Unit) = viewModelScope.launch {
        val user = bookBuddyDao.loginUser(email, password)
        callback(user)
    }

    // ---------- BOOK METHODS ----------
    // Retrieve all books
    val allBooks: LiveData<List<Book>> = bookBuddyDao.getAllBooks()

    // Insert a new book
    fun insertBook(book: Book) = viewModelScope.launch {
        bookBuddyDao.insertBook(book)
    }

    // Get book by ID
    fun getBookById(id: Int, callback: (Book?) -> Unit) = viewModelScope.launch {
        val book = bookBuddyDao.getBookById(id)
        callback(book)
    }

    fun getBooksByCategory(categoryName: String): LiveData<List<Book>> {
        return bookBuddyDao.getBooksByCategory(categoryName)
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        bookBuddyDao.updateBook(book)
    }

    fun deleteBookById(id: Int) = viewModelScope.launch {
        bookBuddyDao.deleteBookById(id)
    }

    // ---------- CATEGORY METHODS ----------
    val allCategories: LiveData<List<Category>> = bookBuddyDao.getAllCategories()

    fun insertCategory(category: Category) = viewModelScope.launch {
        bookBuddyDao.insertCategory(category)
    }

    fun deleteCategory(name: String) = viewModelScope.launch {
        bookBuddyDao.deleteCategory(name)
    }
}
