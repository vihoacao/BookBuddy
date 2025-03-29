package com.vihoacao.bookbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Room Database: Create the database instance.

@Database(
    entities = [User::class, Book::class, Category::class],
    version = 2, // Updated version to 2 for schema changes
    exportSchema = false
)
abstract class BookBuddyDB : RoomDatabase() {
    abstract fun bookBuddyDao(): BookBuddyDao

    companion object {
        @Volatile
        private var INSTANCE: BookBuddyDB? = null

        fun getDatabase(context: Context): BookBuddyDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookBuddyDB::class.java,
                    "bookbuddy_database"
                )
                    .fallbackToDestructiveMigration() // Handles migration safely by recreating the database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
