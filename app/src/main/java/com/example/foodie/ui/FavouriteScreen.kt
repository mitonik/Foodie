package com.example.foodie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.foodie.data.Graph
import com.example.foodie.db.model.Recipe

@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun FavouriteScreen(navController: NavController) {
    val recipes: List<Recipe> = Graph.recipeStore.getFavourite().asLiveData().observeAsState(listOf()).value

    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                Modifier.fillMaxSize(),
                rememberLazyGridState(),
                PaddingValues(10.dp),
                false,
                Arrangement.spacedBy(8.dp),
                Arrangement.spacedBy(8.dp)
            ) {
                items(recipes) { recipe ->
                    Card(
                        { navController.navigate("details_screen/${recipe.recipeId}") }
                    ) {
                        Image(
                            rememberImagePainter(recipe.imagePath),
                            null,
                            Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            recipe.name,
                            Modifier.padding(16.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}