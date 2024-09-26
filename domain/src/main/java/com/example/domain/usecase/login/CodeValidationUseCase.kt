package com.example.domain.usecase.login

import javax.inject.Inject

class CodeValidationUseCase @Inject constructor() {

    operator fun invoke(code: String): Boolean {
        return code.length == 4
    }

}
