package com.calyrsoft.ucbp1.features.login.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.login.domain.model.AuthUser
import com.calyrsoft.ucbp1.features.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    sealed class LoginStateUI {
        object Init : LoginStateUI()
        object Loading : LoginStateUI()
        data class Success(val user: AuthUser) : LoginStateUI()
        data class Error(val message: String) : LoginStateUI()
    }

    private val _state = MutableStateFlow<LoginStateUI>(LoginStateUI.Init)
    val state: StateFlow<LoginStateUI> = _state.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = LoginStateUI.Loading
            val result = loginUseCase(username, password)
            result.fold(
                onSuccess = { user -> _state.value = LoginStateUI.Success(user) },
                onFailure = { error -> _state.value = LoginStateUI.Error(error.message ?: "Error desconocido") }
            )
        }
    }
}

