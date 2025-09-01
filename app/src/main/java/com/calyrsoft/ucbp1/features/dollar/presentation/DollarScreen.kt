package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import java.net.URL
import org.json.JSONObject

@Composable
fun DollarScreen(onBack: () -> Unit) {
    var dollarPrice by remember { mutableStateOf("Cargando...") }

    // Usamos efecto para cargar la API
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                // API gratuita del dólar (ejemplo: dolarapi.com)
                val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
                val json = JSONObject(response)
                val rate = json.getJSONObject("rates").getDouble("BOB") // dólar a bolivianos
                dollarPrice = "1 USD = $rate BOB"
            } catch (e: Exception) {
                dollarPrice = "Error al cargar datos"
            }
        }
    }

    // UI
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cotización del Dólar", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        Text(dollarPrice, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { onBack() }) {
            Text("Volver")
        }
    }
}
