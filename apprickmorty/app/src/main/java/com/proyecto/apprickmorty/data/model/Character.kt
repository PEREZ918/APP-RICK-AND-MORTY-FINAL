package com.proyecto.apprickmorty.data.model

data class CharacterResponse(
    val results: List<RickCharacter>
)

data class RickCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: NamedLocation,
    val location: NamedLocation = NamedLocation(name = "Unknown")
)

data class NamedLocation(
    val name: String
)

// Alias para no romper código previo que referenciaba "Origin"
typealias Origin = NamedLocation
