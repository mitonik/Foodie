package com.example.foodie.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.Recipe
import com.example.foodie.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun RecipeScreen(navController: NavController) {
    val recipes: List<Recipe> =
        Graph.recipeStore.getAll().asLiveData().observeAsState(listOf()).value
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(stringResource(R.string.add)) },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            onClick = { navController.navigate(Screen.AddScreen.route) },
            shape = MaterialTheme.shapes.medium
        )
    }) {
        Surface(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                Modifier.fillMaxSize(),
                rememberLazyGridState(),
                PaddingValues(10.dp),
                false,
                Arrangement.spacedBy(10.dp),
                Arrangement.spacedBy(10.dp)
            ) {
                items(recipes) { recipe ->
                    ElevatedCard(onClick = {
                        navController.navigate("details_screen/${recipe.id}")
                    }) {
                        if (recipe.name != null) {
                            Text(
                                recipe.name,
                                Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}