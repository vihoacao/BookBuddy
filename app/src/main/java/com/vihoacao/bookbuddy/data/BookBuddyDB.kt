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
                // A temp variable so our callback can access the newly created DB instance
                lateinit var tempInstance: BookBuddyDB

                // Define the callback that runs after the database is created
                val prePopulateCallback = object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Insert sample data on a background thread
                        CoroutineScope(Dispatchers.IO).launch {
                            tempInstance.bookBuddyDao().apply {
                                // Insert sample users
                                insertUser(User(name = "Alice", email = "alice@example.com", password = "1"))
                                insertUser(User(name = "Bob", email = "bob@example.com", password = "2"))
                                // Insert categories
                                insertCategory(Category(name = "Fiction"))
                                insertCategory(Category(name = "Drama"))
                                insertCategory(Category(name = "Classic"))
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
                                        bookImage = "https://www.harryhartog.com.au/cdn/shop/products/9781447294832.jpg?v=1677551418&width=480"
                                    )
                                )
                                insertBook(
                                    Book(
                                        name = "Never Let Me Go",
                                        category = "Fiction",
                                        author = "Kazuo Ishiguro",
                                        description = "A deeply emotional novel.",
                                        bookImage = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1353048590i/6334.jpg"
                                    )
                                )
                                insertBook(
                                    Book(
                                        name = "The Bell Jar",
                                        category = "Classic",
                                        author = "Sylvia Path",
                                        description = "A deeply emotional novel.",
                                        bookImage = "https://m.media-amazon.com/images/I/81r5tri1zXL._UF894,1000_QL80_.jpg"
                                    )
                                )
                            }
                        }
                    }
                }

                // Build the database, attaching the callback
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BookBuddyDB::class.java,
                    "bookbuddy_database"
                )
                    .addCallback(prePopulateCallback)
                    .fallbackToDestructiveMigration()
                    .build()

                // Now that it's built, assign it to tempInstance so the callback can see it
                tempInstance = dbInstance

                // Store in the singleton INSTANCE
                INSTANCE = dbInstance

                // Return the newly created instance
                dbInstance
            }
        }
    }
}
