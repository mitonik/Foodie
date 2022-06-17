package com.example.foodie

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.foodie.navigation.FoodieNavHost
import com.example.foodie.navigation.Screen
import com.example.foodie.theme.FoodieTheme

@ExperimentalMaterial3Api
@Composable
fun FoodieApp() {

    val navController = rememberNavController()
    FoodieTheme {
        Scaffold(
            bottomBar = { FoodieNavigationBar(navController) }
        ) {
            FoodieNavHost(navController, Screen.RecipeScreen.route, Modifier.padding(it))
        }
    }
}