package com.project.jf.moneo.presentation.features.home

import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.TransactionUI

data class HomeState(
    val expandedDropdownMenu: Boolean = false,
    val isLoading: Boolean = false,
    val allPeriods: List<ControlPeriodUI>? = null,
    val periodSelected: ControlPeriodUI? = null,
    val transactions: List<TransactionUI>? = null
)

sealed class HomeIntent {
    data object FetchData : HomeIntent()
    data class PeriodSelected(val period: ControlPeriodUI) : HomeIntent()
}

sealed class HomedEffect {
}
