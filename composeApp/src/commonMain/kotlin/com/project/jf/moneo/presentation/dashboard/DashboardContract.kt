package com.project.jf.moneo.presentation.dashboard

import com.project.jf.moneo.presentation.model.BottomNavigationItems
import com.project.jf.moneo.presentation.model.ControlPeriodUI

data class DashboardState(
    val expandedDropdownMenu: Boolean = false,
    val isLoading: Boolean = false,
    val allPeriods: List<ControlPeriodUI>? = null,
    val bottomNavSelected: BottomNavigationItems = BottomNavigationItems.HOME
)

sealed class DashboardIntent {
    data object FetchData : DashboardIntent()
    data class BottomNavSelected(val index: BottomNavigationItems) : DashboardIntent()
}

sealed class DashboardEffect {

}