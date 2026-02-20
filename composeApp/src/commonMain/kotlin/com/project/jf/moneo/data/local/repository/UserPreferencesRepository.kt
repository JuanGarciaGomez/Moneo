package com.project.jf.moneo.data.local.repository

import com.project.jf.moneo.data.local.settings.SettingsMoneo.settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserPreferencesRepository() {

    companion object {
        private const val HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
        private const val USER_NAME = "user_name"
    }

    suspend fun saveUserName(userName: String) {
        withContext(Dispatchers.IO) {
            settings.putString(USER_NAME, userName)
        }
    }

    suspend fun getUserName(): String {
        return withContext(Dispatchers.IO) {
            settings.getString(USER_NAME, "")
        }
    }

    suspend fun saveHasCompletedOnboarding(hasCompletedOnboarding: Boolean) {
        withContext(Dispatchers.IO) {
            settings.putBoolean(HAS_COMPLETED_ONBOARDING, hasCompletedOnboarding)
        }
    }

    suspend fun getHasCompletedOnboarding(): Boolean {
        return withContext(Dispatchers.IO) {
            settings.getBoolean(HAS_COMPLETED_ONBOARDING, false)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.IO) {
            settings.clear()
        }
    }

    suspend fun removeHasCompletedOnboarding() {
        withContext(Dispatchers.IO) {
            settings.remove(HAS_COMPLETED_ONBOARDING)
        }
    }
}
