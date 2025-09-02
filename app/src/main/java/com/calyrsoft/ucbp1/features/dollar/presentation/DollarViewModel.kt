package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FindDollarQuoteUseCase
import com.calyrsoft.ucbp1.features.dollar.domain.model.UserQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DollarViewModel(
    val usecase: FindDollarQuoteUseCase
): ViewModel() {
    sealed class DollarStateUI {
        object Init: DollarStateUI()
        object Loading: DollarStateUI()
        class Error(val message: String): DollarStateUI()
        class Success(val dollar: UserQuote): DollarStateUI()
    }
    private val _state = MutableStateFlow<DollarStateUI>(DollarStateUI.Init)

    val state : StateFlow<DollarStateUI> = _state.asStateFlow()

    fun fetchAlias(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = DollarStateUI.Loading
            val result = usecase.invoke(nickname)

            result.fold(
                onSuccess = {
                        user -> _state.value = DollarStateUI.Success( user )
                },
                onFailure = {
                        error -> _state.value = DollarStateUI.Error(message = error.message ?: "Error desconocido")
                }
            )

//            when {
//                result.isSuccess -> {
//                    val user = result.getOrNull()
//                    _state.value = GithubStateUI.Success( user!! )
//                }
//                result.isFailure -> {
//                    _state.value = GithubStateUI.Error(message = "Error")
//                }
//            }
        }
    }

}

