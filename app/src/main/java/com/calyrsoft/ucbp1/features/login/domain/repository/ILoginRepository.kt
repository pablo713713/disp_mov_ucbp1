package com.calyrsoft.ucbp1.features.login.domain.repository

import com.calyrsoft.ucbp1.features.login.domain.model.AuthUser

interface ILoginRepository {
    suspend fun login(username: String, password: String): Result<AuthUser>
}
