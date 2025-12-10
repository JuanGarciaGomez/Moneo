package com.project.jf.moneo.data.local.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {
    actual fun createSettings(): ObservableSettings {
        val userDefaults = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(userDefaults)
    }
}