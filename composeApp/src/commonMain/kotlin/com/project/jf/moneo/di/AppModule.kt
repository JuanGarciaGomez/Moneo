package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import com.project.jf.moneo.presentation.onboarding.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { OnboardingViewModel() }
    single { UserPreferencesRepository(get()) }
}

expect fun platformModule(): Module