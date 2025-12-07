package com.project.jf.moneo

import androidx.compose.ui.window.ComposeUIViewController
import com.project.jf.moneo.data.local.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val db = getDatabaseBuilder().build()
    App()
}