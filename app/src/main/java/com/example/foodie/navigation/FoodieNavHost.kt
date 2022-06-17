package com.example.foodie.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodie.ui.*

@ExperimentalMaterial3Api
@Composable
fun FoodieNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    modifier: Modifier
) {
    NavHost(navController, startDestination, modifier) {
        composable(Screen.RecipeScreen.route) { RecipeScreen(navController) }
        composable(Screen.FavouriteScreen.route) { FavouriteScreen() }
        composable(Screen.SettingsScreen.route) { SettingsScreen() }
        composable(
            Screen.DetailsScreen.route, listOf(
                navArgument("id") { type = NavType.IntType })
        ) {
            it.arguments?.getInt("id")?.let { id ->
                DetailsScreen(
                    id,
                    { navController.navigate("${Screen.EditScreen.route}/${id}") }
                ) {
                    navController.navigateUp()
                }
            }
        }
        composable(
            Screen.EditScreen.route, listOf(
                navArgument("id") { type = NavType.IntType })
        ) {
            it.arguments?.getInt("id")?.let { id ->
                EditScreen(
                    id
                )
            }
        }
        composable(Screen.AddScreen.route) {
            AddScreen {
                navController.navigateUp()
            }
        }
    }
}