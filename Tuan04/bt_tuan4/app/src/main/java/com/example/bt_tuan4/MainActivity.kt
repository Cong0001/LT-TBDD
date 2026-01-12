package com.example.bt_tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController) }
                composable("list") { ComponentsListScreen(navController) }
                composable("text") { TextDetailScreen(navController) }
                composable("image") { ImageScreen(navController) }
                composable("input") { TextFieldScreen(navController) }
                composable("column") { ColumnLayoutScreen(navController) }
                composable("row") { RowLayoutScreen(navController) }
                composable("box") { BoxLayoutScreen(navController) }
            }
        }
    }
}