package com.proyecto.apprickmorty.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.proyecto.apprickmorty.data.model.Origin
import com.proyecto.apprickmorty.data.model.RickCharacter
import com.proyecto.apprickmorty.ui.components.StatusBadge
import com.proyecto.apprickmorty.ui.theme.ApprickmortyTheme
import com.proyecto.apprickmorty.ui.theme.RickverseType
import com.proyecto.apprickmorty.ui.theme.SurfaceCard
import com.proyecto.apprickmorty.ui.theme.TextSecondary

@Composable
fun CharacterCard(
    character: RickCharacter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(character.name, style = RickverseType.BodyPrimary)
                Spacer(Modifier.height(2.dp))
                Text(character.species, style = RickverseType.BodySecondary, color = TextSecondary)
            }
            StatusBadge(character.status)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    ApprickmortyTheme {
        CharacterCard(
            character = RickCharacter(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                origin = Origin("Earth")
            ),
            onClick = {}
        )
    }
}
