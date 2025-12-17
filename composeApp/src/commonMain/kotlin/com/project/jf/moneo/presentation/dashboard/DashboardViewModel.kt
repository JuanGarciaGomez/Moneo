package com.project.jf.moneo.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.domain.usecase.GetAllControlPeriodsUseCase
import com.project.jf.moneo.domain.usecase.GetTransactionsForPeriodUseCase
import com.project.jf.moneo.presentation.model.BottomNavigationItems
import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.toUI
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getAllControlPeriodsUseCase: GetAllControlPeriodsUseCase,
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<DashboardEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: DashboardIntent) {
        when (intent) {
            DashboardIntent.FetchData -> fetchData()
            is DashboardIntent.BottomNavSelected -> handleBottomNavSelected(intent.item)
            is DashboardIntent.PeriodSelected -> handlePeriodSelected(intent.period)
        }
    }

    private fun handlePeriodSelected(intent: ControlPeriodUI) {
        _state.update { it.copy(periodSelected = intent) }
    }

    private fun handleBottomNavSelected(navSelected: BottomNavigationItems) {
        _state.update { it.copy(bottomNavSelected = navSelected) }
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            combine(
                getAllControlPeriodsUseCase(),
                getTransactionsForPeriodUseCase(
                    state.value.periodSelected?.id ?: 1L
                )
            ) { periods, transactions ->
                val periodsUI = periods.map { it.toUI() }
                val transactionsUI = transactions.map { it.toUI() }
                periodsUI to transactionsUI
            }.collect { (periodsUI, transactionsUI) ->
                _state.update {
                    it.copy(
                        allPeriods = periodsUI,
                        transactions = transactionsUI,
                        periodSelected = periodsUI.firstOrNull(),
                        isLoading = false
                    )
                }
            }
        }
    }

}