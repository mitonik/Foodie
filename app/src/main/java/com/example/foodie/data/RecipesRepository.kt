package com.example.foodie.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class RecipesRepository(private val recipesDao: RecipesDao) {
    val allRecipes: Flow<List<Recipes>> = recipesDao.getRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(name: Recipes) {
        recipesDao.insert(name)
    }
}