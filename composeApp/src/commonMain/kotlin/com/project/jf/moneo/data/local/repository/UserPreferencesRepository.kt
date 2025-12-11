package com.project.jf.moneo.data.local.repository

import com.project.jf.moneo.data.local.settings.SettingsFactory.observableSettings

class UserPreferencesRepository() {

    companion object {
        private const val HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
    }

    fun saveHasCompletedOnboarding(hasCompletedOnboarding: Boolean) {
        observableSettings.putBoolean(HAS_COMPLETED_ONBOARDING, hasCompletedOnboarding)
    }

    fun getHasCompletedOnboarding(): Boolean {
        return observableSettings.getBoolean(HAS_COMPLETED_ONBOARDING, false)
    }

    fun clearAll() {
        observableSettings.clear()
    }

    fun removeHasCompletedOnboarding() {
        observableSettings.remove(HAS_COMPLETED_ONBOARDING)
    }
}
