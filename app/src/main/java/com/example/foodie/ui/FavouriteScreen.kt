package com.example.foodie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.foodie.data.Graph
import com.example.foodie.db.Recipe

@ExperimentalMaterial3Api
@Composable
fun FavouriteScreen() {
    val recipes: LiveData<List<Recipe>> = Graph.recipeStore.getAll().asLiveData()
    val list = recipes.observeAsState(listOf()).value
    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(list) { recipe ->
                    if (recipe.isFavourite == true) {
                        recipe.name?.let { it1 -> RecipeCard({}, it1) }
                    }
                }
            }
        }
    }
}