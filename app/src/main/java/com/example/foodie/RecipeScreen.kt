package com.example.foodie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen() {
    val list = listOf(
        "Cake",
        "Meat",
        "Vegetables",
        "Salt",
        "Pepper",
        "Rice",
        "Food Item",
        "Food Item",
        "Food Item",
        "Food Item",
        "Food Item",
        "Food Item",
        "Food Item"
    )
        Scaffold(floatingActionButton = {
            AddButton()
        }) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list.size) { index ->
                    FoodieItem(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = list[index],
                        text = list[index]
                    )
                }
            }
        }
}