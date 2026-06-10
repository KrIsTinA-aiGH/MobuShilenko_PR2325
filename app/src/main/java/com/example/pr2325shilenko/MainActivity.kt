package com.example.pr2325shilenko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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
    var showOnboarding by remember { mutableStateOf(true) }
    var currentScreen by remember { mutableStateOf("login") }

    if (showOnboarding) {
        OnboardingScreen(onSkipClick = {
            showOnboarding = false
        })
    } else {
        when (currentScreen) {
            "login" -> LoginScreen(onLoginClick = {
                currentScreen = "email"
            })
            "email" -> EmailCodeScreen(onCodeVerified = {
                currentScreen = "password"
            })
            "password" -> CreatePasswordScreen(onPasswordCreated = {
                currentScreen = "card"
            })
            "card" -> CreateCardScreen(onCardCreated = {
                currentScreen = "main"
            })
            "main" -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Главный экран",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}