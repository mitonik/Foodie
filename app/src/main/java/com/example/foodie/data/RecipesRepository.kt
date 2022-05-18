package com.example.foodie.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class RecipesRepository(private val recipesDao: RecipesDao) {
    val allRecipes: LiveData<List<Recipes>> = recipesDao.getRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(name: Recipes) {
        recipesDao.insert(name)
    }
}