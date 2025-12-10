package com.project.jf.moneo.di

import com.project.jf.moneo.Greeting
import com.project.jf.moneo.presentation.onboarding.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { Greeting() }
    factory { OnboardingViewModel() }
}

expect fun platformModule(): Module