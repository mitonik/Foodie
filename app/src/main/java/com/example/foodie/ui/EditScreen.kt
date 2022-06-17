package com.example.foodie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodie.R
import com.example.foodie.theme.FoodieTheme

@ExperimentalMaterial3Api
@Composable
fun EditScreen(
    name: String,
    description: String,
) {
    Scaffold {
        Surface(Modifier.padding(it)) {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.edit_recipe_name)) })
                OutlinedTextField(
                    value = description,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.edit_recipe_description)) })
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun EditScreenPreview() {
    FoodieTheme {
        EditScreen("Name", "Desc")
    }
}