package com.example.foodie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodie.ui.theme.FoodieTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    FoodieNavigationBar(navController)
                }) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.RecipeScreen.route
                        ) {
                            composable(route = Screen.RecipeScreen.route) {
                                RecipeScreen()
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
}