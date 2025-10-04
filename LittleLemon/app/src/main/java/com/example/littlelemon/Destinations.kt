package com.example.littlelemon

interface Destinations {
    val route: String
}


object HomeScreen: Destinations {
    override val route = "HomeScreen"
}

object ProfileScreen: Destinations {
    override val route = "ProfileScreen"
}

object OnboardingScreen: Destinations {
    override val route = "OnboardingScreen"
}