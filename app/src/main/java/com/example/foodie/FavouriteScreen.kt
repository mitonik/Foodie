package com.example.foodie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavouriteScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Favourites")
    }
}