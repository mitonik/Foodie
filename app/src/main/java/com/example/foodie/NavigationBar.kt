package com.example.foodie

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun FoodieNavigationBar(navController: NavController) {
    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        NavigationBarItem(
            selected = currentRoute == Screen.RecipeScreen.route,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.RecipeScreen.route)
            },
            icon = {
                Icon(
                    Icons.Filled.List,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(R.string.recipes)) })

        NavigationBarItem(
            selected = currentRoute == Screen.FavouriteScreen.route,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.FavouriteScreen.route)
            },
            icon = {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(R.string.favourites)) })

        NavigationBarItem(
            selected = currentRoute == Screen.SettingsScreen.route,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.SettingsScreen.route)
            },
            icon = {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(R.string.settings)) })
    }
}