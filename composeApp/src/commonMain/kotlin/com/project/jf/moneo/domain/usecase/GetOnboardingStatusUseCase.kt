package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository

class GetOnboardingStatusUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean {
        return userPreferencesRepository.getHasCompletedOnboarding()
    }
}