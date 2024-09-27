package com.example.presentation.viewmodel

import com.example.base.base.BaseViewModel
import com.example.domain.usecase.login.CodeValidationUseCase
import javax.inject.Inject

class Login2ViewModel @Inject constructor(
    private val codeValidationUseCase: CodeValidationUseCase,
) : BaseViewModel() {
    fun isValidCode(code: String): Boolean {
        return codeValidationUseCase.invoke(code)
    }


}
