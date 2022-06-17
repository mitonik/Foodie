package com.example.foodie.data

import com.example.foodie.db.Recipe
import com.example.foodie.db.RecipeDao
import kotlinx.coroutines.flow.Flow

class RecipeStore(
    private val recipeDao: RecipeDao
) {
    fun getAll(): Flow<List<Recipe>> {
        return recipeDao.getAll()
    }

    suspend fun insertRecipes() {
        recipeDao.insertRecipes(Recipe(0, "Recipe Name", "Custom Description", true))
    }
}