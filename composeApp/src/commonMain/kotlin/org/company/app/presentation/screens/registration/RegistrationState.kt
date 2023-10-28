package org.company.app.presentation.screens.registration

data class RegistrationState(
    val isRegistrationSuccess: Boolean = false,
    val emailText: String = "",
    val passwordText: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)