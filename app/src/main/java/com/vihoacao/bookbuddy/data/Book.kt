package com.vihoacao.bookbuddy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "book_table",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["name"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE // Ensures category deletion removes related books
        )
    ]
)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(index = true) // Index for faster category lookups
    val category: String,
    val author: String,
    val description: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val dateAdded: Long = System.currentTimeMillis(),
    val bookImage: String // Store URL or URI of the book cover image
)
