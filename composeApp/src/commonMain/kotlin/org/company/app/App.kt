package org.company.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.company.app.presentation.screens.login.LoginScreen
import org.company.app.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
internal fun App() = AppTheme {
    KoinContext {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigator(LoginScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}

internal expect fun openUrl(url: String?)