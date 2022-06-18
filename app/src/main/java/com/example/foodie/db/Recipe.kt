package com.example.foodie.db

import androidx.room.*
import androidx.room.Relation

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

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val ingredientId: Int,
    val name: String,
    val isSpice: Boolean
)

@Entity(
    primaryKeys = ["ingredient_id", "recipe_id"]
)
data class Relation(
    val ingredient_id: Int,
    val recipe_id: Int,
    val quantity: String?
)

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