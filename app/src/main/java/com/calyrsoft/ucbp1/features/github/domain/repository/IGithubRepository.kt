package com.calyrsoft.ucbp1.features.github.domain.repository

import com.calyrsoft.ucbp1.features.github.domain.model.UserModel
// SEGUNDO: Definir qué operaciones estarán disponibles

interface IGithubRepository {
    fun findByNick(value: String): Result<UserModel>
    // Result<> es como una caja que puede contener éxito o error

}