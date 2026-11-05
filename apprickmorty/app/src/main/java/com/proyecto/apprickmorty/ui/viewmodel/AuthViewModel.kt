package com.proyecto.apprickmorty.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.proyecto.apprickmorty.data.model.User
import com.proyecto.apprickmorty.data.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository(application)

    var currentUser by mutableStateOf<User?>(null)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var loginErrorMessage by mutableStateOf<String?>(null)
        private set

    var registerErrorMessage by mutableStateOf<String?>(null)
        private set

    init {
        checkSession()
    }

    fun checkSession() {
        isLoggedIn = repository.isLoggedIn()
        if (isLoggedIn) {
            currentUser = repository.getCurrentUser()
        } else {
            currentUser = null
        }
    }

    fun login(email: String, pass: String, onSuccess: () -> Unit) {
        isLoading = true
        loginErrorMessage = null

        if (email.isBlank() || pass.isBlank()) {
            loginErrorMessage = "Por favor, completa todos los campos."
            isLoading = false
            return
        }

        val result = repository.login(email, pass)
        result.onSuccess { user ->
            currentUser = user
            isLoggedIn = true
            loginErrorMessage = null
            isLoading = false
            onSuccess()
        }.onFailure { exception ->
            loginErrorMessage = exception.message ?: "Error al iniciar sesión."
            isLoading = false
        }
    }

    fun register(name: String, email: String, pass: String, passConfirm: String, onSuccess: () -> Unit) {
        isLoading = true
        registerErrorMessage = null

        if (name.isBlank() || email.isBlank() || pass.isBlank() || passConfirm.isBlank()) {
            registerErrorMessage = "Por favor, completa todos los campos."
            isLoading = false
            return
        }

        if (pass != passConfirm) {
            registerErrorMessage = "Las contraseñas no coinciden."
            isLoading = false
            return
        }

        val result = repository.register(name, email, pass)
        result.onSuccess { user ->
            currentUser = user
            isLoggedIn = true
            registerErrorMessage = null
            isLoading = false
            onSuccess()
        }.onFailure { exception ->
            registerErrorMessage = exception.message ?: "Error al registrarse."
            isLoading = false
        }
    }

    fun logout(onLoggedOut: () -> Unit) {
        repository.logout()
        currentUser = null
        isLoggedIn = false
        onLoggedOut()
    }

    fun clearErrors() {
        loginErrorMessage = null
        registerErrorMessage = null
    }
}
