package com.proyecto.apprickmorty.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.apprickmorty.ui.components.NeonProgressBar
import com.proyecto.apprickmorty.ui.theme.Background
import com.proyecto.apprickmorty.ui.theme.CyanAccent
import com.proyecto.apprickmorty.ui.theme.NeonGreen
import com.proyecto.apprickmorty.ui.theme.TextPrimary
import com.proyecto.apprickmorty.ui.theme.TextSecondary
import com.proyecto.apprickmorty.ui.theme.iOSCardBackground
import com.proyecto.apprickmorty.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    authViewModel: AuthViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(1f, animationSpec = tween(durationMillis = 1400))
        delay(300)
        if (authViewModel.isLoggedIn) {
            onNavigateToHome()
        } else {
            onNavigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "RICKVERSE C-137",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary,
                    letterSpacing = 1.sp
                )
            )
            Text(
                text = "v1.0 iOS",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = NeonGreen,
                    letterSpacing = 1.sp
                )
            )
        }

        // Center Portal & Logo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(48.dp))
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                NeonGreen.copy(alpha = 0.35f),
                                CyanAccent.copy(alpha = 0.2f),
                                iOSCardBackground
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                PortalSwirl(size = 170.dp)
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Rickverse",
                style = TextStyle(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Explora el multiverso de Rick & Morty",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
            )
        }

        // Progress Bar
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Cargando portal...",
                    style = TextStyle(fontSize = 12.sp, color = TextSecondary)
                )
                Text(
                    text = "${(progress.value * 100).toInt()}%",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NeonGreen)
                )
            }
            Spacer(Modifier.height(8.dp))
            NeonProgressBar(progress = progress.value, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun PortalSwirl(size: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        CyanAccent.copy(alpha = 0.9f),
                        NeonGreen.copy(alpha = 0.6f),
                        Background
                    ),
                    center = Offset.Unspecified
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(size * 0.60f)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
                        colors = listOf(CyanAccent, NeonGreen, CyanAccent, Background, CyanAccent)
                    )
                )
        )
        Box(
            modifier = Modifier
                .size(size * 0.28f)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Background)
        )
    }
}
