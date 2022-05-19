package com.example.foodie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen() {
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
            Text("Edit")
        }
    }) {
        Surface(Modifier.padding(it)) {
            Column {
                Text("Title")
                Text("Description")
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen()
}