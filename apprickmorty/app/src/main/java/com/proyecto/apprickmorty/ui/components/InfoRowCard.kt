package com.proyecto.apprickmorty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.proyecto.apprickmorty.ui.theme.CardShape
import com.proyecto.apprickmorty.ui.theme.CyanAccent
import com.proyecto.apprickmorty.ui.theme.CyanAccentDim
import com.proyecto.apprickmorty.ui.theme.RickverseSpacing
import com.proyecto.apprickmorty.ui.theme.RickverseType
import com.proyecto.apprickmorty.ui.theme.SurfaceCard
import com.proyecto.apprickmorty.ui.theme.SurfaceCardBorder

@Composable
fun InfoRowCard(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CardShape)
            .background(SurfaceCard)
            .border(1.dp, SurfaceCardBorder, CardShape)
            .padding(RickverseSpacing.CardPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(RickverseSpacing.IconCircleSize)
                .clip(CircleShape)
                .background(CyanAccentDim),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label.uppercase(), style = RickverseType.LabelSmall)
            Spacer(Modifier.height(2.dp))
            Text(value, style = RickverseType.BodyPrimary)
        }
    }
}
