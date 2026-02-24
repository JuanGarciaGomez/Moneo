package com.project.jf.moneo.presentation.features.onboarding

sealed class OnboardingIntent {
    data object ContinueOnboarding : OnboardingIntent()
}

sealed class OnboardingEffect {
    data object NavigateToFirstPeriod : OnboardingEffect()
}

