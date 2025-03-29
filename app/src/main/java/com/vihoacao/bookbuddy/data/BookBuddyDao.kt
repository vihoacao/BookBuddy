package com.vihoacao.bookbuddy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookBuddyDao {

    // ---------- USER METHODS ----------
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllUsersLiveData(): LiveData<List<User>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Update
    suspend fun updateUser(user: User)

    // ---------- BOOK METHODS ----------
    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM book_table WHERE id = :id")
    suspend fun getBookById(id: Int): Book?

    @Query("SELECT * FROM book_table ORDER BY dateAdded DESC")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE category = :categoryName")
    fun getBooksByCategory(categoryName: String): LiveData<List<Book>>

    @Query("DELETE FROM book_table WHERE id = :id")
    suspend fun deleteBookById(id: Int)

    @Update
    suspend fun updateBook(book: Book)

    // ---------- CATEGORY METHODS ----------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category_table ORDER BY name ASC")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("DELETE FROM category_table WHERE name = :name")
    suspend fun deleteCategory(name: String)
}
