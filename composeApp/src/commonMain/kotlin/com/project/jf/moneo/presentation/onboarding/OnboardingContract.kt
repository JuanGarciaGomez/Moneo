package com.project.jf.moneo.presentation.onboarding


data class OnboardingState(
    val hasCompletedOnboarding: Boolean = false
)

sealed class OnboardingIntent {
    data object ContinueOnboarding : OnboardingIntent()
}

sealed class OnboardingEffect {
    data object NavigateToFirstPeriod : OnboardingEffect()
}

