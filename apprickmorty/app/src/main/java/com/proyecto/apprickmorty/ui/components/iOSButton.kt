package com.proyecto.apprickmorty.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.apprickmorty.ui.theme.TextPrimary
import com.proyecto.apprickmorty.ui.theme.TextSecondary
import com.proyecto.apprickmorty.ui.theme.iOSCardBackground
import com.proyecto.apprickmorty.ui.theme.iOSSeparator
import com.proyecto.apprickmorty.ui.theme.iOSSystemBlue

enum class iOSButtonStyle {
    Primary,
    Secondary,
    Destructive
}

@Composable
fun iOSButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: iOSButtonStyle = iOSButtonStyle.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        label = "button_scale"
    )

    val backgroundColor = when (style) {
        iOSButtonStyle.Primary -> if (enabled) iOSSystemBlue else iOSSystemBlue.copy(alpha = 0.4f)
        iOSButtonStyle.Secondary -> iOSCardBackground
        iOSButtonStyle.Destructive -> com.proyecto.apprickmorty.ui.theme.iOSSystemRed
    }

    val textColor = when (style) {
        iOSButtonStyle.Primary, iOSButtonStyle.Destructive -> TextPrimary
        iOSButtonStyle.Secondary -> if (enabled) iOSSystemBlue else TextSecondary
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .scale(scale)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .then(
                if (style == iOSButtonStyle.Secondary) {
                    Modifier.border(1.dp, iOSSeparator, RoundedCornerShape(14.dp))
                } else Modifier
            )
            .clickable(
                enabled = enabled && !isLoading,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = textColor,
                strokeWidth = 2.5.dp,
                modifier = Modifier.size(22.dp)
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = textColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )
                )
            }
        }
    }
}
