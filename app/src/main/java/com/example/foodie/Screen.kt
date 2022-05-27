package com.example.foodie

sealed class Screen(val route: String) {
    object RecipeScreen: Screen("recipe_screen")
    object FavouriteScreen: Screen("favourite_screen")
    object SettingsScreen : Screen("settings_screen")
    object DetailsScreen : Screen("details_screen/{title}/{description}")
}
