package com.example.foodie.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded
    val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entity = Ingredient::class,
        entityColumn = "ingredientId",
        associateBy = Junction(
            value = Relation::class,
            parentColumn = "recipe_id",
            entityColumn = "ingredient_id"
        )
    )
    val ingredients: List<Ingredient>
)