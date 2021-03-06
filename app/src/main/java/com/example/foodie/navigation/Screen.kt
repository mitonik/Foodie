package com.example.foodie.navigation

sealed class Screen(val route: String) {
    object RecipeScreen : Screen("recipe_screen")
    object FavouriteScreen : Screen("favourite_screen")
    object SettingsScreen : Screen("settings_screen")
    object DetailsScreen : Screen("details_screen/{id}")
    object EditScreen : Screen("edit_screen/{id}")
    object AddScreen : Screen("add_screen")
}
