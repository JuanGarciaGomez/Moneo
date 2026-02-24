package com.project.jf.moneo.presentation.model

import com.project.jf.moneo.presentation.navigation.routes.DashboardRoute
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.bar_chart
import moneo.composeapp.generated.resources.dashboard_bottom_nav_Reports
import moneo.composeapp.generated.resources.dashboard_bottom_nav_home
import moneo.composeapp.generated.resources.dashboard_bottom_nav_settings
import moneo.composeapp.generated.resources.home
import moneo.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class BottomNavigationItems(
    val title: StringResource,
    val icon: DrawableResource,
    val route: DashboardRoute
) {
    HOME(
        title = Res.string.dashboard_bottom_nav_home,
        icon = Res.drawable.home,
        route = DashboardRoute.Home
    ),
    REPORTS(
        title = Res.string.dashboard_bottom_nav_Reports,
        icon = Res.drawable.bar_chart,
        route = DashboardRoute.Reports
    ),
    SETTINGS(
        title = Res.string.dashboard_bottom_nav_settings,
        icon = Res.drawable.settings,
        route = DashboardRoute.Settings
    ),
}
