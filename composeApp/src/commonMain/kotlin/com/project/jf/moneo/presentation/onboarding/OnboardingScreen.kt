package com.project.jf.moneo.presentation.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        OnboardingRender()
    }
}