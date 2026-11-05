package com.proyecto.apprickmorty.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val CardShape = RoundedCornerShape(14.dp)
val InputBoxShape = RoundedCornerShape(12.dp)
val PillShape = RoundedCornerShape(50)
val IconCircleShape = CircleShape

val RickverseShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = CardShape,
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

object RickverseSpacing {
    val ScreenPaddingHorizontal = 20.dp
    val SectionSpacing = 24.dp
    val CardSpacing = 12.dp
    val CardPadding = 16.dp
    val AvatarSize = 56.dp
    val IconCircleSize = 40.dp
}
