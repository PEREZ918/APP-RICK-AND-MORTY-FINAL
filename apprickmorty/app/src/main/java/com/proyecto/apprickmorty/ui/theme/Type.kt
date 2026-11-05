package com.proyecto.apprickmorty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Reemplaza por la fuente real del proyecto (ej. Rajdhani / Exo 2 / Orbitron) si se agrega a res/font
val RickverseFontFamily = FontFamily.Default

// Estilos custom usados por los componentes de Rickverse
object RickverseType {
    val DisplayTitle = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
        letterSpacing = 1.sp,
        color = NeonGreen
    )

    val ScreenTitle = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = TextPrimary
    )

    val SectionHeader = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp,
        color = TextPrimary
    )

    val LabelSmall = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = 1.2.sp,
        color = TextLabel
    )

    val BodyPrimary = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = TextPrimary
    )

    val BodySecondary = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = TextSecondary
    )

    val BadgeText = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        letterSpacing = 0.5.sp
    )

    val CaptionTag = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        color = CyanAccent
    )
}

// Typography de Material3 (fallback para componentes estándar)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = TextPrimary
    ),
    labelSmall = TextStyle(
        fontFamily = RickverseFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = TextLabel
    )
)
