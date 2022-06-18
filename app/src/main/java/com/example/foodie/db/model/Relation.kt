package com.example.foodie.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    primaryKeys = ["ingredient_id", "recipe_id"]
)
data class Relation(
    val ingredient_id: Int,
    val recipe_id: Int,
    val quantity: String?
)



