package com.vihoacao.bookbuddy

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vihoacao.bookbuddy.data.BookBuddyDB
import com.vihoacao.bookbuddy.data.BookBuddyDao
import com.vihoacao.bookbuddy.data.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class BookBuddyTest {
    private lateinit var db: BookBuddyDB
    private lateinit var bookBuddyDao: BookBuddyDao

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(appContext, BookBuddyDB::class.java)
            .allowMainThreadQueries()
            .build()
        bookBuddyDao = db.bookBuddyDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetUser() = runBlocking {
        val user = User(name = "Tony", email = "tony@example.com", password = "password123")
        bookBuddyDao.insertUser(user)
        val fetchedUser = bookBuddyDao.getUserById(1)
        assertEquals("Tony", fetchedUser?.name)
    }

    @Test
    fun deleteAllUsers() = runBlocking {
        val user = User(name = "Sarah", email = "sarah@example.com", password = "pass456")
        bookBuddyDao.insertUser(user)
        bookBuddyDao.deleteAllUsers()
        val fetchedUser = bookBuddyDao.getUserById(1)
        assertNull(fetchedUser)
    }

    @Test
    fun getAllUsers() = runBlocking {
        val user1 = User(name = "Sarah", email = "sarah@example.com", password = "pass456")
        val user2 = User(name = "Tony", email = "tony@example.com", password = "password123")
        bookBuddyDao.insertUser(user1)
        bookBuddyDao.insertUser(user2)

        val allUsers = bookBuddyDao.getAllUsers()
        assertEquals(2, allUsers.size)
        assertEquals("Sarah", allUsers[0].name)
        assertEquals("Tony", allUsers[1].name)
    }
}
