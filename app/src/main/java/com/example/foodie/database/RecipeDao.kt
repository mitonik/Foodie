package com.example.foodie.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<Recipe>>

    @Insert
    suspend fun insertAll(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}