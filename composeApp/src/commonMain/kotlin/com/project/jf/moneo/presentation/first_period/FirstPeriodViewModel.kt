package com.project.jf.moneo.presentation.first_period

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.domain.usecase.SaveControlPeriodUseCase
import com.project.jf.moneo.domain.usecase.SaveHasCompletedOnboardingUseCase
import com.project.jf.moneo.domain.usecase.SaveUserNameUseCase
import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.toDomain
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class FirstPeriodViewModel(
    private val saveHasCompletedOnboardingUseCase: SaveHasCompletedOnboardingUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveControlPeriodUseCase: SaveControlPeriodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FirstPeriodState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<FirstPeriodEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: FirstPeriodIntent) {
        when (intent) {
            is FirstPeriodIntent.UpdatePeriodName -> updatePeriodName(intent.name)
            is FirstPeriodIntent.OnPersonNameChange -> updateOnPersonNameChange(intent.name)
            is FirstPeriodIntent.UpdateStartDate -> updateStartDate(intent.date)
            FirstPeriodIntent.SaveData -> saveData()
            FirstPeriodIntent.NavigateBack -> navigateBack()
        }
    }

    private fun updateOnPersonNameChange(name: String) {
        _state.update {
            it.copy(
                personName = name,
                canProceed = name.isNotBlank(),
                personNameError = name.isBlank()
            )
        }
    }

    private fun updatePeriodName(name: String) {
        _state.update {
            it.copy(
                periodName = name,
                canProceed = name.isNotBlank(),
                periodNameError = name.isBlank()
            )
        }
    }

    private fun updateStartDate(date: LocalDate) {
        _state.update { it.copy(startDate = date) }
        validateDates()
    }

    private fun validateDates() {
        _state.update { currentState ->
            currentState.copy(
                canProceed = currentState.periodName.isNotBlank()
            )
        }
    }

    private fun saveData() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        saveControlPeriodUseCase(
            controlPeriod = ControlPeriodUI(
                name = state.value.periodName,
                startDate = state.value.startDate.toEpochDays(),
                endDate = null
            ).toDomain()
        )
        saveHasCompletedOnboarding()
        saveUserName()
        _state.update { it.copy(isLoading = false) }
        _effects.emit(FirstPeriodEffect.NavigateToNextScreen)
    }

    private fun saveUserName() = viewModelScope.launch {
        saveUserNameUseCase(state.value.personName)
    }

    private fun saveHasCompletedOnboarding() = viewModelScope.launch {
        saveHasCompletedOnboardingUseCase(true)
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effects.emit(FirstPeriodEffect.NavigateBack)
        }
    }
}