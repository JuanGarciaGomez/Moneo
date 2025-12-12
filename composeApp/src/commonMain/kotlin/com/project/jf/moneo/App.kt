package com.project.jf.moneo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.jf.moneo.presentation.first_period.FirstPeriodScreen
import com.project.jf.moneo.presentation.navigation.routes.Routes
import com.project.jf.moneo.presentation.onboarding.OnboardingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
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
        }
    }
}