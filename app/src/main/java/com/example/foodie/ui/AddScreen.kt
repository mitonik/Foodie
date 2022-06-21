package com.example.foodie.ui

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.data.Graph
import com.example.foodie.db.model.Recipe
import com.example.foodie.theme.FoodieTheme
import kotlinx.coroutines.runBlocking

@ExperimentalMaterial3Api
@Composable
fun AddScreen(onAdd: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val proteins = remember { mutableStateOf("") }
    val fats = remember { mutableStateOf("") }
    val carbons = remember { mutableStateOf("") }
    val calories = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val isFavourite = remember { mutableStateOf(false) }
    val imagePath = remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
                uri: Uri? -> imageUri = uri
        }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    Scaffold {
        Surface(Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Box(contentAlignment = Alignment.TopEnd) {
                    
                    imageUri?.let { 
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    imagePath.value = imageUri.toString()

                    if(imageUri != null) {
                        bitmap.value?.let { 
                            bitmap ->
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    else {
                        Image(
                            painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Button(onClick = {
                        launcher.launch("image/*")

                    }) {
                        Text(text = "Pick Image")
                    }
                }

                //NAME
                TextField(
                    value = name.value,
                    onValueChange = { value ->
                        name.value = value
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.edit_recipe_name)) }
                )
                //PROTEINS
                TextField(
                    value = proteins.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { value ->
                        proteins.value = value
                    },
                    label = { Text(stringResource(R.string.edit_recipe_proteins)) }
                )
                //PROTEINS
                TextField(
                    value = fats.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { value ->
                        fats.value = value
                    },
                    label = { Text(stringResource(R.string.edit_recipe_fats)) }
                )
                //PROTEINS
                TextField(
                    value = carbons.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { value ->
                        carbons.value = value
                    },
                    label = { Text(stringResource(R.string.edit_recipe_carbons)) }
                )
                //PROTEINS
                TextField(
                    value = calories.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { value ->
                        calories.value = value
                    },
                    label = { Text(stringResource(R.string.edit_recipe_calories)) }
                )
                //DESC
                TextField(
                    value = description.value,
                    onValueChange = { value ->
                        description.value = value
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.edit_recipe_description)) }
                )
                //IS FAV?
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Favourite")
                    Spacer(Modifier.weight(1f, true))
                    Switch(checked = isFavourite.value, onCheckedChange = { value ->
                        isFavourite.value = value
                    })
                }
                Button(
                    onClick = {
                        runBlocking {
                            Graph.recipeStore.insertRecipes(
                                Recipe(0,
                                    name.value,
                                    proteins.value,
                                    fats.value,
                                    carbons.value,
                                    calories.value,
                                    isFavourite.value,
                                    description.value,
                                    imagePath.value
                                )
                            )
                        }
                        onAdd()
                    },
                    Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.add))
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun AddScreenPreview() {
    FoodieTheme {
        AddScreen {}
    }
}
