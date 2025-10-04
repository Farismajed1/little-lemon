package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, context: Context){
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isUserOnboarding = sharedPreferences.getBoolean("isUserOnboarding", true)

    NavHost(
        navController = navController,
        startDestination = if(isUserOnboarding) OnboardingScreen.route else HomeScreen.route
    ){
        composable(HomeScreen.route){ Home(navController) }

        composable(OnboardingScreen.route){ Onboarding(navController, context) }

        composable(ProfileScreen.route){ Profile(navController, context) }
    }
}