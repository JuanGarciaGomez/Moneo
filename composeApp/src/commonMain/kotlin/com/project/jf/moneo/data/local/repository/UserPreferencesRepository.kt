package com.project.jf.moneo.data.local.repository

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalSettingsApi::class)
class UserPreferencesRepository(private val settings: ObservableSettings) {

    private val flowSettings = settings.toFlowSettings()

    companion object {
        private const val HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
    }

    val hasCompletedOnboardingFlow: Flow<Boolean> =
        flowSettings.getBooleanFlow(HAS_COMPLETED_ONBOARDING, false)

    fun saveHasCompletedOnboarding(hasCompletedOnboarding: Boolean) {
        settings.putBoolean(HAS_COMPLETED_ONBOARDING, hasCompletedOnboarding)
    }

    fun clearAll() {
        settings.clear()
    }

    fun removeHasCompletedOnboarding() {
        settings.remove(HAS_COMPLETED_ONBOARDING)
    }
}
