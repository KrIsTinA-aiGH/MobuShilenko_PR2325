package com.example.pr2325shilenko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pr2325shilenko.ui.theme.Pr2325ShilenkoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr2325ShilenkoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onSkipClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Login.route) {
            LoginScreen(onLoginClick = {
                navController.navigate(Screen.EmailCode.route)
            })
        }

        composable(Screen.EmailCode.route) {
            EmailCodeScreen(onCodeVerified = {
                navController.navigate(Screen.CreatePassword.route)
            })
        }

        composable(Screen.CreatePassword.route) {
            CreatePasswordScreen(onPasswordCreated = {
                navController.navigate(Screen.CreateCard.route)
            })
        }

        composable(Screen.CreateCard.route) {
            CreateCardScreen(onCardCreated = {
                navController.navigate(Screen.Analyses.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Analyses.route) {
            MainScreenWithBottomNav(navController, startTab = 0)
        }
        composable(Screen.Results.route) {
            MainScreenWithBottomNav(navController, startTab = 1)
        }
        composable(Screen.Support.route) {
            MainScreenWithBottomNav(navController, startTab = 2)
        }
        composable(Screen.Profile.route) {
            MainScreenWithBottomNav(navController, startTab = 3)
        }
    }
}