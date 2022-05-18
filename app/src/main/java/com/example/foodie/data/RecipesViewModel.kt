package com.example.foodie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository): ViewModel() {
    val allRecipes: LiveData<List<Recipes>> = repository.allRecipes

    fun insert(name: Recipes) = viewModelScope.launch {
        repository.insert(name)
    }
}

class RecipesViewModelFactory(private val repository: RecipesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}