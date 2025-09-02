package com.calyrsoft.ucbp1.features.dollar.domain.repository

import com.calyrsoft.ucbp1.features.dollar.domain.model.UserQuote
// SEGUNDO: Definir qué operaciones estarán disponibles

interface IDollarQuote {
    fun findDollarQuote(value: String): Result<UserQuote>
    // Result<> es como una caja que puede contener éxito o error

}