package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")   // 👈 Nueva pantalla
    object Home: Screen("home")
    object Github: Screen("github")
    object Profile: Screen("profile")
}
