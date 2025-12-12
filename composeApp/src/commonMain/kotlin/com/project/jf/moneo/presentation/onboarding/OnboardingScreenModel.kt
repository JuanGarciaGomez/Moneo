package com.project.jf.moneo.presentation.onboarding

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingScreenModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(OnboardingState())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingEffect>()
    val effects = _effects.asSharedFlow()

    private val scope = screenModelScope

    init {
        scope.launch {
            val hasCompleted = userPreferencesRepository.getHasCompletedOnboarding()
            if (hasCompleted) {
                _effects.emit(OnboardingEffect.NavigateToFirstPeriod)
            }
        }
    }

    fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.ContinueOnboarding -> {
                scope.launch {
                    userPreferencesRepository.saveHasCompletedOnboarding(true)
                    _effects.emit(OnboardingEffect.NavigateToFirstPeriod)
                }
            }
        }
    }
}