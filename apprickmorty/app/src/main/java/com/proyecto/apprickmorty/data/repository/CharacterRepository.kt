package com.proyecto.apprickmorty.data.repository

import com.proyecto.apprickmorty.data.model.RickCharacter
import com.proyecto.apprickmorty.data.remote.RetrofitClient

class CharacterRepository {

    private val api = RetrofitClient.api

    suspend fun getCharacters(name: String? = null): Result<List<RickCharacter>> {
        return try {
            val response = api.getCharacters(name)
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCharacterById(id: Int): Result<RickCharacter> {
        return try {
            Result.success(api.getCharacterById(id))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
