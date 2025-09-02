package com.calyrsoft.ucbp1.features.github.domain.repository

import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
// SEGUNDO: Definir qué operaciones estarán disponibles

interface IGithubRepository {
    suspend fun findByNick(value: String): Result<UserModel>
}