package com.example.todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.presentation.edit_note.Edit
import com.example.todo.presentation.home_screen.Home
import com.example.todo.presentation.splash_screen.Splash_screen
import com.example.todo.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "SplashScreen" ){
                composable(Screen.Splashscreen.screen){
                    Splash_screen(navController)
                }
                composable(Screen.Home.screen){
                    Home(navController = navController)
                }
                composable(Screen.Edit.screen + "?id={id}" ,
                    arguments = listOf(
                        navArgument(
                            name = "id"
                        ){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                    ){
                    Edit(navController = navController )
                }
            }
        }
    }
}
