package com.proyecto.apprickmorty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.apprickmorty.ui.theme.iOSSystemGreen
import com.proyecto.apprickmorty.ui.theme.iOSSystemRed

@Composable
fun StatusBadge(text: String, modifier: Modifier = Modifier) {
    val isAlive = text.lowercase().contains("alive") || text.lowercase().contains("online")
    val badgeColor = if (isAlive) iOSSystemGreen else iOSSystemRed
    val bgColor = badgeColor.copy(alpha = 0.15f)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(badgeColor)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = text.uppercase(),
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = badgeColor,
                letterSpacing = 0.5.sp
            )
        )
    }
}
