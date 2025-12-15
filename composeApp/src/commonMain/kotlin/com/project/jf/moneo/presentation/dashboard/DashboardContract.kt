package com.project.jf.moneo.presentation.dashboard

data class DashboardState(
    val expandedDropdownMenu: Boolean = false,
)

sealed interface DashboardIntent {

}

sealed interface DashboardEffect {

}