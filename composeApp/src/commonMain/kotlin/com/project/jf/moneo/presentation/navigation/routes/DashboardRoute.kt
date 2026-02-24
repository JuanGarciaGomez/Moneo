package com.project.jf.moneo.presentation.navigation.routes

import kotlinx.serialization.Serializable

sealed interface DashboardRoute {
    @Serializable
    data object Home : DashboardRoute

    @Serializable
    data object Reports : DashboardRoute

    @Serializable
    data object Settings : DashboardRoute
}