package com.example.foodie.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class IngredientWithRecipes(
    @Embedded
    val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entity = Recipe::class,
        entityColumn = "recipeId",
        associateBy = Junction(
            value = Relation::class,
            parentColumn = "ingredient_id",
            entityColumn = "recipe_id"
        )
    )
    val recipes: List<Recipe>
)