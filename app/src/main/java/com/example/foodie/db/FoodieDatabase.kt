package com.example.foodie.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class, Ingredient::class, Relation::class], version = 1, exportSchema = false)
abstract class FoodieDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun relationDao(): RelationDao
}