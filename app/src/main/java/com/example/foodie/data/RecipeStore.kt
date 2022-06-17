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

    fun loadRecipeById(id: Int): Flow<Recipe> {
        return recipeDao.loadRecipeById(id)
    }

    suspend fun insertRecipes(id: Int, name: String, description: String, isFavourite: Boolean) {
        recipeDao.insertRecipes(Recipe(id, name, description, isFavourite))
    }

    suspend fun deleteRecipe(id: Int, name: String, description: String, isFavourite: Boolean) {
        recipeDao.delete(Recipe(id, name, description, isFavourite))
    }

    suspend fun updateRecipes(id: Int, name: String, description: String, isFavourite: Boolean) {
        recipeDao.updateRecipes(Recipe(id, name, description, isFavourite))
    }
}