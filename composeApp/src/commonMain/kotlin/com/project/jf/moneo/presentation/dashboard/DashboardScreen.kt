package com.project.jf.moneo.presentation.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.project.jf.moneo.presentation.components.BaseScreen
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = koinViewModel()) {
    DashboardContent()
}

@Composable
fun DashboardContent() {
    BaseScreen(topBar = { DashboardTopBar() }) {}
}


@OptIn(ExperimentalMaterial3Api::class)
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
private fun DashboardBottomBar(onClick: () -> Unit) {

}

@Preview
@Composable
fun DashboardPreview() {
    DashboardContent()
}