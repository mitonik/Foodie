package com.example.foodie

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.theme.FoodieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPreview()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainPreview() {
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
    FoodieTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Scaffold(Modifier.weight(1f), floatingActionButton = {
                    AddButton()
                }) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        list.forEach {
                            item {
                                FoodieItem(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = it,
                                    text = it
                                )
                            }
                        }
                    }
                }
                FoodieNavigationBar(Modifier.weight(1f))
            }
        }
    }
}