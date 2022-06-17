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

    suspend fun insertRecipes(recipe: Recipe) {
        recipeDao.insertRecipes(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.delete(recipe)
    }

    suspend fun updateRecipes(recipe: Recipe) {
        recipeDao.updateRecipes(recipe)
    }
}