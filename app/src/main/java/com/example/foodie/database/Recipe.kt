package com.example.foodie.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val description: String?,
    val isFavourite: Boolean?
)