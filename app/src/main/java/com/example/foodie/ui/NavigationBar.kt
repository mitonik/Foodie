package com.example.foodie

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodie.navigation.Screen

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
                if (currentRoute == Screen.RecipeScreen.route) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        Icons.Outlined.List,
                        contentDescription = null
                    )
                }
            },
            label = { Text(text = stringResource(R.string.recipes)) })

        NavigationBarItem(
            selected = currentRoute == Screen.FavouriteScreen.route,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.FavouriteScreen.route)
            },
            icon = {
                if (currentRoute == Screen.FavouriteScreen.route) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = null
                    )
                }
            },
            label = { Text(text = stringResource(R.string.favourites)) })

        NavigationBarItem(
            selected = currentRoute == Screen.SettingsScreen.route,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.SettingsScreen.route)
            },
            icon = {
                if (currentRoute == Screen.SettingsScreen.route) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                }
            },
            label = { Text(text = stringResource(R.string.settings)) })
    }
}