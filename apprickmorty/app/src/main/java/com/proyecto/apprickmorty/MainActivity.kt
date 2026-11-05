package com.proyecto.apprickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.proyecto.apprickmorty.navigation.RickverseNavGraph
import com.proyecto.apprickmorty.ui.theme.ApprickmortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApprickmortyTheme {
                RickverseNavGraph()
            }
        }
    }
}
