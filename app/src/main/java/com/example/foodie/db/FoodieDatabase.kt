package com.example.foodie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.db.dao.IngredientDao
import com.example.foodie.db.dao.RecipeDao
import com.example.foodie.db.dao.RelationDao
import com.example.foodie.db.model.Ingredient
import com.example.foodie.db.model.Recipe
import com.example.foodie.db.model.Relation

@Database(entities = [Recipe::class, Ingredient::class, Relation::class], version = 1, exportSchema = false)
abstract class FoodieDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun relationDao(): RelationDao
}