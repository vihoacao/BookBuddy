package com.vihoacao.bookbuddy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Create a data class representing a table in the database.
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val dateCreated: Long = System.currentTimeMillis()
)
