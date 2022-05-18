package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodie.data.Recipes
import com.example.foodie.data.RecipesRepository
import com.example.foodie.data.RecipesRoomDatabase
import com.example.foodie.ui.theme.FoodieTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val readAllData: LiveData<List<Recipes>>
        val repository: RecipesRepository
        val recipesDao = RecipesRoomDatabase.getDatabase(this).recipesDao()
        repository = RecipesRepository(recipesDao)
        readAllData = repository.allRecipes

        setContent {
            val navController = rememberNavController()
            val list = readAllData.observeAsState(listOf()).value

            FoodieTheme {
                Scaffold(bottomBar = {
                    FoodieNavigationBar(navController)
                }) {
                    NavHost(
                        navController,
                        Screen.RecipeScreen.route,
                        Modifier.padding(it)
                    ) {
                        composable(route = Screen.RecipeScreen.route) {
                            RecipeScreen({
                                runBlocking {
                                    repository.insert(
                                        Recipes(
                                            0,
                                            "The Cake Is A Lie"
                                        )
                                    )
                                }
                            }, list, Modifier.fillMaxSize())
                        }
                        composable(route = Screen.FavouriteScreen.route) {
                            FavouriteScreen()
                        }
                        composable(route = Screen.SettingsScreen.route) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }

}