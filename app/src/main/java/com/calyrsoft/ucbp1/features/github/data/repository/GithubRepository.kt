package com.calyrsoft.ucbp1.features.github.data.repository

import com.calyrsoft.ucbp1.features.github.data.remote.GithubApi
import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository

class GithubRepository(
    private val api: GithubApi
) : IGithubRepository {

    override suspend fun findByNick(value: String): Result<UserModel> {
        if (value.isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacío"))
        }

        return try {
            val response = api.getUser(value)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(
                        UserModel(
                            nickname = body.login,
                            pathUrl = body.avatar_url
                        )
                    )
                } else {
                    Result.failure(Exception("Usuario no encontrado"))
                }
            } else {
                Result.failure(Exception("Error ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
