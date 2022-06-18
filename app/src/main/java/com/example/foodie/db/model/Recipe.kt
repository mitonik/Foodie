package com.example.foodie.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val recipeId: Int,
    val name: String,
    val proteins: Int?,
    val fats: Int?,
    val carbons: Int?,
    val calories: Int?,
    val isFavourite: Boolean,
    val description: String?,
    val imagepath: String?
)