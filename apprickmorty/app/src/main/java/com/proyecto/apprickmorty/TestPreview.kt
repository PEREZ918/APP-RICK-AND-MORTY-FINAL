package com.proyecto.apprickmorty

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestComposable() {
    Text(text = "Test Preview Root")
}

@Preview
@Composable
fun SimpleTestPreviewRoot() {
    TestComposable()
}
