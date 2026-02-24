package com.project.jf.moneo.presentation.dashboard

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.project.jf.moneo.presentation.model.BottomNavigationItems
import com.project.jf.moneo.presentation.navigation.routes.DashboardRoute
import kotlinx.coroutines.flow.collectLatest
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handleIntent(DashboardIntent.FetchData)
    }

    DashboardContent(state, viewModel::handleIntent)
}

@Composable
fun DashboardContent(state: DashboardState, handleIntent: (intent: DashboardIntent) -> Unit) {
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
                    HomeScreen(
                        state = state,
                        handleIntent = handleIntent,
                        onNavigateToHistory = {}
                    )
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
fun ReportsScreen() {
    Text(text = "Reports Screen")
}

@Composable
fun SettingsScreen() {
    Text(text = "Settings Screen")
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
