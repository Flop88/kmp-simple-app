package org.company.app.di

import org.company.app.data.remote.repository.AuthRepositoryImpl
import org.company.app.domain.repository.AuthRepository
import org.company.app.domain.usecase.AuthUseCase
import org.company.app.presentation.screens.login.LoginViewModel
import org.company.app.presentation.screens.registration.RegistrationViewModel
import org.koin.dsl.module

private val domainModule = module {
    factory { AuthUseCase() }
}

private val presentationModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
    single { LoginViewModel() }
    single { RegistrationViewModel() }
}

private fun getAllModules() = listOf(
    domainModule, presentationModule
)

fun getSharedModules() = getAllModules()