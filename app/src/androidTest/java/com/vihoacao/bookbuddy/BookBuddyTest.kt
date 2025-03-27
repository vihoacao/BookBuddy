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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BookBuddyTest {
    private lateinit var db: BookBuddyDB
    private lateinit var bookBuddyDao: BookBuddyDao
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun createDb() {
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(appContext, BookBuddyDB::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        bookBuddyDao = db.bookBuddyDao()
    }
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    //runBlocking:
    //- Blocks the current thread until the coroutine completes.
    //- Used to bridge synchronous and asynchronous code.
    //- Creates a scope for coroutines, controlling their lifetime.
    //Key differences:
    //- suspend defines asynchronous functions.
    //- runBlocking executes coroutines synchronously.
    fun insertAndGetUser() = runBlocking {
        val user = User(username = "Tony", score = 100, duration = 50, date = 10000L)
        bookBuddyDao.insert(user)
        val fetchedUser = bookBuddyDao.getUserById(1)
        assertEquals("Tony", fetchedUser?.username)
    }

    @Test
    fun deleteAllUsers() = runBlocking {
        val user = User(username = "Sarah", score = 80, duration = 70)
        bookBuddyDao.insert(user)
        bookBuddyDao.deleteAllUsers()
        val fetchedUser = bookBuddyDao.getUserById(1)
        assertNull(fetchedUser)
    }

    @Test
    fun getAllUsers() = runBlocking {
        val user1 = User(username = "Sarah", score = 80, duration = 70)
        val user2 = User(username = "Tony", score = 100, duration = 50, date = 10000L)
        bookBuddyDao.insert(user1)
        bookBuddyDao.insert(user2)

        val allUsers = bookBuddyDao.getAllUsers()
        assertEquals(allUsers.size, 2)
        //order by score DESC -> Tony is the first record
        assertEquals(allUsers[0].username, "Tony")
        assertEquals(allUsers[1].username, "Sarah")
    }

}