package com.example.foodie.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.theme.FoodieTheme
import kotlinx.coroutines.runBlocking

@ExperimentalMaterial3Api
@Composable
fun AddScreen(
    onAdd: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val isFavourite = remember { mutableStateOf(false) }

    Scaffold {
        Column(Modifier.padding(it)) {
            TextField(
                value = name.value,
                onValueChange = { value ->
                    name.value = value
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.edit_recipe_name)) }
            )
            TextField(
                value = description.value,
                onValueChange = { value ->
                    description.value = value
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.edit_recipe_description)) }
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Favourite")
                Spacer(Modifier.weight(1f, true))
                Switch(checked = isFavourite.value, onCheckedChange = { value ->
                    isFavourite.value = value
                })
            }
            Button(
                onClick = {
                    runBlocking {
                        Graph.recipeStore.insertRecipes(
                            0,
                            name.value,
                            description.value,
                            isFavourite.value
                        )
                    }
                    onAdd()
                },
                Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add))
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun AddScreenPreview() {
    FoodieTheme {
        AddScreen {}
    }
}