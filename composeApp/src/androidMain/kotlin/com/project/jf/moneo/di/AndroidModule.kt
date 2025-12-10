package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.settings.SettingsFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { SettingsFactory(get()) }
}