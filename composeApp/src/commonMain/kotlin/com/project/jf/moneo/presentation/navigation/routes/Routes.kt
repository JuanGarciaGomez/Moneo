package com.project.jf.moneo.presentation.navigation.routes

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Onboarding : Routes

    @Serializable
    data object FirstPeriod : Routes

    @Serializable
    data object Dashboard : Routes

    @Serializable
    data object Reports : Routes

    @Serializable
    data object Settings : Routes
}