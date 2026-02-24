package com.project.jf.moneo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.jf.moneo.presentation.dashboard.DashboardRouter
import com.project.jf.moneo.presentation.first_period.FirstPeriodScreen
import com.project.jf.moneo.presentation.navigation.routes.MainRoute
import com.project.jf.moneo.presentation.onboarding.OnboardingScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainRoute.Onboarding) {
        composable<MainRoute.Onboarding> {
            OnboardingScreen {
                navController.navigate(MainRoute.FirstPeriod)
            }
        }
        composable<MainRoute.FirstPeriod> {
            FirstPeriodScreen(onNavigateToDashboard = {
                navController.navigate(MainRoute.Dashboard) {
                    popUpTo(MainRoute.Onboarding) { inclusive = true }
                }
            }) {
                navController.popBackStack()
            }
        }

        composable<MainRoute.Dashboard> {
            DashboardRouter()
        }
    }
}