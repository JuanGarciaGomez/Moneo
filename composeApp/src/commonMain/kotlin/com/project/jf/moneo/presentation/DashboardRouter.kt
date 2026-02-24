package com.project.jf.moneo.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.jf.moneo.presentation.components.BaseScreen
import com.project.jf.moneo.presentation.components.DashboardBottomBar
import com.project.jf.moneo.presentation.features.home.HomeScreen
import com.project.jf.moneo.presentation.model.BottomNavigationItems
import com.project.jf.moneo.presentation.navigation.routes.DashboardRoute
import com.project.jf.moneo.presentation.features.report.ReportsScreen
import com.project.jf.moneo.presentation.features.setting.SettingsScreen
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardRouter() {

    val nestedNavController = rememberNavController()
    val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedItem = BottomNavigationItems.entries.find { item ->
        currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
    } ?: BottomNavigationItems.HOME

    BaseScreen(
        topBar = {
            DashboardTopBar()
        },
        bottomBar = {
            DashboardBottomBar(selectedItem) { item ->
                nestedNavController.navigate(item.route) {
                    popUpTo(nestedNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        content = {
            NavHost(
                navController = nestedNavController,
                startDestination = DashboardRoute.Home
            ) {
                composable<DashboardRoute.Home> {
                    HomeScreen(onNavigateToHistory = {})
                }
                composable<DashboardRoute.Reports> {
                    ReportsScreen()
                }
                composable<DashboardRoute.Settings> {
                    SettingsScreen()
                }
            }
        }
    )
}

@Composable
private fun DashboardTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    )
}

@Composable
fun DashboardRouterPreview(content: @Composable () -> Unit) {
    BaseScreen(
        topBar = {
            DashboardTopBar()
        },
        bottomBar = {
            DashboardBottomBar(BottomNavigationItems.HOME) {}
        },
        content = content
    )
}
