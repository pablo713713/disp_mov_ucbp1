package com.calyrsoft.ucbp1.di

import com.calyrsoft.ucbp1.features.github.data.remote.GithubApi


import com.calyrsoft.ucbp1.features.github.data.repository.GithubRepository
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import com.calyrsoft.ucbp1.features.github.domain.usecase.FindByNickNameUseCase
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel

import com.calyrsoft.ucbp1.features.login.data.repository.LoginRepository
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository
import com.calyrsoft.ucbp1.features.login.domain.usecase.LoginUseCase
import com.calyrsoft.ucbp1.features.login.presentation.LoginViewModel


import com.calyrsoft.ucbp1.features.dollar.data.repository.DollarQuote
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarQuote
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FindDollarQuoteUseCase
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }


    // 📌 Github
    single<IGithubRepository> { GithubRepository(get()) }
    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get()) }

    // 📌 Login
    single<ILoginRepository> { LoginRepository() }
    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }

    single<IDollarQuote> { DollarQuote() }
    factory { FindDollarQuoteUseCase(get()) }
    viewModel { DollarViewModel(get()) }
}
