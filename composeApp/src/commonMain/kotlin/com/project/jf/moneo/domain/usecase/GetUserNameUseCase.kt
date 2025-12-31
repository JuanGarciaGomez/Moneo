package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository

class GetUserNameUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    operator fun invoke(): String {
        return userPreferencesRepository.getUserName()
    }
}