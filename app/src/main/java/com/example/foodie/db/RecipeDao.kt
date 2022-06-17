package com.example.foodie.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun loadRecipeById(id: Int): Flow<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(vararg recipes: Recipe)

    @Update
    suspend fun updateRecipes(vararg recipes: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)
}