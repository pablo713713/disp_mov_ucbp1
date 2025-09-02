package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.domain.model.UserQuote
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarQuote

class DollarQuote: IDollarQuote{
    override fun findDollarQuote(value: String): Result<UserQuote> {
        if(value.isEmpty()) {
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        // Devuelve datos de ejemplo (luego se conectará a API real)

        return Result.success(
            UserQuote(
                "dollar",
                "https://avatars.githubusercontent.com/u/874321?v=4"
            )
        )

    }
}
