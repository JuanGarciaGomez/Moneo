package com.project.jf.moneo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.jf.moneo.presentation.onboarding.OnboardingRender
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                OnboardingRender()
            }
        }
    }
}