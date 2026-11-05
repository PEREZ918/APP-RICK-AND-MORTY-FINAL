package com.proyecto.apprickmorty.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.apprickmorty.data.model.RickCharacter
import com.proyecto.apprickmorty.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository = CharacterRepository()
) : ViewModel() {

    var characters by mutableStateOf<List<RickCharacter>>(emptyList())
        private set

    var selectedCharacter by mutableStateOf<RickCharacter?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadCharacters(name: String? = null) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            repository.getCharacters(name?.takeIf { it.isNotBlank() })
                .onSuccess { characters = it }
                .onFailure { errorMessage = it.message }
            isLoading = false
        }
    }

    fun loadCharacterById(id: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            repository.getCharacterById(id)
                .onSuccess { selectedCharacter = it }
                .onFailure { errorMessage = it.message }
            isLoading = false
        }
    }

    fun selectCharacter(character: RickCharacter) {
        selectedCharacter = character
    }
}
