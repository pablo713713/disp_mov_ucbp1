package com.calyrsoft.ucbp1.features.login.data.repository

import com.calyrsoft.ucbp1.features.login.domain.model.AuthUser
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository

class LoginRepository : ILoginRepository {
    override suspend fun login(username: String, password: String): Result<AuthUser> {
        // 🔹 Aquí simulamos un login básico
        return if (username == "admin" && password == "1234") {
            Result.success(
                AuthUser(
                    id = "1",
                    username = username,
                    displayName = "Administrador"
                )
            )
        } else {
            Result.failure(Exception("Usuario o contraseña incorrectos"))
        }
    }
}
