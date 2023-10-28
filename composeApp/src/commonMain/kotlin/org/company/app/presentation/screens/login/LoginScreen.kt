package org.company.app.presentation.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.company.app.presentation.screens.registration.RegistrationScreen
import org.company.app.theme.LocalThemeIsDark
import org.koin.compose.koinInject

class LoginScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel: LoginViewModel = koinInject()
        val uiState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        println("LoginScreen State: $uiState")

        when {
            viewModel.state.value.isLoading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = Color.Red)
                }
            }

            viewModel.state.value.isLoginSuccess -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Main Screen")
                }
            }

            viewModel.state.value.errorMessage.isNullOrEmpty().not() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = uiState.errorMessage.orEmpty(), color = Color.Red
                    )
                }
            }

            else -> {
                LoginContent(uiState, viewModel, navigator)
            }
        }

    }


    @Composable
    private fun LoginContent(
        uiState: LoginState,
        viewModel: LoginViewModel,
        navigator: Navigator,
    ) {
        var passwordVisibility by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.weight(1.0f))

                var isDark by LocalThemeIsDark.current
                IconButton(onClick = { isDark = !isDark }) {
                    Icon(
                        modifier = Modifier.padding(8.dp).size(20.dp),
                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = null
                    )
                }
            }

            OutlinedTextField(value = uiState.emailText,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.OnEmailChanged(it))
                },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            OutlinedTextField(value = uiState.passwordText,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.OnPasswordChanged(it))
                },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val imageVector =
                            if (passwordVisibility) Icons.Default.Close else Icons.Default.Edit
                        Icon(
                            imageVector,
                            contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                        )
                    }
                })

            Button(
                onClick = {
                    viewModel.onEvent(LoginEvent.OnLogin)
                }, modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Login")
            }

            Text(
                text = "register",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        navigator.push(RegistrationScreen())
                    }
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }


}