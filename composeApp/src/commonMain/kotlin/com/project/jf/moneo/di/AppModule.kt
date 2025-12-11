package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import com.project.jf.moneo.data.local.settings.SettingsFactory
import com.project.jf.moneo.presentation.first_period.FirstPeriodViewModel
import com.project.jf.moneo.presentation.onboarding.OnboardingViewModel
import com.russhwolf.settings.ObservableSettings
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { OnboardingViewModel(get()) }
    factory { FirstPeriodViewModel() }
    single { UserPreferencesRepository(get()) }
    single<ObservableSettings> { get<SettingsFactory>().createSettings() }
}

expect fun platformModule(): Module