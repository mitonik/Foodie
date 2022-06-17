package com.example.foodie.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodie.DetailsScreen
import com.example.foodie.FavouriteScreen
import com.example.foodie.RecipeScreen
import com.example.foodie.SettingsScreen
import com.example.foodie.data.Graph
import com.example.foodie.db.Recipe

@ExperimentalMaterial3Api
@Composable
fun FoodieNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    modifier: Modifier
) {
    val recipes: LiveData<List<Recipe>> = Graph.recipeStore.getAll().asLiveData()
    val list = recipes.observeAsState(listOf()).value
    NavHost(
        navController,
        startDestination,
        modifier
    ) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(navController, list)
        }
        composable(Screen.FavouriteScreen.route) {
            FavouriteScreen(list)
        }
        composable(Screen.SettingsScreen.route) {
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