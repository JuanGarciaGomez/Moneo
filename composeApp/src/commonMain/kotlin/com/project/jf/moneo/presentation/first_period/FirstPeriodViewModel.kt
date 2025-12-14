package com.project.jf.moneo.presentation.first_period

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class FirstPeriodViewModel : ViewModel() {

    private val _state = MutableStateFlow(FirstPeriodState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<FirstPeriodEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: FirstPeriodIntent) {
        when (intent) {
            is FirstPeriodIntent.UpdatePeriodName -> updatePeriodName(intent.name)
            is FirstPeriodIntent.UpdateStartDate -> updateStartDate(intent.date)
            is FirstPeriodIntent.UpdateEndDate -> updateEndDate(intent.date)
            FirstPeriodIntent.SavePeriod -> savePeriod()
            FirstPeriodIntent.NavigateBack -> navigateBack()
        }
    }

    private fun updatePeriodName(name: String) {
        _state.update {
            it.copy(
                periodName = name,
                canProceed = name.isNotBlank() && (it.errorMessage == null)
            )
        }
    }

    private fun updateStartDate(date: LocalDate) {
        _state.update { it.copy(startDate = date) }
        validateDates()
    }

    private fun updateEndDate(date: LocalDate?) {
        _state.update { it.copy(endDate = date) }
        validateDates()
    }

    private fun validateDates() {
        _state.update { currentState ->
            val isValid = currentState.endDate?.let { end ->
                end >= currentState.startDate
            } ?: true

            currentState.copy(
                errorMessage = if (!isValid) "La fecha final debe ser posterior a la fecha inicial" else null,
                canProceed = currentState.periodName.isNotBlank() && isValid
            )
        }
    }

    private fun savePeriod() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            // Simular guardado (aquí irías a tu repositorio)
            kotlinx.coroutines.delay(1000)

            // TODO: Guardar en repositorio
            // periodRepository.savePeriod(
            //     name = state.value.periodName,
            //     startDate = state.value.startDate,
            //     endDate = state.value.endDate
            // )

            _state.update { it.copy(isLoading = false) }
            _effects.emit(FirstPeriodEffect.NavigateToNextScreen)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effects.emit(FirstPeriodEffect.NavigateBack)
        }
    }
}