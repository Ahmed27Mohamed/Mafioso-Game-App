package com.a2004256_ahmedmohamed.mafiosogame

sealed class Screen(val route: String) {
    object Setup : Screen("setup")
    object Game : Screen("game")
    object Splash : Screen("splash")
}