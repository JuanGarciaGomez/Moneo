package com.project.jf.moneo.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import moneo.composeapp.generated.resources.Res
import moneo.composeapp.generated.resources.ob_button_continue
import moneo.composeapp.generated.resources.ob_completed
import moneo.composeapp.generated.resources.ob_current_period
import moneo.composeapp.generated.resources.ob_distribution_title
import moneo.composeapp.generated.resources.ob_report_info
import moneo.composeapp.generated.resources.ob_subtitle
import moneo.composeapp.generated.resources.ob_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = koinViewModel()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                OnboardingEffect.NavigateToFirstPeriod -> {
                }
            }
        }
    }

    OnboardingContent(
        state = state.value,
        handleIntent = viewModel::handleIntent
    )
}


@Composable
fun OnboardingContent(
    state: OnboardingState,
    handleIntent: (intent: OnboardingIntent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = stringResource(Res.string.ob_title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start
            )

            Text(
                text = stringResource(
                    Res.string.ob_completed,
                    state.hasCompletedOnboarding.toString()
                ),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(Res.string.ob_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )

            BarChartPlaceholder()

            Text(
                text = stringResource(Res.string.ob_report_info),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }

        Button(
            onClick = { handleIntent(OnboardingIntent.ContinueOnboarding) },
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
        ) {
            Text(stringResource(Res.string.ob_button_continue))
        }
    }
}

@Composable
fun BarChartPlaceholder() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.ob_distribution_title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = stringResource(Res.string.ob_current_period),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Bar(heightFraction = 0.1f, color = MaterialTheme.colorScheme.primary)
            Bar(heightFraction = 0.9f, color = MaterialTheme.colorScheme.secondary)
            Bar(heightFraction = 0.4f, color = MaterialTheme.colorScheme.tertiary)
            Bar(heightFraction = 0.7f, color = MaterialTheme.colorScheme.primaryContainer)
        }
    }
}

@Composable
fun RowScope.Bar(heightFraction: Float, color: Color) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(heightFraction)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(color)
            .padding(horizontal = 4.dp)
    )
}


@Preview
@Composable
fun OnboardingPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            OnboardingContent(
                state = OnboardingState(hasCompletedOnboarding = false),
                handleIntent = {}
            )
        }
    }
}
