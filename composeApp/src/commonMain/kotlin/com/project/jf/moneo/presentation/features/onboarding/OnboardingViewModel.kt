package com.project.jf.moneo.presentation.features.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.domain.usecase.GetOnboardingStatusUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getOnboardingStatusUseCase: GetOnboardingStatusUseCase
) : ViewModel() {

    private val _effects = MutableSharedFlow<OnboardingEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.ContinueOnboarding -> {
                viewModelScope.launch {
                    _effects.emit(OnboardingEffect.NavigateToFirstPeriod)
                }
            }
        }
    }
}