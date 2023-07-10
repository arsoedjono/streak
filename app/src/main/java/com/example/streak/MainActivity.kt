package com.example.streak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.streak.ui.screen.home.HomeScreen
import com.example.streak.ui.theme.StreakTheme
import com.example.streak.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreakTheme {
                val navController = rememberNavController()
                
                NavHost(
                    navController = navController,
                    startDestination = Routes.Home.name
                ) {
                    composable(Routes.Home.name) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
