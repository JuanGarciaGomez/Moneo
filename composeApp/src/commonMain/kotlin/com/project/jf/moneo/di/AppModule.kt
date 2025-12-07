package com.project.jf.moneo.di

import com.project.jf.moneo.Greeting
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { Greeting() }
}

expect fun platformModule(): Module