package com.example.foodie.db.dao

import androidx.room.*
import com.example.foodie.db.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE isFavourite = 1")
    fun getFavourite(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    fun loadRecipeById(id: Int): Flow<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(vararg recipes: Recipe)

    @Update
    suspend fun updateRecipes(vararg recipes: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)
}





