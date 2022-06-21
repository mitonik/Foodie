package com.example.foodie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.db.dao.RecipeDao
import com.example.foodie.db.model.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class FoodieDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}