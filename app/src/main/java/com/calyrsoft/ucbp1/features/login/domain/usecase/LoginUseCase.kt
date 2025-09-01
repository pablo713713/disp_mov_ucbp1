package com.calyrsoft.ucbp1.features.login.domain.usecase

import com.calyrsoft.ucbp1.features.login.domain.model.AuthUser
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository

class LoginUseCase(
    private val repository: ILoginRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<AuthUser> {
        return repository.login(username, password)
    }
}
