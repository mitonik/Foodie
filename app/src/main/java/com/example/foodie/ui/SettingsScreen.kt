package com.example.foodie.ui

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.theme.FoodieTheme

@ExperimentalMaterial3Api
@Composable
fun SettingsScreen() {
    Scaffold(Modifier.fillMaxSize(), topBar = {
        SmallTopAppBar(title = {
            Text(stringResource(R.string.settings))
        })
    }) {
        Surface(Modifier.padding(it)) {
            Column {
                Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.dark_theme),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(Modifier.weight(1f, true))
                    Switch(checked = isSystemInDarkTheme(), onCheckedChange = {}, enabled = false)
                }
            }
        }
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Polish", locale = "pl")
@ExperimentalMaterial3Api
@Composable
fun SettingsScreenPreview() {
    FoodieTheme {
        SettingsScreen()
    }
}
