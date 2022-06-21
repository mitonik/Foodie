package com.example.foodie.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import coil.compose.rememberImagePainter
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.model.Recipe
import kotlinx.coroutines.runBlocking

@ExperimentalMaterial3Api
@Composable
fun EditScreen(
    id: Int
) {
    val recipe = Graph.recipeStore.loadRecipeById(id).asLiveData().observeAsState().value
    Scaffold {
        Surface(Modifier.padding(it)) {
            Column(
                Modifier.padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)) {
                if (recipe != null) {

                    val imageUri = recipe.imagePath
                    val painter = rememberImagePainter(
                        if (imageUri?.isEmpty() == true) {
                            R.drawable.ic_launcher_background
                        }
                        else {
                            imageUri
                        }
                    )

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent()
                    ) { uri: Uri? ->
                        uri?.let { imageUri }
                    }

                    Button(onClick = {
                        launcher.launch("image/*")
                    }) {
                        Text(text = stringResource(R.string.add_image))
                    }

                    Card(
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .size(360.dp)
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    /*
                     * NAME EDIT
                     */
                    TextField(
                        value = recipe.name,
                        onValueChange = {
                            runBlocking {
                                if ((recipe.isFavourite != null) && recipe.description != null) {
                                    Graph.recipeStore.updateRecipes(
                                        Recipe(recipe.recipeId, it, recipe.proteins,
                                            recipe.fats, recipe.carbons, recipe.calories,
                                            recipe.isFavourite, recipe.description, recipe.imagePath
                                        )
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(stringResource(R.string.edit_recipe_name)) })

                    /*
                     * PROTEINS EDIT
                     */
                    recipe.proteins?.let { it1 ->
                        TextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.isFavourite != null) && recipe.description != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, it,
                                                recipe.fats, recipe.carbons, recipe.calories,
                                                recipe.isFavourite, recipe.description, recipe.imagePath
                                            )
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_proteins)) })
                    }

                    /*
                     * FATS EDIT
                     */
                    recipe.fats?.let { it1 ->
                        TextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.isFavourite != null) && recipe.description != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, recipe.proteins,
                                                it, recipe.carbons, recipe.calories,
                                                recipe.isFavourite, recipe.description, recipe.imagePath
                                            )
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_fats)) })
                    }

                    /*
                     * CARBONS EDIT
                     */
                    recipe.carbons?.let { it1 ->
                        TextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.isFavourite != null) && recipe.description != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, recipe.proteins,
                                                recipe.fats, it, recipe.calories,
                                                recipe.isFavourite, recipe.description, recipe.imagePath
                                            )
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_carbons)) })
                    }

                    /*
                     * CALORIES EDIT
                     */
                    recipe.calories?.let { it1 ->
                        TextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.isFavourite != null) && recipe.description != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, recipe.proteins,
                                                recipe.fats, recipe.carbons, it,
                                                recipe.isFavourite, recipe.description, recipe.imagePath
                                            )
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_calories)) })
                    }

                    /*
                     * DESCRIPTION EDIT
                     */
                    recipe.description?.let { it1 ->
                        TextField(
                            value = it1,
                            onValueChange = {
                                runBlocking {
                                    if ((recipe.name != null) && (recipe.isFavourite != null)) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, recipe.proteins,
                                                recipe.fats, recipe.carbons, recipe.calories,
                                                recipe.isFavourite, it, recipe.imagePath)
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(R.string.edit_recipe_description)) })
                    }

                    /*
                     * FAV EDIT
                     */
                    recipe.isFavourite?.let { it1 ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(stringResource(R.string.favourites))
                            Spacer(Modifier.weight(1f, true))
                            Switch(checked = it1, onCheckedChange = { value ->
                                runBlocking {
                                    if (recipe.name != null) {
                                        Graph.recipeStore.updateRecipes(
                                            Recipe(recipe.recipeId, recipe.name, recipe.proteins,
                                                recipe.fats, recipe.carbons, recipe.calories,
                                                !recipe.isFavourite, recipe.description, recipe.imagePath
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