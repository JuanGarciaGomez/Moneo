package com.project.jf.moneo.presentation.model

import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.bar_chart
import moneo.composeapp.generated.resources.dashboard_bottom_nav_Reports
import moneo.composeapp.generated.resources.dashboard_bottom_nav_home
import moneo.composeapp.generated.resources.dashboard_bottom_nav_settings
import moneo.composeapp.generated.resources.home
import moneo.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class BottomNavigationItems(val title: StringResource, val icon: DrawableResource) {
    HOME(title = Res.string.dashboard_bottom_nav_home, icon = Res.drawable.home),
    REPORTS(title = Res.string.dashboard_bottom_nav_Reports, icon = Res.drawable.bar_chart),
    SETTINGS(title = Res.string.dashboard_bottom_nav_settings, icon = Res.drawable.settings),
}
