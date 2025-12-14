package com.project.jf.moneo.presentation.first_period

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.jf.moneo.presentation.components.DatePickerField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.LocalDate
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.arrow_back
import moneo.composeapp.generated.resources.ob_initial_period
import moneo.composeapp.generated.resources.ob_initial_period_alternative_description
import moneo.composeapp.generated.resources.ob_initial_period_button_start
import moneo.composeapp.generated.resources.ob_initial_period_description
import moneo.composeapp.generated.resources.ob_initial_period_name
import moneo.composeapp.generated.resources.ob_initial_period_name_option
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FirstPeriodScreen(
    viewModel: FirstPeriodViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                FirstPeriodEffect.NavigateBack -> onNavigateBack()
                FirstPeriodEffect.NavigateToNextScreen -> {
                    // TODO: Navegar a la siguiente pantalla
                    // Por ahora solo volvemos atrás
                    onNavigateBack()
                }
            }
        }
    }

    FirstPeriodContent(
        state = state,
        onIntent = viewModel::handleIntent
    )
}

@Composable
private fun FirstPeriodContent(
    state: FirstPeriodState,
    onIntent: (FirstPeriodIntent) -> Unit = {}
) {
    Scaffold(
        topBar = {
            FirstPeriodTopBar(
                onNavigateBack = { onIntent(FirstPeriodIntent.NavigateBack) }
            )
        },
        bottomBar = {
            FirstPeriodBottomBar(
                enabled = state.canProceed && !state.isLoading,
                isLoading = state.isLoading,
                onClick = { onIntent(FirstPeriodIntent.SavePeriod) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DescriptionCard()

            FormCard(
                periodName = state.periodName,
                startDate = state.startDate,
                periodNameError = state.periodNameError,
                onPeriodNameChange = { onIntent(FirstPeriodIntent.UpdatePeriodName(it)) },
                onStartDateChange = { onIntent(FirstPeriodIntent.UpdateStartDate(it)) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstPeriodTopBar(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.ob_initial_period),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = "Volver"
                )
            }
        }
    )
}

@Composable
private fun DescriptionCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(Res.string.ob_initial_period_description),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = stringResource(Res.string.ob_initial_period_alternative_description),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun FormCard(
    periodName: String,
    periodNameError: Boolean,
    startDate: LocalDate,
    onPeriodNameChange: (String) -> Unit,
    onStartDateChange: (LocalDate) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = periodName,
                onValueChange = onPeriodNameChange,
                label = {
                    Text(text = stringResource(Res.string.ob_initial_period_name))
                },
                supportingText = {
                    Text(
                        text = stringResource(Res.string.ob_initial_period_name_option),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = periodNameError
            )

            DatePickerField(
                label = "Fecha de inicio",
                selectedDate = startDate,
                onDateSelected = onStartDateChange
            )
        }
    }
}

@Composable
private fun FirstPeriodBottomBar(
    enabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 3.dp,
        shadowElevation = 8.dp
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = enabled && !isLoading
        ) {
            if (isLoading) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                    Text(
                        text = "Guardando...",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            } else {
                Text(
                    text = stringResource(Res.string.ob_initial_period_button_start),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Preview
@Composable
fun FirstPeriodPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstPeriodContent(
                state = FirstPeriodState(
                    periodName = "Mi primer periodo",
                    canProceed = true
                )
            )
        }
    }
}

@Preview
@Composable
fun FirstPeriodLoadingPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstPeriodContent(
                state = FirstPeriodState(
                    periodName = "Mi primer periodo",
                    isLoading = true,
                    canProceed = true
                )
            )
        }
    }
}

@Preview
@Composable
fun FirstPeriodEmptyPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstPeriodContent(
                state = FirstPeriodState()
            )
        }
    }
}

@Preview
@Composable
fun FirstPeriodErrorPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstPeriodContent(
                state = FirstPeriodState(
                    periodName = "Mi periodo",
                )
            )
        }
    }
}
