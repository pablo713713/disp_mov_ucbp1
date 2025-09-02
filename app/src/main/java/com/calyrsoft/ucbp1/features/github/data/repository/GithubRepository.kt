package com.calyrsoft.ucbp1.features.github.data.repository

import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
// TERCERO: Implementar las operaciones reales
class GithubRepository: IGithubRepository {
    override fun findByNick(value: String): Result<UserModel> {
        if(value.isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        // Devuelve datos de ejemplo (luego se conectará a API real)

        return Result.success(
            UserModel(
                "calyr",
                "https://avatars.githubusercontent.com/u/874321?v=4"
            )
        )

    }
}