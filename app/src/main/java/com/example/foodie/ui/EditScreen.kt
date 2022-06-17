package com.example.foodie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.asLiveData
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.Recipe
import kotlinx.coroutines.runBlocking

@ExperimentalMaterial3Api
@Composable
fun EditScreen(
    id: Int
) {
    val recipe = Graph.recipeStore.loadRecipeById(id).asLiveData().observeAsState().value
    Scaffold {
        Surface(Modifier.padding(it)) {
            Column {
                if (recipe != null) {
                    recipe.name?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.isFavourite != null) && recipe.description != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(
                                                recipe.id,
                                                it,
                                                recipe.description,
                                                recipe.isFavourite
                                            )
                                        )
                                    }
                                }
                            },
                            label = { Text(stringResource(R.string.edit_recipe_name)) })
                    }
                    recipe.description?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.name != null) && (recipe.isFavourite != null)) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.id, recipe.name, it, recipe.isFavourite)
                                        )
                                    }
                                }
                            },
                            label = { Text(stringResource(R.string.edit_recipe_description)) })
                    }
                }
            }
        }
    }
}