package com.example.foodie

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.theme.FoodieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodieItem(painter: Painter, contentDescription: String, text: String, modifier: Modifier = Modifier) {
    OutlinedCard(onClick = { /*TODO*/ }) {
        Image(painter, contentDescription, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Text(text, Modifier.padding(16.dp), overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CardPreview() {
    FoodieTheme {
        FoodieItem(painterResource(id = R.drawable.ic_launcher_background), "Food Image", "Cake")
    }
}