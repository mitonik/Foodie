package com.example.foodie.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes_table ORDER BY name ASC")
    fun getRecipes(): Flow<List<Recipes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Recipes)

    @Query("DELETE FROM recipes_table")
    suspend fun deleteAll()
}