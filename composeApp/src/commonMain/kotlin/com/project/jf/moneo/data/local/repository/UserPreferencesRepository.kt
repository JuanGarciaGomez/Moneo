package com.project.jf.moneo.data.local.repository

import com.project.jf.moneo.data.local.settings.SettingsMoneo.settings

class UserPreferencesRepository() {

    companion object {
        private const val HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
    }

    fun saveHasCompletedOnboarding(hasCompletedOnboarding: Boolean) {
        settings.putBoolean(HAS_COMPLETED_ONBOARDING, hasCompletedOnboarding)
    }

    fun getHasCompletedOnboarding(): Boolean {
        return settings.getBoolean(HAS_COMPLETED_ONBOARDING, false)
    }

    fun clearAll() {
        settings.clear()
    }

    fun removeHasCompletedOnboarding() {
        settings.remove(HAS_COMPLETED_ONBOARDING)
    }
}
