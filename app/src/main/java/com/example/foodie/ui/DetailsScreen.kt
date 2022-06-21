package com.example.foodie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.model.Recipe
import kotlinx.coroutines.runBlocking

@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    id: Int,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val recipe = Graph.recipeStore.loadRecipeById(id).asLiveData().observeAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    if (recipe != null) {
                        Text(
                            recipe.name,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                    }
                },
                actions = {
                    IconButton(onClick = {
                        runBlocking {
                            if (recipe?.name != null && recipe.description != null
                                && recipe.isFavourite != null
                            ) {
                                Graph.recipeStore.updateRecipes(
                                    Recipe(
                                        recipe.recipeId,
                                        recipe.name,
                                        recipe.proteins,
                                        recipe.fats,
                                        recipe.carbons,
                                        recipe.calories,
                                        !recipe.isFavourite,
                                        recipe.description,
                                        recipe.imagePath
                                    )
                                )
                            }
                        }
                    }) {
                        if (recipe != null) {
                            if (recipe.isFavourite == true) {
                                Icon(Icons.Outlined.Favorite, null)
                            } else {
                                Icon(Icons.Outlined.FavoriteBorder, null)
                            }
                        }
                    }
                    IconButton(onClick = { onEdit() }) {
                        Icon(Icons.Outlined.Edit, null)
                    }
                    IconButton(onClick = {
                        runBlocking {
                            if (recipe?.name != null && recipe.description != null
                                && recipe.isFavourite != null
                            ) {
                                Graph.recipeStore.deleteRecipe(
                                    Recipe(
                                        recipe.recipeId,
                                        recipe.name,
                                        recipe.proteins,
                                        recipe.fats,
                                        recipe.carbons,
                                        recipe.calories,
                                        !recipe.isFavourite,
                                        recipe.description,
                                        recipe.imagePath
                                    )
                                )
                            }
                            onDelete()
                        }
                    }) {
                        Icon(Icons.Outlined.Delete, null)
                    }
                }
            )
        }
    ) {
        Surface(Modifier.padding(it)) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                val painter = rememberImagePainter(
                    if (recipe?.imagePath == null) {
                        R.drawable.ic_launcher_background
                    } else {
                        recipe.imagePath
                    }
                )
                Image(
                    painter,
                    null,
                    Modifier
                        .size(500.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.padding(10.dp)) {
                    if (recipe?.proteins != "") {
                        recipe?.proteins?.let { proteins ->
                            Text("${stringResource(R.string.recipe_proteins)}: $proteins g")
                        }
                    }
                    if (recipe?.fats != "") {
                        recipe?.fats?.let { fats ->
                            Text("${stringResource(R.string.recipe_fats)}: $fats g")
                        }
                    }
                    if (recipe?.carbons != "") {
                        recipe?.carbons?.let { carbons ->
                            Text("${stringResource(R.string.recipe_carbons)}: $carbons g")
                        }
                    }
                    if (recipe?.calories != "") {
                        recipe?.calories?.let { calories ->
                            Text("${stringResource(R.string.recipe_calories)}: $calories kcal")
                        }
                    }
                }
                recipe?.description?.let { description ->
                    Text(
                        description,
                        Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}