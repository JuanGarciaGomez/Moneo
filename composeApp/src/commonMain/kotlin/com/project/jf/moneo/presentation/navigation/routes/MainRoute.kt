package com.project.jf.moneo.presentation.navigation.routes

import kotlinx.serialization.Serializable

sealed interface MainRoute {
    @Serializable
    data object Onboarding : MainRoute

    @Serializable
    data object FirstPeriod : MainRoute

    @Serializable
    data object Dashboard : MainRoute
}