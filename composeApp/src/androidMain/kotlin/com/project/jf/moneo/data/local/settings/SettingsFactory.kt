package com.project.jf.moneo.data.local.settings

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): ObservableSettings {
        val sharedPreferences = context.getSharedPreferences(
            "moneo_prefs",
            Context.MODE_PRIVATE
        )
        return SharedPreferencesSettings(sharedPreferences)
    }
}