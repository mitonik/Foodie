package com.example.foodie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.model.Recipe
import kotlinx.coroutines.runBlocking


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
                        Text("${recipe.name} (${recipe.recipeId})")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        runBlocking {
                            if (recipe?.name != null && recipe.description != null
                                && recipe.isFavourite != null) {
                                Graph.recipeStore.updateRecipes(
                                    Recipe(recipe.recipeId, recipe.name,
                                        recipe.proteins, recipe.fats, recipe.carbons, recipe.calories,
                                        !recipe.isFavourite, recipe.description, recipe.imagePath
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
                                && recipe.isFavourite != null) {
                                Graph.recipeStore.deleteRecipe(
                                    Recipe(recipe.recipeId, recipe.name,
                                        recipe.proteins, recipe.fats, recipe.carbons, recipe.calories,
                                        !recipe.isFavourite, recipe.description, recipe.imagePath
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
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())) {
                Image(
                    painterResource(R.drawable.ic_launcher_background),
                    null,
                    Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)) {
                    //PROTEINS
                    Text("P: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ))
                    if (recipe?.proteins != "") {
                        recipe?.proteins?.let {
                                it1 -> Text(
                            it1,
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                        }
                    }
                    else {
                        Text(" -",
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //FATS
                    Text("F: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ))
                    if (recipe?.fats != "") {
                        recipe?.fats?.let {
                                it1 -> Text(
                            it1,
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                        }
                    }
                    else {
                        Text(" -",
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    //CARBONS
                    Text("C: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ))
                    if (recipe?.carbons != "") {
                        recipe?.carbons?.let {
                                it1 -> Text(
                            it1,
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                        }
                    }
                    else {
                        Text(" -",
                            style = TextStyle(
                                fontSize = 18.sp
                            ))
                    }

                }

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)) {
                    //CALORIES
                    Text("Cal: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ))
                    if (recipe?.calories != "") {
                        recipe?.calories?.let {
                                it1 -> Text(
                            it1,
                            style = TextStyle(
                                fontSize = 20.sp
                            ))
                         }
                    }
                    else {
                        Text(" -",
                            style = TextStyle(
                                fontSize = 20.sp
                            ))
                    }
                }
                
                Column(modifier = Modifier.padding(15.dp)) {
                    Text("Preparing:",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    if (recipe != null) {
                        recipe.description?.let {
                                it1 -> Text(
                            it1,
                            style = TextStyle(fontSize = 18.sp))
                        }
                    }

                   recipe?.imagePath?.let {
                            it1 -> Text(
                        it1,
                        style = TextStyle(fontSize = 18.sp))
                    }

                }
            }
        }
    }
}