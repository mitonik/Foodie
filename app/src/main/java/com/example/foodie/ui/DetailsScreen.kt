package com.example.foodie.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import coil.compose.rememberImagePainter
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

    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    if (recipe != null) {
                        Text(recipe.name,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

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

                val imageUri = rememberSaveable { mutableStateOf("") }
                val painter = rememberImagePainter(
                    if (recipe?.imagePath == null) {
                        R.drawable.ic_launcher_background
                    }
                    else {
                        recipe.imagePath
                    }
                )

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)) {
                    Card(
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .size(380.dp)
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()) {

                    recipe?.name?.let { it1 ->
                        Text(
                            it1,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)) {

                    //PROTEINS
                    Text(
                        stringResource(R.string.recipe_proteins),
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
                    Text(" [g]",
                        style = TextStyle(
                            fontSize = 18.sp
                        ))
                    Spacer(modifier = Modifier.width(10.dp))

                    //FATS
                    Text(stringResource(R.string.recipe_fats),
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
                    Text(" [g]",
                        style = TextStyle(
                            fontSize = 18.sp
                        ))
                    Spacer(modifier = Modifier.width(10.dp))

                    //CARBONS
                    Text(stringResource(R.string.recipe_carbons),
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
                    Text(" [g]",
                        style = TextStyle(
                            fontSize = 18.sp
                        ))

                }

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)) {
                    //CALORIES
                    Text(stringResource(R.string.recipe_calories),
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
                    Text(" [kcal]",
                        style = TextStyle(
                            fontSize = 18.sp
                        ))
                }

                Column(modifier = Modifier.padding(15.dp)) {

                    Text(stringResource(R.string.recipe_prepare),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    if (recipe != null) {
                        recipe.description?.let { it1 ->
                            Text(
                                it1,
                                style = TextStyle(fontSize = 18.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}