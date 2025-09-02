package com.calyrsoft.ucbp1.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Home: Screen("home")
    object Dollar: Screen("dollar")
    object Github: Screen("github")
    object Profile: Screen("profile")
}
