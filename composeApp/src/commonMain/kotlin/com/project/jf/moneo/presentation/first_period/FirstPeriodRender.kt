package com.project.jf.moneo.presentation.first_period

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import org.koin.compose.koinInject

@Composable
fun FirstPeriodRender(viewModel: FirstPeriodViewModel = koinInject()) {
    FirstPeriodScreen()
}

@Composable
fun FirstPeriodScreen() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(Res.drawable.arrow_back), contentDescription = null)
                Text(
                    text = stringResource(Res.string.ob_initial_period),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.medium
                    ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.ob_initial_period_description),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = stringResource(Res.string.ob_initial_period_alternative_description),
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.medium
                    ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { "" },
                    label = { Text(text = stringResource(Res.string.ob_initial_period_name)) },
                    supportingText = { Text(text = stringResource(Res.string.ob_initial_period_name_option)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                    singleLine = true
                )
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
        ) {
            Text(stringResource(Res.string.ob_initial_period_button_start))
        }
    }
}


@Preview
@Composable
fun OnboardingPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FirstPeriodScreen()
        }
    }
}
