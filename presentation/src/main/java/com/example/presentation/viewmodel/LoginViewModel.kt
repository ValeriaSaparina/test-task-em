package com.example.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.usecase.login.EmailValidationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val emailValidationUseCase: EmailValidationUseCase,
) : BaseViewModel() {

    fun login(email: String) {
        viewModelScope.launch {
            _loading.emit(true)
            if (emailValidationUseCase.invoke(email)) {
                _success.emit(true)
            } else {
                _error.emit(true)
            }
            _loading.emit(false)
        }
    }

    fun clearResults() {
        viewModelScope.launch {
            _success.emit(false)
            _error.emit(false)
        }
    }

}