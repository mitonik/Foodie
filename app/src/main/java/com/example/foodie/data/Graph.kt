package com.example.foodie.data

import android.content.Context
import androidx.room.Room
import com.example.foodie.db.FoodieDatabase

object Graph {
    lateinit var database: FoodieDatabase
    val recipeStore by lazy {
        RecipeStore(recipeDao = database.recipeDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, FoodieDatabase::class.java, "foodie")
            .fallbackToDestructiveMigration().build()
    }
}