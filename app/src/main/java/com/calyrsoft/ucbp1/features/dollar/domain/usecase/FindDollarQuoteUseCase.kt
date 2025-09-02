package com.calyrsoft.ucbp1.features.dollar.domain.usecase



import com.calyrsoft.ucbp1.features.dollar.domain.model.UserQuote
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarQuote
import kotlinx.coroutines.delay

class FindDollarQuoteUseCase(
    val repository: IDollarQuote
) {
    suspend fun invoke(nickname: String) : Result<UserQuote>  {
        delay(5000)
        return repository.findDollarQuote(nickname)
    }
}