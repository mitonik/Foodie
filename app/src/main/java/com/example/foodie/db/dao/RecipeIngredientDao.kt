package com.example.foodie.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.foodie.db.model.IngredientWithRecipes
import com.example.foodie.db.model.RecipeWithIngredients

@Dao
interface RecipeIngredientDao {
    @Query("SELECT * FROM Recipe")
    fun getRecipeWithIngredients(): LiveData<List<RecipeWithIngredients>>

    @Query("SELECT * FROM Ingredient")
    fun getIngredientWithRecipes(): LiveData<List<IngredientWithRecipes>>
}