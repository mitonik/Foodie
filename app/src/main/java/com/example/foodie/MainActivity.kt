package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.theme.FoodieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        ExtendedFloatingActionButton(modifier = Modifier.padding(16.dp), text = { Text(text = "Add") }, icon = { Icon(
                            Icons.Default.Add,
                            contentDescription = null
                        ) }, onClick = { /*TODO*/ })
                        NavigationBarPreview()
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    NavigationBar {
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Filled.List,
                contentDescription = null
            )
        }, label = { Text(text = "Recipes") })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }, label = { Text(text = "Favourites") })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = null
            )
        }, label = { Text(text = "Settings") })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListItemPreview() {
    Card() {
        Text(text = "Delicious cake")
    }
}