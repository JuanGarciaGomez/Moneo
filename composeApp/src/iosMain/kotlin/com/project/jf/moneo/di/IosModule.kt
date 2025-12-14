package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.MoneoDatabase
import com.project.jf.moneo.data.local.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<MoneoDatabase> {
        getDatabaseBuilder().build()
    }
}