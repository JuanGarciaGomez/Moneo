package com.project.jf.moneo.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingState())
    val uiState = _uiState.asStateFlow()

    val effects = MutableSharedFlow<OnboardingEffect>()

    fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.ContinueOnboarding -> {
                viewModelScope.launch {
                    effects.emit(OnboardingEffect.NavigateToFirstPeriod)
                }
            }
        }
    }
}