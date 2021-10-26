package com.example.todo.presentation.util

sealed class Screen  (val screen : String) {
    object Splashscreen : Screen("SplashScreen")
    object Home : Screen("Home")
    object Edit : Screen("Edit")
}
