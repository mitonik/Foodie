package com.example.foodie

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.theme.FoodieTheme

@Composable
fun AddButton(onClick: () -> Unit = {}) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(R.string.add)) },
        icon = {
            Icon(
                Icons.Default.Add,
                contentDescription = null
            )
        },
        onClick = onClick, shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
fun AddButtonPreview() {
    FoodieTheme {
        AddButton()
    }
}