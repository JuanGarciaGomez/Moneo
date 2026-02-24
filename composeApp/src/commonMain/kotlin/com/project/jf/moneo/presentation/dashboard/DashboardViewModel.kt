package com.project.jf.moneo.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.domain.usecase.GetAllControlPeriodsUseCase
import com.project.jf.moneo.domain.usecase.GetTransactionsForPeriodUseCase
import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.toUI
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var transactionsJob: kotlinx.coroutines.Job? = null

    fun handleIntent(intent: DashboardIntent) {
        when (intent) {
            DashboardIntent.FetchData -> fetchData()
            is DashboardIntent.PeriodSelected -> handlePeriodSelected(intent.period)
        }
    }

    private fun handlePeriodSelected(period: ControlPeriodUI) {
        _state.update { it.copy(periodSelected = period) }
        fetchTransactions(period.id)
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getAllControlPeriodsUseCase().collect { periods ->
                val periodsUI = periods.map { it.toUI() }
                val initialPeriod = periodsUI.firstOrNull()
                _state.update {
                    it.copy(
                        allPeriods = periodsUI,
                        periodSelected = initialPeriod,
                        isLoading = false
                    )
                }
                initialPeriod?.let { fetchTransactions(it.id) }
            }
        }
    }

    private fun fetchTransactions(periodId: Long) {
        transactionsJob?.cancel()
        transactionsJob = viewModelScope.launch {
            getTransactionsForPeriodUseCase(periodId).collect { transactions ->
                _state.update {
                    it.copy(transactions = transactions.map { t -> t.toUI() })
                }
            }
        }
    }

    override fun onCleared() {
        transactionsJob?.cancel()
        super.onCleared()
    }
}