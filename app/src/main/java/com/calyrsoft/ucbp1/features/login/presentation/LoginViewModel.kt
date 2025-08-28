package com.calyrsoft.ucbp1.features.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    sealed class LoginState {
        object Init: LoginState()
        object Success: LoginState()
        class Error(val message: String): LoginState()
    }

    private val _state = MutableStateFlow<LoginState>(LoginState.Init)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            _state.value = LoginState.Success
        } else {
            _state.value = LoginState.Error("Usuario y/o contraseña no pueden estar vacíos")
        }
    }

    fun resetState() {
        _state.value = LoginState.Init
    }
}
