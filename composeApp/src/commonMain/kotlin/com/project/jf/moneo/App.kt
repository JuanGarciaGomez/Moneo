package com.project.jf.moneo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.project.jf.moneo.presentation.navigation.NavigationWrapper
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}