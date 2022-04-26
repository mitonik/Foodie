package com.example.foodie

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodie.ui.theme.FoodieTheme

@Composable
fun FoodieNavigationBar(modifier: Modifier = Modifier) {
    NavigationBar {
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Filled.List,
                contentDescription = null
            )
        }, label = { Text(text = stringResource(R.string.recipes)) })

        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }, label = { Text(text = stringResource(R.string.favourites)) })

        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = null
            )
        }, label = { Text(text = stringResource(R.string.settings)) })
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Polish", locale = "pl")
@Composable
fun FoodieNavigationBarPreview() {
    FoodieTheme {
        FoodieNavigationBar()
    }
}