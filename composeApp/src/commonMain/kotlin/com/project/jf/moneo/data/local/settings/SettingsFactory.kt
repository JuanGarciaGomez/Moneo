package com.project.jf.moneo.data.local.settings

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}