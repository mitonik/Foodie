package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodie.data.Recipes
import com.example.foodie.data.RecipesRepository
import com.example.foodie.data.RecipesRoomDatabase
import com.example.foodie.ui.theme.FoodieTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
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
                }) { it ->
                    NavHost(
                        navController,
                        Screen.RecipeScreen.route,
                        Modifier.padding(it)
                    ) {
                        composable(route = Screen.RecipeScreen.route) {
                            RecipeScreen(navController, list)
                        }
                        composable(route = Screen.FavouriteScreen.route) {
                            FavouriteScreen(list)
                        }
                        composable(route = Screen.SettingsScreen.route) {
                            SettingsScreen()
                        }
                        composable(
                            Screen.DetailsScreen.route,
                            arguments = listOf(navArgument("title") {
                                type = NavType.StringType
                            })
                        ) {
                            DetailsScreen(
                                {},
                                it.arguments?.getString("title").toString(),
                                "",
                                false,
                                {})
                        }
                    }
                }
            }
        }
    }

}