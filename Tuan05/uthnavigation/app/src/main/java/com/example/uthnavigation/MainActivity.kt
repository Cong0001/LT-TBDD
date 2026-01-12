package com.example.uthnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.uthnavigation.navigation.Screen
import com.example.uthnavigation.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UthnavigationTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashScreen(navController)
                    }
                    composable(Screen.Onboarding.route) {
                        OnboardingScreen(navController)
                    }
                    composable(Screen.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
