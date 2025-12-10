package com.project.jf.moneo

import android.app.Application
import com.project.jf.moneo.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MoneoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MoneoApp)
        }
    }
}