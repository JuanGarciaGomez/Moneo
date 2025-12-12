package com.project.jf.moneo.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingState())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingEffect>()
    val effects = _effects.asSharedFlow()

    init {
        viewModelScope.launch {
            val hasCompleted = userPreferencesRepository.getHasCompletedOnboarding()
            if (hasCompleted) {
                _effects.emit(OnboardingEffect.NavigateToFirstPeriod)
            }
        }
    }

    fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.ContinueOnboarding -> {
                viewModelScope.launch {
                    userPreferencesRepository.saveHasCompletedOnboarding(true)
                    _effects.emit(OnboardingEffect.NavigateToFirstPeriod)
                }
            }
        }
    }
}