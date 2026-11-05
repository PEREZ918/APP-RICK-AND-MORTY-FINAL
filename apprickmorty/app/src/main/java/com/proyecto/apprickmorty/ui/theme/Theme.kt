package com.proyecto.apprickmorty.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val RickverseDarkColorScheme = darkColorScheme(
    primary = NeonGreen,
    onPrimary = Background,
    secondary = CyanAccent,
    onSecondary = Background,
    tertiary = CyanAccent,
    background = Background,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCard,
    onSurfaceVariant = TextSecondary,
    outline = SurfaceCardBorder
)

// La app es siempre dark theme (interfaz "sci-fi" de Rickverse), sin dynamic color.
@Composable
fun ApprickmortyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RickverseDarkColorScheme,
        typography = Typography,
        shapes = RickverseShapes,
        content = content
    )
}
