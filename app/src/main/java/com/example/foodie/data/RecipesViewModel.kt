package com.example.foodie.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository): ViewModel() {
    val allRecipes: LiveData<List<Recipes>> = repository.allRecipes.asLiveData()

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