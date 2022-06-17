package com.example.foodie.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.theme.FoodieTheme

@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    title: String,
    description: String,
    isFavourite: Boolean,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Outlined.ArrowBack, null)
                    }
                },
                title = { Text(title) },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        if (isFavourite) {
                            Icon(Icons.Outlined.Favorite, null)
                        } else {
                            Icon(Icons.Outlined.FavoriteBorder, null)
                        }
                    }
                }
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
                Text(description, Modifier.padding(10.dp))
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
            "Bacon Deviled Egg Recipes",
            "Find irresistibly delicious recipes for bacon deviled eggs to serve at your next get-together.",
            true
        )
    }
}