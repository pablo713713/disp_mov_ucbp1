package com.calyrsoft.ucbp1.features.github.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import com.calyrsoft.ucbp1.navigation.Screen

@Composable
fun GithubScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm: GithubViewModel = koinViewModel()
) {
    var nickname by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    Column {
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it }
        )
        OutlinedButton(onClick = { vm.fetchAlias(nickname) }) {
            Text("Buscar")
        }
        // 👉 Botón para ir a DollarScreen
        Button(
            onClick = { navController.navigate(Screen.Dollar.route) },
            modifier = Modifier
        ) {
            Text("Ir a Dólar")
        }
        when (val st = state) {
            is GithubViewModel.GithubStateUI.Error -> {
                Text(st.message)
            }
            GithubViewModel.GithubStateUI.Init -> {
                Text("Init")
            }
            GithubViewModel.GithubStateUI.Loading -> {
                Text("Loading")
            }
            is GithubViewModel.GithubStateUI.Success -> {
                Text(st.github.nickname)
                AsyncImage(
                    model = st.github.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(st.github.pathUrl)


            }
        }
    }
}
