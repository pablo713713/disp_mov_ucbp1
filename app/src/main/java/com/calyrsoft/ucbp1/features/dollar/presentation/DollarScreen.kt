package com.calyrsoft.ucbp1.features.dollar.presentation

import com.calyrsoft.ucbp1.features.dollar.presentation.DollarViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel
import com.calyrsoft.ucbp1.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun DollarScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm: DollarViewModel = koinViewModel()
) {

    var nickname by remember { mutableStateOf("") }

    val state by vm.state.collectAsState()

    Column {
        Text("")
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                    it -> nickname = it
            }
        )
        OutlinedButton( onClick = {
            vm.fetchAlias(nickname)
        }) {
            Text("Buscar")
        }
        Button(
            onClick = { navController.navigate(Screen.Github.route) },
            modifier = Modifier
        ) {
            Text("Ir a Github")
        }
        when( val st = state) {
            is DollarViewModel.DollarStateUI.Error -> {
                Text(st.message )
            }
            DollarViewModel.DollarStateUI.Init -> {
                Text("Init" )
            }
            DollarViewModel.DollarStateUI.Loading -> {
                Text("Loading" )
            }
            is DollarViewModel.DollarStateUI.Success -> {
                Text(st.dollar.nickname )
                AsyncImage(
                    model = st.dollar.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(st.dollar.pathUrl)
            }
        }
    }
}