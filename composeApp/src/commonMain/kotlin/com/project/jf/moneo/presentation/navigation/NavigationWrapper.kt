package com.project.jf.moneo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.jf.moneo.presentation.first_period.FirstPeriodScreen
import com.project.jf.moneo.presentation.navigation.routes.Routes
import com.project.jf.moneo.presentation.onboarding.OnboardingScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Onboarding) {
        composable<Routes.Onboarding> {
            OnboardingScreen {
                navController.navigate(Routes.FirstPeriod)
            }
        }
        composable<Routes.FirstPeriod> {
            FirstPeriodScreen{
                navController.popBackStack()
            }
        }
    }
}