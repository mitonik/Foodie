package com.example.foodie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodie.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(title: String, description: String) {
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(onClick = { /*TODO*/ }, shape = Shapes.medium) {
            Text("Edit")
        }
    }) {
        Surface(Modifier.padding(it)) {
            Column {
                Text(title)
                Text(description)
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        "Bacon Deviled Egg Recipes",
        "Find irresistibly delicious recipes for bacon deviled eggs to serve at your next get-together."
    )
}