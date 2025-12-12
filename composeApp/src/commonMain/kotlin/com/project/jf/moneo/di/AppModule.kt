package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import com.project.jf.moneo.presentation.first_period.FirstPeriodScreenModel
import com.project.jf.moneo.presentation.onboarding.OnboardingScreenModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { OnboardingScreenModel(get()) }
    factory { FirstPeriodScreenModel() }
    single { UserPreferencesRepository() }
}

expect fun platformModule(): Module