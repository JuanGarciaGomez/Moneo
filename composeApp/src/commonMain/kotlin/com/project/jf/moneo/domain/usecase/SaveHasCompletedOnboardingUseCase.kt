package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository

class SaveHasCompletedOnboardingUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(hasCompletedOnboarding: Boolean) {
        userPreferencesRepository.saveHasCompletedOnboarding(hasCompletedOnboarding)
    }
}