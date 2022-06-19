package com.example.foodie.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FoodieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorScheme()

    } else {
        lightColorScheme()
    }

    val systemUiController = rememberSystemUiController()
    val color = if (darkTheme) Color(43, 40, 49) else Color(242, 237, 247)
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.surface,
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = color,
            darkIcons = !darkTheme
        )
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}