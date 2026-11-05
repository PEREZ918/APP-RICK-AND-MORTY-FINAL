package com.proyecto.apprickmorty.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.proyecto.apprickmorty.ui.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailScreen2(
    characterId: Int,
    onBackClick: () -> Unit,
    viewModel: CharacterViewModel = viewModel()
) {
    CharacterDetailScreen(
        characterId = characterId,
        onBack = onBackClick,
        viewModel = viewModel
    )
}
