package com.project.jf.moneo.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.jf.moneo.domain.usecase.GetAllControlPeriodsUseCase
import com.project.jf.moneo.presentation.model.toUI
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getAllControlPeriodsUseCase: GetAllControlPeriodsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<DashboardEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: DashboardIntent) {
        when (intent) {
            DashboardIntent.FetchData -> fetchData()
            is DashboardIntent.BottomNavSelected -> handleBottomNavSelected(intent)
        }
    }

    private fun handleBottomNavSelected(navSelected: DashboardIntent.BottomNavSelected){
        _state.update { it.copy(bottomNavSelected = navSelected.index) }
    }

    private fun fetchData() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        getAllControlPeriodsUseCase().collect { allPeriods ->
            val allPeriodsUI = allPeriods.map { it.toUI() }
            _state.update { it.copy(allPeriods = allPeriodsUI, isLoading = false) }
        }
    }

}