package com.proyecto.apprickmorty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.proyecto.apprickmorty.data.model.RickCharacter
import com.proyecto.apprickmorty.ui.components.EpisodeCard
import com.proyecto.apprickmorty.ui.components.RingAvatar
import com.proyecto.apprickmorty.ui.components.StatusBadge
import com.proyecto.apprickmorty.ui.components.iOSButton
import com.proyecto.apprickmorty.ui.components.iOSButtonStyle
import com.proyecto.apprickmorty.ui.components.iOSTextField
import com.proyecto.apprickmorty.ui.theme.Background
import com.proyecto.apprickmorty.ui.theme.NeonGreen
import com.proyecto.apprickmorty.ui.theme.TextPrimary
import com.proyecto.apprickmorty.ui.theme.TextSecondary
import com.proyecto.apprickmorty.ui.theme.iOSCardBackground
import com.proyecto.apprickmorty.ui.theme.iOSSeparator
import com.proyecto.apprickmorty.ui.theme.iOSSystemBlue
import com.proyecto.apprickmorty.ui.theme.iOSSystemRed
import com.proyecto.apprickmorty.ui.viewmodel.AuthViewModel
import com.proyecto.apprickmorty.ui.viewmodel.CharacterViewModel

private data class ChronicleEntry(val code: String, val title: String, val date: String)

private val chronicles = listOf(
    ChronicleEntry("S01E01", "Pilot", "2 de Diciembre, 2013"),
    ChronicleEntry("S01E02", "Lawnmower Dog", "9 de Diciembre, 2013"),
    ChronicleEntry("S01E03", "Anatomy Park", "16 de Diciembre, 2013"),
    ChronicleEntry("S01E04", "M. Night Shaym-Aliens!", "13 de Enero, 2014"),
    ChronicleEntry("S01E05", "Meeseeks and Destroy", "20 de Enero, 2014")
)

@Composable
fun HomeScreen(
    characterViewModel: CharacterViewModel,
    authViewModel: AuthViewModel,
    onCastMemberClick: (Int) -> Unit,
    onLogout: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(searchQuery) {
        characterViewModel.loadCharacters(searchQuery)
    }

    val characters = characterViewModel.characters
    val isLoading = characterViewModel.isLoading
    val errorMessage = characterViewModel.errorMessage
    val currentUser = authViewModel.currentUser

    Scaffold(
        containerColor = Background,
        bottomBar = {
            CupertinoBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> HomeTabView(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    characters = characters,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    userName = currentUser?.name ?: "Multiverso",
                    onCastMemberClick = onCastMemberClick
                )

                1 -> CharactersTabView(
                    characters = characters,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    onCastMemberClick = onCastMemberClick
                )

                2 -> ProfileTabView(
                    userName = currentUser?.name ?: "Usuario",
                    userEmail = currentUser?.email ?: "admin@rickverse.com",
                    onLogout = onLogout
                )
            }
        }
    }
}

@Composable
private fun HomeTabView(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    characters: List<RickCharacter>,
    isLoading: Boolean,
    errorMessage: String?,
    userName: String,
    onCastMemberClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        // Header Large Title iOS
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "R I C K V E R S E",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = iOSSystemBlue,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Text(
                        text = "Hola, $userName",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }
                StatusBadge("ONLINE")
            }

            Spacer(Modifier.height(16.dp))

            // iOS Search Bar
            iOSTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = "Buscar personaje...",
                leadingIcon = Icons.Default.Search
            )

            Spacer(Modifier.height(24.dp))

            iOSSectionHeader(title = "PERSONAJES DESTACADOS")
            Spacer(Modifier.height(14.dp))
        }

        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = iOSSystemBlue, strokeWidth = 2.5.dp)
                }
            }
        }

        if (!errorMessage.isNullOrBlank()) {
            item {
                Text(
                    text = "Error: $errorMessage",
                    style = TextStyle(fontSize = 13.sp, color = iOSSystemRed),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        if (characters.isNotEmpty()) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(characters) { character ->
                        RingAvatar(
                            imageUrl = character.image,
                            name = character.name,
                            isSelected = false,
                            onClick = { onCastMemberClick(character.id) }
                        )
                    }
                }

                Spacer(Modifier.height(28.dp))
                iOSSectionHeader(title = "CRÓNICAS Y EPISODIOS")
                Spacer(Modifier.height(14.dp))
            }
        }

        items(chronicles) { entry ->
            EpisodeCard(
                code = entry.code,
                title = entry.title,
                subtitle = entry.date,
                onClick = { },
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@Composable
private fun CharactersTabView(
    characters: List<RickCharacter>,
    isLoading: Boolean,
    errorMessage: String?,
    onCastMemberClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            Text(
                text = "Personajes",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            Text(
                text = "Directorio de entes interdimensionales",
                style = TextStyle(fontSize = 14.sp, color = TextSecondary)
            )
            Spacer(Modifier.height(20.dp))
        }

        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = iOSSystemBlue)
                }
            }
        }

        items(characters) { character ->
            iOSCharacterRow(character = character, onClick = { onCastMemberClick(character.id) })
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun iOSCharacterRow(character: RickCharacter, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(iOSCardBackground)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = character.name,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "${character.species} • ${character.status}",
                style = TextStyle(fontSize = 13.sp, color = TextSecondary)
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ProfileTabView(
    userName: String,
    userEmail: String,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Perfil",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(30.dp))

        // Profile Avatar Card iOS
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(iOSSystemBlue.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = iOSSystemBlue,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = userName,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        )

        Text(
            text = userEmail,
            style = TextStyle(fontSize = 14.sp, color = TextSecondary)
        )

        Spacer(Modifier.height(36.dp))

        // Grouped Settings List iOS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(iOSCardBackground)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            iOSProfileRow(title = "Dimension Activa", value = "C-137")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(iOSSeparator)
            )
            iOSProfileRow(title = "Estado de Credenciales", value = "Verificado")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(iOSSeparator)
            )
            iOSProfileRow(title = "Versión de App", value = "1.0.0 iOS")
        }

        Spacer(Modifier.weight(1f))

        // Logout Button
        iOSButton(
            text = "Cerrar Sesión",
            style = iOSButtonStyle.Destructive,
            icon = Icons.Default.Logout,
            onClick = onLogout
        )

        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun iOSProfileRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = TextStyle(fontSize = 15.sp, color = TextPrimary))
        Text(value, style = TextStyle(fontSize = 15.sp, color = TextSecondary))
    }
}

@Composable
private fun iOSSectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary,
            letterSpacing = 0.8.sp
        )
    )
}

@Composable
private fun CupertinoBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = iOSCardBackground,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            Triple("Inicio", Icons.Default.Home, 0),
            Triple("Personajes", Icons.Default.People, 1),
            Triple("Perfil", Icons.Default.Person, 2)
        )

        items.forEach { (label, icon, tabIndex) ->
            val isSelected = selectedTab == tabIndex
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tabIndex) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = iOSSystemBlue,
                    selectedTextColor = iOSSystemBlue,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary,
                    indicatorColor = iOSCardBackground
                )
            )
        }
    }
}
