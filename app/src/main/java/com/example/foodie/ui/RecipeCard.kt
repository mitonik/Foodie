package com.example.foodie.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun RecipeCard(onClick: () -> Unit, text: String) {
    ElevatedCard(onClick) {
        Text(
            text,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RecipeCardPreview() {
    RecipeCard({}, "Cheesy Vegetarian Enchilada Casserole")
}