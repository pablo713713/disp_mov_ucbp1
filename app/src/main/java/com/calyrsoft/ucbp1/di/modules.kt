package com.calyrsoft.ucbp1.di

import com.calyrsoft.ucbp1.features.github.data.repository.GithubRepository
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import com.calyrsoft.ucbp1.features.github.domain.usecase.FindByNickNameUseCase
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel
import com.calyrsoft.ucbp1.features.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositorio y casos de uso de Github
    single<IGithubRepository>{ GithubRepository() }
    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get()) }

    // LoginViewModel
    viewModel { LoginViewModel() }

}