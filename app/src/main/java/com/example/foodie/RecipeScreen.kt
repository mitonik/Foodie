package com.example.foodie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.database.Recipe
import com.example.foodie.database.RecipeDao
import com.example.foodie.ui.theme.Shapes
import kotlinx.coroutines.runBlocking

@ExperimentalMaterial3Api
@Composable
fun RecipeScreen(recipes: RecipeDao, navController: NavController, list: List<Recipe>) {
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(stringResource(R.string.add)) },
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            onClick = {
                runBlocking {
                    recipes.insertAll(Recipe(0, "Recipe Name", "Custom Description", false))
                }
                navController.navigate(Screen.DetailsScreen.route)
            },
            shape = Shapes.medium
        )
    }) {
        Surface(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(list) { recipe ->
                    recipe.name?.let { it1 ->
                        RecipeCard(
                            onClick = { navController.navigate("details_screen/${recipe.name}/${recipe.name}") },
                            text = it1
                        )
                    }
                }
            }
        }
    }
}