package com.proyecto.apprickmorty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.proyecto.apprickmorty.ui.theme.Background
import com.proyecto.apprickmorty.ui.theme.CyanAccent
import com.proyecto.apprickmorty.ui.theme.InputBoxShape
import com.proyecto.apprickmorty.ui.theme.RickverseType

@Composable
fun ScannerInputBox(
    placeholder: String,
    trailingLabel: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(InputBoxShape)
            .background(Background)
            .border(1.dp, CyanAccent.copy(alpha = 0.6f), InputBoxShape)
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(placeholder, style = RickverseType.BodySecondary, modifier = Modifier.weight(1f))
        if (trailingLabel.isNotEmpty()) {
            Text(trailingLabel, style = RickverseType.CaptionTag)
        }
    }
}
