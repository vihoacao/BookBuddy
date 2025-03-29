package com.vihoacao.bookbuddy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey
    val name: String // Example values: "Read", "Reading", "To-Be Read"
)
