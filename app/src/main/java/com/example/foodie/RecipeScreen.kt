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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.data.Recipes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    click: () -> Unit,
    list: List<Recipes>,
    modifier: Modifier = Modifier
) {
    Scaffold(floatingActionButton = {
        AddButton(click)
    }) {
        Surface(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                modifier = modifier,
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(list) { recipe ->
                    RecipeCard(recipe.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(
        {},
        listOf(
            Recipes(0, "Cakeasdkajshdkjahskjdhakjhsdjdshakjhsa"),
            Recipes(0, "Steak"),
            Recipes(0, "Fries"),
            Recipes(0, "Fish")
        )
    )
}