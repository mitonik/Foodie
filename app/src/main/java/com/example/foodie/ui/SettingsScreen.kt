package com.example.foodie.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.R

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
                    Text(text = stringResource(R.string.dark_theme))
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = isSystemInDarkTheme(), onCheckedChange = {}, enabled = false)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
