package com.project.jf.moneo.domain.usecase

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository

class SaveUserNameUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(userName: String) {
        userPreferencesRepository.saveUserName(userName)
    }
}