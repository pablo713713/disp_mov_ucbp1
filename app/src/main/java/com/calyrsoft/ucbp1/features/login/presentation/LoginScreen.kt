package com.calyrsoft.ucbp1.features.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.calyrsoft.ucbp1.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = koinViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = {
                vm.login(username, password)
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Iniciar sesión")
        }

        when(state) {
            is LoginViewModel.LoginState.Error -> {
                Text(
                    text = (state as LoginViewModel.LoginState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            is LoginViewModel.LoginState.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Github.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                    vm.resetState() // Reinicia estado si se vuelve a la pantalla
                }
            }
            else -> {}
        }
    }
}
