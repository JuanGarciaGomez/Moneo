package com.project.jf.moneo.data.local.settings

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): Settings {
        val delegate = context.getSharedPreferences(
            "app_settings",
            Context.MODE_PRIVATE
        )
        return SharedPreferencesSettings(delegate)
    }
}