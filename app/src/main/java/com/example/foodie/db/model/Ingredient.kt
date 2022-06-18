package com.example.foodie.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val ingredientId: Int,
    val name: String,
    val isSpice: Boolean
)