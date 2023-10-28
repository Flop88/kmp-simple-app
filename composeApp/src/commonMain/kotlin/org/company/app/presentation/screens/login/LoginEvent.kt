package org.company.app.presentation.screens.login

sealed interface LoginEvent {
    data object OnLogin : LoginEvent
    data class OnEmailChanged(val value: String): LoginEvent
    data class OnPasswordChanged(val value: String): LoginEvent
    data object OnSetDefaultState: LoginEvent
}