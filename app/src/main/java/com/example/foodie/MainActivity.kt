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
import androidx.room.Room
import com.example.foodie.database.FoodieDatabase
import com.example.foodie.database.Recipe
import com.example.foodie.ui.theme.FoodieTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, FoodieDatabase::class.java, "foodie").build()
        val recipeDao = db.recipeDao()
        val recipes: LiveData<List<Recipe>> = recipeDao.getAll()

        setContent {
            val navController = rememberNavController()
            val list = recipes.observeAsState(listOf()).value

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
                            RecipeScreen(recipeDao, navController, list)
                        }
                        composable(route = Screen.FavouriteScreen.route) {
                            FavouriteScreen(list)
                        }
                        composable(route = Screen.SettingsScreen.route) {
                            SettingsScreen()
                        }
                        composable(
                            Screen.DetailsScreen.route,
                            arguments = listOf(
                                navArgument("title") { type = NavType.StringType },
                                navArgument("description") { type = NavType.StringType }
                            )
                        ) {
                            DetailsScreen(
                                {},
                                it.arguments?.getString("title").toString(),
                                it.arguments?.getString("description").toString(),
                                false,
                                {}
                            )
                        }
                    }
                }
            }
        }
    }

}