package com.example.foodie.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.theme.FoodieTheme
import com.example.foodie.theme.Shapes
import kotlinx.coroutines.runBlocking


@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    id: Int,
    onEdit: () -> Unit
) {
    val recipe = Graph.recipeStore.loadRecipeById(id).asLiveData().observeAsState().value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    if (recipe != null) {
                        Text("${recipe.name} (${recipe.id})")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        runBlocking {
                            if (recipe?.name != null && recipe.description != null && recipe.isFavourite != null) {
                                Graph.recipeStore.updateRecipes(
                                    recipe.id,
                                    recipe.name,
                                    recipe.description,
                                    !recipe.isFavourite
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
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(R.string.edit)) },
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                onClick = onEdit,
                shape = Shapes.medium,
            )
        }
    ) {
        Surface(Modifier.padding(it)) {
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                if (recipe != null) {
                    recipe.description?.let { it1 -> Text(it1, Modifier.padding(10.dp)) }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Polish", locale = "pl")
@Composable
fun DetailsScreenPreview() {
    FoodieTheme {
        DetailsScreen(
            0
        ) {}
    }
}