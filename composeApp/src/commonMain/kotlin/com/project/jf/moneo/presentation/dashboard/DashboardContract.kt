package com.project.jf.moneo.presentation.dashboard

import com.project.jf.moneo.presentation.model.BottomNavigationItems
import com.project.jf.moneo.presentation.model.ControlPeriodUI
import com.project.jf.moneo.presentation.model.TransactionUI

data class DashboardState(
    val expandedDropdownMenu: Boolean = false,
    val isLoading: Boolean = false,
    val allPeriods: List<ControlPeriodUI>? = null,
    val periodSelected: ControlPeriodUI? = null,
    val transactions: List<TransactionUI>? = null,
    val bottomNavSelected: BottomNavigationItems = BottomNavigationItems.HOME
)

sealed class DashboardIntent {
    data object FetchData : DashboardIntent()
    data class BottomNavSelected(val item: BottomNavigationItems) : DashboardIntent()
    data class PeriodSelected(val period: ControlPeriodUI) : DashboardIntent()
}

sealed class DashboardEffect {

}