package com.project.jf.moneo.data.local.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {
    actual fun createSettings(): Settings {
        val delegate = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(delegate)
    }
}