package com.example.foodie.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes_table ORDER BY name ASC")
    fun getRecipes(): LiveData<List<Recipes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Recipes)

    @Query("DELETE FROM recipes_table")
    suspend fun deleteAll()
}