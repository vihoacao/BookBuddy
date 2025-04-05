package com.vihoacao.bookbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Book::class, Category::class],
    version = 3, // Updated version for schema changes
    exportSchema = false
)
abstract class BookBuddyDB : RoomDatabase() {
    abstract fun bookBuddyDao(): BookBuddyDao

    companion object {
        @Volatile
        private var INSTANCE: BookBuddyDB? = null

        fun getDatabase(context: Context): BookBuddyDB {
            return INSTANCE ?: synchronized(this) {
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BookBuddyDB::class.java,
                    "bookbuddy_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Pre-populate the database on creation
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.bookBuddyDao()?.apply {
                                    // Insert sample users
                                    insertUser(User(name = "Alice", email = "alice@example.com", password = "1"))
                                    insertUser(User(name = "Bob", email = "bob@example.com", password = "2"))
                                    // Insert categories
                                    insertCategory(Category(name = "Fiction"))
                                    insertCategory(Category(name = "Drama"))
                                    // Insert sample books
                                    insertBook(
                                        Book(
                                            name = "Before the Coffee Gets Cold",
                                            category = "Fiction",
                                            author = "Toshikazu Kawaguchi",
                                            description = "A heartwarming time-travel story.",
                                            bookImage = "https://m.media-amazon.com/images/I/71kW0ESYl5L.jpg"
                                        )
                                    )
                                    insertBook(
                                        Book(
                                            name = "A Little Life",
                                            category = "Drama",
                                            author = "Hanya Yanagihara",
                                            description = "A deeply emotional novel.",
                                            bookImage = "https://m.media-amazon.com/images/I/71kW0ESYl5L.jpg"
                                        )
                                    )
                                }
                            }
                        }
                    })
                    .fallbackToDestructiveMigration() // Recreates the database if no migration is provided
                    .build()
                INSTANCE = dbInstance
                dbInstance
            }
        }
    }
}
