package com.example.foodie.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val recipeId: Int,
    val name: String,
    val proteins: String?,
    val fats: String?,
    val carbons: String?,
    val calories: String?,
    val isFavourite: Boolean?,
    val description: String?,
    val imagePath: String?
)