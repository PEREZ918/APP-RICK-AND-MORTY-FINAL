package com.proyecto.apprickmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.proyecto.apprickmorty.data.model.RickCharacter
import com.proyecto.apprickmorty.ui.components.EpisodeCard
import com.proyecto.apprickmorty.ui.components.InfoRowCard
import com.proyecto.apprickmorty.ui.components.StatusBadge
import com.proyecto.apprickmorty.ui.components.iOSButton
import com.proyecto.apprickmorty.ui.theme.Background
import com.proyecto.apprickmorty.ui.theme.TextPrimary
import com.proyecto.apprickmorty.ui.theme.TextSecondary
import com.proyecto.apprickmorty.ui.theme.iOSCardBackground
import com.proyecto.apprickmorty.ui.theme.iOSSystemBlue
import com.proyecto.apprickmorty.ui.viewmodel.CharacterViewModel

private val transmissionLogs = listOf(
    Triple("S01E01", "Pilot", "Registro Interdimensional"),
    Triple("S01E06", "Rick Potion No. 9", "Incidente C-137"),
    Triple("S02E04", "Total Rickall", "Cuarentena Parásitos")
)

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBack: () -> Unit,
    viewModel: CharacterViewModel
) {
    LaunchedEffect(characterId) {
        viewModel.loadCharacterById(characterId)
    }

    val character = viewModel.selectedCharacter
    val errorMessage = viewModel.errorMessage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        when {
            character != null -> {
                CharacterDetailContent(character = character, onBack = onBack)
            }
            viewModel.isLoading -> {
                CircularProgressIndicator(
                    color = iOSSystemBlue,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage ?: "No se pudo cargar el personaje.",
                        style = TextStyle(fontSize = 15.sp, color = TextSecondary)
                    )
                    Spacer(Modifier.height(16.dp))
                    iOSButton(
                        text = "Reintentar",
                        onClick = { viewModel.loadCharacterById(characterId) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterDetailContent(character: RickCharacter, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(340.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.5f),
                                Color.Transparent,
                                Background
                            )
                        )
                    )
            )

            // Top Navigation Back Pill
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable(onClick = onBack)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Volver",
                    tint = iOSSystemBlue,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Volver",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = iOSSystemBlue
                    )
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = character.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
                StatusBadge(character.status)
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Especie: ${character.species}",
                style = TextStyle(fontSize = 15.sp, color = TextSecondary)
            )

            Spacer(Modifier.height(24.dp))

            // Info Cards
            InfoRowCard(
                icon = Icons.Default.Public,
                label = "Origen de Origen",
                value = character.origin.name
            )

            Spacer(Modifier.height(12.dp))

            InfoRowCard(
                icon = Icons.Default.LocationOn,
                label = "Última Ubicación Conocida",
                value = character.location.name
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = "REGISTROS INTERDIMENSIONALES",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary,
                    letterSpacing = 0.8.sp
                )
            )

            Spacer(Modifier.height(12.dp))

            transmissionLogs.forEach { (code, title, subtitle) ->
                EpisodeCard(
                    code = code,
                    title = title,
                    subtitle = subtitle,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}
