package com.example.pr2325shilenko

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object EmailCode : Screen("email_code")
    object CreatePassword : Screen("create_password")
    object CreateCard : Screen("create_card")
    object Analyses : Screen("analyses")
    object Results : Screen("results")
    object Support : Screen("support")
    object Profile : Screen("profile")
}