package com.example.foodie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                if (recipe != null) {
                    Image(
                        painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    recipe.name?.let { it1 ->
                        TextField(
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
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_name)) })
                    }
                    recipe.description?.let { it1 ->
                        TextField(
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
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_description)) })
                    }
                    recipe.isFavourite?.let { it1 ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Favourite")
                            Spacer(Modifier.weight(1f, true))
                            Switch(checked = it1, onCheckedChange = { value ->
                                runBlocking {
                                    if (recipe.name != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(
                                                recipe.id,
                                                recipe.name,
                                                recipe.description,
                                                value
                                            )
                                        )
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}