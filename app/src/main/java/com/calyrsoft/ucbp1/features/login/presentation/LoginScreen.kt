package com.calyrsoft.ucbp1.features.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import com.calyrsoft.ucbp1.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = koinViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(
            onClick = { vm.login(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        when (val st = state) {
            is LoginViewModel.LoginStateUI.Init -> Text("Ingrese sus credenciales")
            is LoginViewModel.LoginStateUI.Loading -> Text("Cargando...")
            is LoginViewModel.LoginStateUI.Error -> Text("Error: ${st.message}")
            is LoginViewModel.LoginStateUI.Success -> {
                Text("Bienvenido ${st.user.displayName}")
                // Navegar a Github después del login exitoso
                navController.navigate(Screen.Dollar.route)
            }
        }
    }
}

