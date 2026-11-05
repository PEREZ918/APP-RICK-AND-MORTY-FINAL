package com.proyecto.apprickmorty.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.proyecto.apprickmorty.data.model.User

class AuthRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("rickverse_auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_LOGGED_USER_NAME = "logged_user_name"
        private const val KEY_LOGGED_USER_EMAIL = "logged_user_email"
        private const val PREFIX_USER = "user_account_"

        // Usuario por defecto para prueba directa
        private const val DEFAULT_EMAIL = "admin@rickverse.com"
        private const val DEFAULT_PASS = "123456"
        private const val DEFAULT_NAME = "Rick Sanchez"
    }

    init {
        // Registrar usuario por defecto si no existe
        if (!prefs.contains(PREFIX_USER + DEFAULT_EMAIL)) {
            saveUserCredential(DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_PASS)
        }
    }

    fun login(email: String, pass: String): Result<User> {
        val cleanEmail = email.trim().lowercase()
        val storedData = prefs.getString(PREFIX_USER + cleanEmail, null)
            ?: return Result.failure(Exception("Usuario no registrado"))

        val parts = storedData.split("|")
        if (parts.size != 2) {
            return Result.failure(Exception("Error en credenciales guardadas"))
        }

        val storedName = parts[0]
        val storedPass = parts[1]

        if (storedPass != pass) {
            return Result.failure(Exception("Contraseña incorrecta"))
        }

        // Guardar sesión activa
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putString(KEY_LOGGED_USER_NAME, storedName)
            .putString(KEY_LOGGED_USER_EMAIL, cleanEmail)
            .apply()

        return Result.success(User(name = storedName, email = cleanEmail))
    }

    fun register(name: String, email: String, pass: String): Result<User> {
        val cleanEmail = email.trim().lowercase()
        val cleanName = name.trim()

        if (cleanName.isBlank()) {
            return Result.failure(Exception("El nombre es requerido"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches()) {
            return Result.failure(Exception("Ingrese un correo válido"))
        }
        if (pass.length < 6) {
            return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
        }

        if (prefs.contains(PREFIX_USER + cleanEmail)) {
            return Result.failure(Exception("El correo ya está registrado"))
        }

        saveUserCredential(cleanName, cleanEmail, pass)

        // Iniciar sesión automáticamente
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putString(KEY_LOGGED_USER_NAME, cleanName)
            .putString(KEY_LOGGED_USER_EMAIL, cleanEmail)
            .apply()

        return Result.success(User(name = cleanName, email = cleanEmail))
    }

    fun logout() {
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .remove(KEY_LOGGED_USER_NAME)
            .remove(KEY_LOGGED_USER_EMAIL)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getCurrentUser(): User? {
        if (!isLoggedIn()) return null
        val name = prefs.getString(KEY_LOGGED_USER_NAME, "Dimensioner") ?: "Dimensioner"
        val email = prefs.getString(KEY_LOGGED_USER_EMAIL, "") ?: ""
        return User(name = name, email = email)
    }

    private fun saveUserCredential(name: String, email: String, pass: String) {
        prefs.edit()
            .putString(PREFIX_USER + email.trim().lowercase(), "${name.trim()}|$pass")
            .apply()
    }
}
