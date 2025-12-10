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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Composable
fun Onboarding(viewModel: OnboardingViewModel = koinInject()) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Saldos Claros, Decisiones Inteligentes.",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start
            )

            Text(
                text = "Visualiza el estado de tu Periodo de Control activo y entiende dónde gastas más para tomar mejores decisiones.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )

            BarChartPlaceholder()

            Text(
                text = "Podras ver este resumen cada vez que ingreses a la app en la opción REPORTES ",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
        ) {
            Text("Continuar y Crear mi Primer Periodo")
        }
    }
}

@Composable
fun BarChartPlaceholder() {
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
            Onboarding(OnboardingViewModel())
        }
    }
}
